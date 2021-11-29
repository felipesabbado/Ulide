package com.example.ulide.ui.spotsFromRoute;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.ulide.MainActivity;
import com.example.ulide.R;
import com.example.ulide.databinding.FragmentSpotsFromRouteBinding;
import com.example.ulide.downloaders.JSONArrayDownloader;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class SpotsFromRouteFragment extends Fragment {

    private FragmentSpotsFromRouteBinding binding;
    private ListView listViewSpots;

    private ArrayAdapter<String> adapterSpots;
    private ArrayList<String> spots;
    private ArrayList<String> spotsId;
    private ArrayList<String> lat;
    private ArrayList<String> lng;
    private JSONArray spotsArray;
    private GoogleMap mMap;
    private LatLng spotPos;

    public SpotsFromRouteFragment() {
        // Required empty public constructor
    }

//    @Override
//    public void onPrepareOptionsMenu(@NonNull Menu menu) {
//        super.onPrepareOptionsMenu(menu);
//        MenuItem item = menu.findItem(R.id.spots);
//        item.setVisible(isEditing);
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSpotsFromRouteBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(callback);
        }

        setHasOptionsMenu(false);

        listViewSpots = binding.listViewSpots;


        getParentFragmentManager().setFragmentResultListener("route",
                this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle bundle) {
                // We use a String here, but any type that can be put in a Bundle is supported
                String id = bundle.getString("id");
                String name = bundle.getString("name");
                // Do something with the result
                ((MainActivity) getActivity()).getSupportActionBar().setTitle(name);

                getJSON(id,name);
            }
        });

        return root;
    }

    public void getJSON(String id, String name){
        JSONArrayDownloader task = new JSONArrayDownloader();
        String url = "https://ulide.herokuapp.com/api/routes/" + id + "/spots";
        try {
            spotsArray = task.execute(url).get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
            spotsArray = null;
        }

        JSONObject obj;
        spots = new ArrayList<>();
        spotsId = new ArrayList<>();
        lat = new ArrayList<>();
        lng = new ArrayList<>();
        if (spotsArray != null) {
            for (int i = 0; i < spotsArray.length(); i++) {
                try {
                    obj = spotsArray.getJSONObject(i);
                    spots.add(obj.getString("spName"));
                    spotsId.add(obj.getString("id"));
                    lat.add(obj.getString("spLat"));
                    lng.add(obj.getString("spLong"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            Log.e("SPOTS", spots.toString());
            Log.i("INFO", name);
            InitalizeAdapter();
        }
    }

    public void InitalizeAdapter() {
        adapterSpots = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, spots);
        listViewSpots.setAdapter(adapterSpots);
        createListViewClickItemEvent(listViewSpots, spots);
    }

    private final OnMapReadyCallback callback = new OnMapReadyCallback() {
        @Override
        public void onMapReady(GoogleMap googleMap) {
            mMap = googleMap;

            mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);

            // Add a marker in each spot
            for(int i = 0; i < spotsArray.length(); i++){
                spotPos = new LatLng(Double.parseDouble(lat.get(i)), Double.parseDouble(lng.get(i)));
                mMap.addMarker(new MarkerOptions().position(spotPos).title(spots.get(i)));
            }
            LatLng lisbon = new LatLng(38.736946, -9.142685);
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(lisbon, 11));
        }
    };

    private void createListViewClickItemEvent(ListView list, final ArrayList<String> item) {
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                spotPos = new LatLng(Double.parseDouble(lat.get(i)), Double.parseDouble(lng.get(i)));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(spotPos, 13.5f));
                for (int k = 0; k < spotsArray.length(); k++){
                    if (k != i){
                        spotPos = new LatLng(Double.parseDouble(lat.get(k)), Double.parseDouble(lng.get(k)));
                        mMap.addMarker(new MarkerOptions().position(spotPos)
                                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
                    } else {
                        spotPos = new LatLng(Double.parseDouble(lat.get(i)), Double.parseDouble(lng.get(i)));
                        mMap.addMarker(new MarkerOptions().position(spotPos)
                                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
                    }
                }
            }
        });
    }
}