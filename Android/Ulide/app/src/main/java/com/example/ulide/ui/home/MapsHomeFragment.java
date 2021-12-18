package com.example.ulide.ui.home;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ulide.R;
import com.example.ulide.data.LoginDataSource;
import com.example.ulide.downloaders.JSONArrayDownloader;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class MapsHomeFragment extends Fragment {
    private ArrayList<String> spotsName;
    private ArrayList<LatLng> spotsPos;
    private ArrayList<Marker> markers;

    private final OnMapReadyCallback callback = new OnMapReadyCallback() {
        private GoogleMap mMap;

        @Override
        public void onMapReady(GoogleMap googleMap) {
            mMap = googleMap;

            mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);

            // Add a marker in Sydney and move the camera
            LatLng lisbon = new LatLng(38.7273481, -9.2306308);
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(lisbon, 11));

            // Add a marker in each spot
            markers = new ArrayList<>();
            for(int i = 0; i < spotsPos.size(); i++){
                markers.add(mMap.addMarker(new MarkerOptions().
                        position(spotsPos.get(i)).title(spotsName.get(i))));
            }
        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        String id = String.valueOf(LoginDataSource.ID);
        String url = "https://ulide.herokuapp.com/api/spots/done/user/" + id;
        spotsName = new ArrayList<>();
        spotsPos = new ArrayList<>();
        getJsonArray(url);

        return inflater.inflate(R.layout.fragment_maps_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(callback);
        }
    }

    public void getJsonArray(String url){
        JSONArray jsonArray;
        JSONArrayDownloader task = new JSONArrayDownloader();
        try {
            jsonArray = task.execute(url).get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
            jsonArray = null;
        }

        JSONObject obj;
        if (jsonArray != null) {
            for (int i = 0; i < jsonArray.length(); i++) {
                try {
                    obj = jsonArray.getJSONObject(i);
                    spotsName.add(obj.getString("spName"));
                    spotsPos.add(new LatLng(Double.parseDouble(obj.getString("spLat")),
                            Double.parseDouble(obj.getString("spLong"))));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}