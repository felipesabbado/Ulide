package com.example.ulide2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.ulide2.downloads.CreatSpotsDownloads;
import com.example.ulide2.downloads.JSONObjDownloader;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class SpotInfoMenu extends AppCompatActivity {
    TextView spotName;
    TextView spotLat;
    TextView spotLng;
    TextView spotPrice;
    TextView spotBio;
    JSONObject spotObj = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spot_info_menu);
        setTitle("Spot");

        spotName = findViewById(R.id.spotName);
        spotLat = findViewById(R.id.spotLat);
        spotLng = findViewById(R.id.spotLng);
        spotPrice = findViewById(R.id.spotPrice);
        spotBio = findViewById(R.id.spotBio);

        JSONObjDownloader task = new JSONObjDownloader();
        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        String url = "https://ulide.herokuapp.com/api/spots/" + id;
        try {
            spotObj = task.execute(url).get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
            spotObj = null;
        }

        try {
            spotName.setText(spotObj.getString("spName"));
            spotLat.setText(spotObj.getString("spLat"));
            spotLng.setText(spotObj.getString("spLong"));
            if (spotObj.getBoolean("spPrice")) {
                spotPrice.setText("Pago");
            } else if (spotObj.getBoolean("spPrice")){
                spotPrice.setText("Grátis");
            } else {
                spotPrice.setText("Indeterminado");
            }
            spotBio.setText(spotObj.getString("spBio"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}