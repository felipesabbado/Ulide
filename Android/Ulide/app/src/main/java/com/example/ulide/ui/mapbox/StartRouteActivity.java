package com.example.ulide.ui.mapbox;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.ulide.R;
import com.google.android.gms.maps.MapView;

import com.mapbox.mapboxsdk.Mapbox;

public class StartRouteActivity extends AppCompatActivity {

    private MapView mapView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Mapbox.getInstance(this, getString(R.string.access_token_mapbox));
        setContentView(R.layout.activity_start_route);

        mapView = (MapView) findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }
}