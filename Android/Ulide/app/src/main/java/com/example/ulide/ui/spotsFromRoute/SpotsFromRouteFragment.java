package com.example.ulide.ui.spotsFromRoute;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.ulide.MainActivity;
import com.example.ulide.R;
import com.example.ulide.databinding.FragmentSpotsFromRouteBinding;
import com.example.ulide.downloaders.JSONArrayDownloader;
import com.example.ulide.ui.findRoutes.FindRoutesFragment;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;

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
    private JSONArray spotsArray;

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


        getParentFragmentManager().setFragmentResultListener("route", this, new FragmentResultListener() {
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

    private final OnMapReadyCallback callback = new OnMapReadyCallback() {

        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         * In this case, we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to the app.
         */
        private GoogleMap mMap;

        @Override
        public void onMapReady(GoogleMap googleMap) {
            mMap = googleMap;

            mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);

            // Add a marker in Sydney and move the camera
            LatLng lisbon = new LatLng(38.736946, -9.142685);
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(lisbon, 13));
        }
    };

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
        if (spotsArray != null) {
            for (int i = 0; i < spotsArray.length(); i++) {
                try {
                    obj = spotsArray.getJSONObject(i);
                    spots.add(obj.getString("spName"));
                    spotsId.add(obj.getString("id"));
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
    }
}