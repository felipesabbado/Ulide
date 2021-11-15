package com.example.ulide.Actvities;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ulide.R;
import com.example.ulide.downloads.RoutesJson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;


public class RoutesActivity extends AppCompatActivity {

    ListView listViewRoutesNames;
    JSONArray allRoutesJson = null;
    ArrayList<String> rtNames;
    ArrayAdapter<String> rtNameAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_routes);

        listViewRoutesNames = findViewById(R.id.ListViewRouteNames);

        RoutesJson task = new RoutesJson();
        try {
            allRoutesJson = task.execute("http://ulide.herokuapp.com/api/routes").get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
            allRoutesJson = null;
        }

        if(allRoutesJson != null) {
            for (int i = 0; i < allRoutesJson.length(); i++) {
                try {
                    JSONObject jsonPart = allRoutesJson.getJSONObject(i);
                    rtNames.add(jsonPart.getString("rtName"));

                } catch (Exception e) {
                    System.out.println("Nao deuuuuuuuuuuuuuuuuuu");
                }
            }
            InitializeAdapter();
        } else {
            Log.e("Info", "Nao Tem nada");
        }
    }




    public void InitializeAdapter(){
        rtNameAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, rtNames);
        listViewRoutesNames.setAdapter(rtNameAdapter);
    }
}