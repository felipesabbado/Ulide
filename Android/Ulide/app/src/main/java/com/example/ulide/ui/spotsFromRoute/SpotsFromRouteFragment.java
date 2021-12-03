package com.example.ulide.ui.spotsFromRoute;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.navigation.Navigation;

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
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class SpotsFromRouteFragment extends Fragment
        implements GoogleMap.OnMarkerClickListener, OnMapReadyCallback {

    private FragmentSpotsFromRouteBinding binding;
    private ListView listViewSpots;

    private ArrayList<String> spotsName;
    private ArrayList<String> spotsId;
    private ArrayList<LatLng> spotsPos;
    private ArrayList<Marker> markers;
    private JSONArray spotsArray;
    private GoogleMap mMap;

    public SpotsFromRouteFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSpotsFromRouteBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
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
            Log.e("SPOTS", spotsName.toString());
            Log.i("INFO", name);
            InitalizeAdapter();
        }
    }

    public void InitalizeAdapter() {
        ArrayAdapter<String> adapterSpots = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_list_item_1, spotsName);
        listViewSpots.setAdapter(adapterSpots);
        createListViewClickItemEvent(listViewSpots, spotsName);
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);

        // Add a marker in each spot
        markers = new ArrayList<>();
        for(int i = 0; i < spotsPos.size(); i++){
            markers.add(mMap.addMarker(new MarkerOptions().position(spotsPos.get(i)).title(spotsName.get(i))));
        }
        LatLng lisbon = new LatLng(38.736946, -9.142685);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(lisbon, 11));

        // Set a listener for marker click.
        mMap.setOnMarkerClickListener(this);
    }

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

    private void createListViewClickItemEvent(ListView list, final ArrayList<String> item) {
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(spotsPos.get(i), 13.5f));
                for (int k = 0; k < spotsArray.length(); k++){
                    markers.get(k)
                            .setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
                }
                markers.get(i)
                        .setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));

                Navigation.findNavController(view)
                        .navigate(R.id.action_nav_spots_from_route_to_nav_spot);
            }
        });
    }
}