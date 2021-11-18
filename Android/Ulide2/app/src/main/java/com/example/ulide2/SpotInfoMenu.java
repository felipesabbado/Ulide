package com.example.ulide2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.ulide2.downloads.JSONObjDownloader;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

public class SpotInfoMenu extends AppCompatActivity {
    private String spotName;
    private double spotLat;
    private double spotLng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spot_info_menu);

        TextView spotPrice = findViewById(R.id.spotPrice);
        TextView spotBio = findViewById(R.id.spotBio);

        JSONObjDownloader task = new JSONObjDownloader();

        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        String url = "https://ulide.herokuapp.com/api/spots/" + id;

        JSONObject spotObj;

        try {
            spotObj = task.execute(url).get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
            spotObj = null;
        }

        try {
            spotName = spotObj.getString("spName");
            spotLat = spotObj.getDouble("spLat");
            spotLng = spotObj.getDouble("spLong");
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

        setTitle(spotName);
    }
}