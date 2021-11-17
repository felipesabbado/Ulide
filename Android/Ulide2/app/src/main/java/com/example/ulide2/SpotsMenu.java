package com.example.ulide2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.ulide2.downloads.RoutesDownloads;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class SpotsMenu extends AppCompatActivity {

    ListView listViewRouteSpots;
    ArrayList<String> spots;
    ArrayAdapter<String> adapterSpots;
    JSONArray spotsArray = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spots_menu);
        setTitle("Spots");

        listViewRouteSpots = findViewById(R.id.ListViewRouteSpots);

        RoutesDownloads task = new RoutesDownloads();
        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        String url = "https://ulide.herokuapp.com/api/routes/" + id + "/spots";
        try {
            spotsArray = task.execute(url).get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
            spotsArray = null;
        }

        JSONObject obj;
        spots = new ArrayList<>();
        if (spotsArray != null) {
            for (int i = 0; i < spotsArray.length(); i++) {
                try {
                    obj = spotsArray.getJSONObject(i);
                    spots.add(obj.getString("spName"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            Log.e("SPOTS", spots.toString());
            InitalizeAdapter();
        }
    }

    public void InitalizeAdapter() {
        adapterSpots = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, spots);
        listViewRouteSpots.setAdapter(adapterSpots);
    }
}