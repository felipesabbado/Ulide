package com.example.ulide.ui.spotsFromRoute;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ulide.MainActivity;
import com.example.ulide.R;
import com.example.ulide.databinding.FragmentRecycleViewSpotsFromRoutesBinding;

import com.example.ulide.downloaders.JSONArrayDownloader;
import com.example.ulide.ui.findRoutes.FindRoutesFragment;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;

import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;



import java.util.ArrayList;

import java.util.List;



import androidx.cardview.widget.CardView;

import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import android.widget.TextView;


import java.util.Objects;
import java.util.concurrent.ExecutionException;



import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class RecycleViewSpotsFromRoutesFragment extends Fragment implements GoogleMap.OnMarkerClickListener, OnMapReadyCallback {

    private FragmentRecycleViewSpotsFromRoutesBinding binding;



    private static final String SYMBOL_ICON_ID = "SYMBOL_ICON_ID";
    private static final String SOURCE_ID = "SOURCE_ID";
    private static final String LAYER_ID = "LAYER_ID";


    private ArrayList<String> spotsName;
    private ArrayList<String> spotsId;
    private ArrayList<LatLng> spotsPos;
    private ArrayList<Marker> markers;
    private JSONArray spotsArray;
    private GoogleMap mMap;






    @SuppressLint("RestrictedApi")
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Objects.requireNonNull(((MainActivity) requireActivity()).getSupportActionBar()).setTitle(FindRoutesFragment.NAME_ROUTE);
        Objects.requireNonNull(((MainActivity) requireActivity()).getSupportActionBar()).collapseActionView();
        getJSON(FindRoutesFragment.ID_ROUTE);

    }

    public void getJSON(String id){
        JSONArrayDownloader task = new JSONArrayDownloader();
        String url = "https://ulide.herokuapp.com/api/routes/" + id + "/spots";
        try {
            spotsArray = task.execute(url).get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
            spotsArray = null;
        }

        JSONObject obj;
        spotsName = new ArrayList<>();
        spotsId = new ArrayList<>();
        spotsPos = new ArrayList<>();
        if (spotsArray != null) {
            for (int i = 0; i < spotsArray.length(); i++) {
                try {
                    obj = spotsArray.getJSONObject(i);
                    spotsName.add(obj.getString("spName"));
                    spotsId.add(obj.getString("id"));
                    spotsPos.add(new LatLng(Double.parseDouble(obj.getString("spLat")),
                            Double.parseDouble(obj.getString("spLong"))));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        }
    }




    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentRecycleViewSpotsFromRoutesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map1);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }

        setHasOptionsMenu(false);



        return root;
    }

