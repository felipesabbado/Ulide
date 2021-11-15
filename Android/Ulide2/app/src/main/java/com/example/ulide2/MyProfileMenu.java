package com.example.ulide2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.ulide2.downloads.MyProfileDownloads;
import com.example.ulide2.downloads.RoutesDownloads;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class MyProfileMenu extends AppCompatActivity {

    TextView textView;
    ListView listViewRoutes;
    ArrayList<String> arrayListRoutes;
    ArrayAdapter<String> adapterRoutes;
    JSONArray objUsTu = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile_menu);

        textView = findViewById(R.id.textView);
        listViewRoutes = findViewById(R.id.ListViewPopularRoutes);

        MyProfileDownloads task = new MyProfileDownloads();
        try {
            objUsTu = task.execute("https://ulide.herokuapp.com/api/routes").get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
            objUsTu = null;
        }
    }
    public void getWeather(View view) {
        JSONObject obj = null;
        ArrayList<String> routes = new ArrayList<>();
        if(objUsTu != null) {
            for(int i = 0; i < objUsTu.length(); i++) {
                try {
                    obj = objUsTu.getJSONObject(i);
                    routes.add(obj.getString("rtName"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
        textView.setText(routes.toString());
    }
}