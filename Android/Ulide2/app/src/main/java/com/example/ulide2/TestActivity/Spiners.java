package com.example.ulide2.TestActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.ulide2.R;
import com.example.ulide2.downloads.JSONArrayDownloader;
import com.example.ulide2.downloads.RoutesDownloads;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class Spiners extends AppCompatActivity {
    Spinner spinnerRoutes, spinnerSpots;

    private String[] countries = {"Portugal", "Espanha"};
    private String[] portugal = {"Lisboa", "Porto", "Faro"};
    private String[] espanha = {"Madrid", "Barcelona", "Valencia"};

    ArrayList<String> routes;
    ArrayList<String> routesId;
    ArrayList<String> routesName;
    JSONArray objRoutesAvg = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spiners);

        RoutesDownloads task = new RoutesDownloads();
        try {
            objRoutesAvg = task.execute("https://ulide.herokuapp.com/api/routes/avg").get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
            objRoutesAvg = null;
        }

        JSONObject obj;
        routes = new ArrayList<>();
        routesId = new ArrayList<>();
        routesName = new ArrayList<>();
        if(objRoutesAvg != null) {
            for(int i = 0; i < objRoutesAvg.length(); i++) {
                try {
                    obj = objRoutesAvg.getJSONObject(i);
                    double routesAvg = obj.getDouble("rtAvg");
                    String routeName = obj.getString("rtName");
                    routes.add(String.format("%s - Rate: %.2f", routeName, routesAvg));
                    routesId.add(obj.getString("id"));
                    routesName.add(obj.getString("rtName"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }



        spinnerRoutes = (Spinner) findViewById(R.id.spinnerRoutes);
        spinnerSpots = (Spinner) findViewById(R.id.spinnerSpots);

        ArrayAdapter<String> spinnerArrayAdapter =
                new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, routes);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerRoutes.setAdapter(spinnerArrayAdapter);

        spinnerRoutes.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String countryName = spinnerRoutes.getSelectedItem().toString();
                Log.i("PAIS ->", countryName);

                ArrayList<String> spots;
                ArrayList<String> spotsId;

                JSONArray spotsArray = null;

                JSONArrayDownloader task = new JSONArrayDownloader();
                String url = "https://ulide.herokuapp.com/api/routes/" + routesId.get(position) + "/spots";
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
                }



                ArrayAdapter<String> adapter= new ArrayAdapter<String>(view.getContext(),android.R.layout.simple_spinner_item,spots);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerSpots.setAdapter(adapter);



            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}