package com.example.ulide2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.ulide2.downloads.DownloadTask;
import com.example.ulide2.downloads.RoutesDownloads;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class RoutesMenu extends AppCompatActivity {

    ListView listViewRoutes;
    ArrayList<String> arrayListRoutes;
    ArrayAdapter<String> adapterRoutes;
    JSONArray objUsTu = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_routes_menu);
        setTitle("Routes");

        listViewRoutes = findViewById(R.id.ListViewPopularRoutes);

        RoutesDownloads task = new RoutesDownloads();
        try {
            objUsTu = task.execute("https://ulide.herokuapp.com/api/routes").get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
            objUsTu = null;
        }

        JSONObject obj = null;
        arrayListRoutes = new ArrayList<>();
        if(objUsTu != null) {
            for(int i = 0; i < objUsTu.length(); i++) {
                try {
                    obj = objUsTu.getJSONObject(i);
                    arrayListRoutes.add(obj.getString("rtName"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            Log.e("Array List", arrayListRoutes.toString());
            InitializeAdapter();
        }
    }


    public void InitializeAdapter(){
        adapterRoutes = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrayListRoutes);
        listViewRoutes.setAdapter(adapterRoutes);
        createListViewClickItemEvent(listViewRoutes, arrayListRoutes);
    }
    
    private void createListViewClickItemEvent(ListView list, final ArrayList<String> items) {
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.e("INFO", "O nome da rota é: " + items.get(i));
            }
        });
    }
}