//    @Override
//    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//
//    }

    @Override
    public boolean onMarkerClick(@NonNull Marker marker) {
        for (int i = 0; i < markers.size(); i++){
            markers.get(i).setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
        }
        marker.setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));

        // Return false to indicate that we have not consumed the event and that we wish
        // for the default behavior to occur (which is for the camera to move such that the
        // marker is centered and for the marker's info window to open, if it has one).
        return false;
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {

        mMap = googleMap;
        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        mMap.setBuildingsEnabled(false);

        initFeatureCollection(googleMap);
        initRecyclerView();


        LatLng lisbon = new LatLng(38.736946, -9.142685);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(lisbon, 11));

        // Set a listener for marker click.
        mMap.setOnMarkerClickListener(this);

    }

    private void initFeatureCollection(GoogleMap map) {


        markers = new ArrayList<>();
        for(int i = 0; i < spotsPos.size(); i++){
            markers.add(mMap.addMarker(new MarkerOptions().position(spotsPos.get(i)).title(spotsName.get(i))));
        }


    }

    private void initRecyclerView() {
        RecyclerView recyclerView = binding.rvOnTopOfMap1;
        RecycleViewSpotsFromRoutesFragment.LocationRecyclerViewAdapter locationAdapter =
                new RecycleViewSpotsFromRoutesFragment.LocationRecyclerViewAdapter(createRecyclerViewLocations(), mMap);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),
                LinearLayoutManager.HORIZONTAL, true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(locationAdapter);
        SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(recyclerView);
    }


    private List<RecycleViewSpotsFromRoutesFragment.SingleRecyclerViewLocation> createRecyclerViewLocations() {
        ArrayList<RecycleViewSpotsFromRoutesFragment.SingleRecyclerViewLocation> locationList = new ArrayList<>();
        for (int x = 0; x < spotsPos.size(); x++) {
            RecycleViewSpotsFromRoutesFragment.SingleRecyclerViewLocation singleLocation = new RecycleViewSpotsFromRoutesFragment.SingleRecyclerViewLocation();
            singleLocation.setName(spotsName.get(x));
            singleLocation.setBedInfo(spotsName.get(x));
            singleLocation.setLocationCoordinates(spotsPos.get(x));
            locationList.add(singleLocation);
        }
        return locationList;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    class SingleRecyclerViewLocation {

        private String name;
        private String bedInfo;
        private LatLng locationCoordinates;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getBedInfo() {
            return bedInfo;
        }

        public void setBedInfo(String bedInfo) {
            this.bedInfo = bedInfo;
        }

        public LatLng getLocationCoordinates() {
            return locationCoordinates;
        }

        public void setLocationCoordinates(LatLng locationCoordinates) {
            this.locationCoordinates = locationCoordinates;
        }
    }

    static class LocationRecyclerViewAdapter extends
            RecyclerView.Adapter<RecycleViewSpotsFromRoutesFragment.LocationRecyclerViewAdapter.MyViewHolder> {

        private List<RecycleViewSpotsFromRoutesFragment.SingleRecyclerViewLocation> locationList;
        private GoogleMap map;
        long mLastClickTime = 0;

        public LocationRecyclerViewAdapter(List<RecycleViewSpotsFromRoutesFragment.SingleRecyclerViewLocation> locationList, GoogleMap map) {
            this.locationList = locationList;
            this.map = map;
        }

        @Override
        public RecycleViewSpotsFromRoutesFragment.LocationRecyclerViewAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.rv_on_top_of_map_card, parent, false);
            return new RecycleViewSpotsFromRoutesFragment.LocationRecyclerViewAdapter.MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(RecycleViewSpotsFromRoutesFragment.LocationRecyclerViewAdapter.MyViewHolder holder, int position) {
            RecycleViewSpotsFromRoutesFragment.SingleRecyclerViewLocation singleRecyclerViewLocation = locationList.get(position);
            holder.name.setText(singleRecyclerViewLocation.getName());
            holder.numOfBeds.setText(singleRecyclerViewLocation.getBedInfo());
            holder.setClickListener(new RecycleViewSpotsFromRoutesFragment.ItemClickListener() {
                @Override
                public void onClick(View view, int position) {
                    LatLng selectedLocationLatLng = locationList.get(position).getLocationCoordinates();


                    if (SystemClock.elapsedRealtime() - mLastClickTime < 250){
                        Navigation.findNavController(view).navigate(R.id.action_nav_recycle_view_spots_from_routes_to_nav_spot);
                        Log.e("Time", ""+mLastClickTime);
                    } else {

                        LatLng latLng = new LatLng(locationList.get(position).getLocationCoordinates().latitude , locationList.get(position).getLocationCoordinates().longitude );


                        map.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                        CameraPosition cameraPosition = new CameraPosition.Builder()
                                .target(latLng )      // Sets the center of the map to Mountain View
                                .zoom(18)                   // Sets the zoom
                                .bearing(90)                // Sets the orientation of the camera to east
                                .tilt(30)                   // Sets the tilt of the camera to 30 degrees
                                .build();
                        map.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
                        Log.e("Time", ""+mLastClickTime);
                    }
                    mLastClickTime = SystemClock.elapsedRealtime();
                }
            });
        }

        String n = "raining";
        @Override
        public int getItemCount() {
            return locationList.size();
        }

        static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
            TextView name;
            TextView numOfBeds;
            CardView singleCard;
            RecycleViewSpotsFromRoutesFragment.ItemClickListener clickListener;

            MyViewHolder(View view) {
                super(view);
                name = view.findViewById(R.id.location_title_tv);
                numOfBeds = view.findViewById(R.id.location_num_of_beds_tv);
                singleCard = view.findViewById(R.id.single_location_cardview);
                singleCard.setOnClickListener(this);
            }

            public void setClickListener(RecycleViewSpotsFromRoutesFragment.ItemClickListener itemClickListener) {
                this.clickListener = itemClickListener;
            }

            @Override
            public void onClick(View view) {
                clickListener.onClick(view, getLayoutPosition());
            }
        }
    }

    public interface ItemClickListener {
        void onClick(View view, int position);
    }
}