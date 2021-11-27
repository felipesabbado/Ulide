package com.example.ulide2;

import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.ulide2.TestActivity.Spiners;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.example.ulide2.databinding.ActivityMainMenuBinding;

public class MainMenu extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMainMenuBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainMenuBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);

        // Add a marker in Sydney and move the camera
        LatLng lisbon = new LatLng(38.736946, -9.142685);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(lisbon, 13));
    }

    public void routes(View view) {
        Intent routesMenu = new Intent(getApplicationContext(), RoutesMenu.class);
        startActivity(routesMenu);
    }

    public void MyProfileMenu(View view) {
        Intent profileMenu = new Intent(getApplicationContext(), MyProfileMenu.class);
        startActivity(profileMenu);
    }

    public void CreatSpotsMenu(View view) {
        Intent profileMenu = new Intent(getApplicationContext(), Spiners.class);
        startActivity(profileMenu);
    }
}