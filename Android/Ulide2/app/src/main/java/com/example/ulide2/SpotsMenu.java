package com.example.ulide2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.ulide2.downloads.JSONArrayDownloader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class SpotsMenu extends AppCompatActivity {

    ListView listViewRouteSpots;
    ArrayList<String> spots;
    ArrayList<String> spotsId;
    ArrayAdapter<String> adapterSpots;
    JSONArray spotsArray = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spots_menu);

        listViewRouteSpots = findViewById(R.id.ListViewRouteSpots);

        JSONArrayDownloader task = new JSONArrayDownloader();
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
            InitalizeAdapter();
        }

        setTitle(intent.getStringExtra("name"));
    }

    public void InitalizeAdapter() {
        adapterSpots = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, spots);
        listViewRouteSpots.setAdapter(adapterSpots);
        createListViewClickItemEvent(listViewRouteSpots, spots, spotsId);
    }

    private void createListViewClickItemEvent(ListView list, final ArrayList<String> items,
                                              final ArrayList<String> id) {
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.e("INFO", "O nome do spot é: " + items.get(i));
                Log.e("INFO", "O id do spot é: " + id.get(i));
                Intent spotInfoMenu = new Intent(getApplicationContext(), SpotInfoMenu.class);
                spotInfoMenu.putExtra("id", id.get(i));
                startActivity(spotInfoMenu);
            }
        });
    }
}