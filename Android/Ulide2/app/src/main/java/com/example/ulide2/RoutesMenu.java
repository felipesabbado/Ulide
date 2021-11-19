package com.example.ulide2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.ulide2.downloads.RoutesDownloads;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class RoutesMenu extends AppCompatActivity {

    ListView listViewRoutes;
    ArrayList<String> routes;
    ArrayList<String> routesId;
    ArrayList<String> routesName;
    ArrayAdapter<String> adapterRoutes;
    JSONArray objRoutesAvg = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_routes_menu);
        setTitle("Routes");

        listViewRoutes = findViewById(R.id.ListViewPopularRoutes);

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
            Log.e("Array List", routes.toString());
            InitializeAdapter();
        }
    }


    public void InitializeAdapter(){
        adapterRoutes = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, routes);
        listViewRoutes.setAdapter(adapterRoutes);
        createListViewClickItemEvent(listViewRoutes, routes, routesId, routesName);
    }
    
    private void createListViewClickItemEvent(ListView list, final ArrayList<String> items,
                                              final ArrayList<String> id, final ArrayList<String> name) {
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.e("INFO", "O nome da rota é: " + items.get(i));
                Log.e("INFO", "O id da rota é: " + id.get(i));
                Intent spotsMenu = new Intent(getApplicationContext(), SpotsMenu.class);
                spotsMenu.putExtra("id", id.get(i));
                spotsMenu.putExtra("name", name.get(i));
                startActivity(spotsMenu);
            }
        });
    }
}
