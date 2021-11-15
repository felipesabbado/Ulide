package com.example.ulide2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.ulide2.downloads.CreatSpotsDownloads;
import com.example.ulide2.downloads.DownloadTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

public class CreateSpotsMenu extends AppCompatActivity {

    ImageView image;

    JSONObject objectWeather = null;
    JSONArray arrayWeather = null;
    String objectWeatherToString;
    //

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_spots_menu);

        image = findViewById(R.id.imageView);

        CreatSpotsDownloads task = new CreatSpotsDownloads();
        try {
            objectWeather = task.execute("http://ulide.herokuapp.com/api/userAchievements/1").get();
            objectWeatherToString = objectWeather.toString();

            arrayWeather = new JSONArray(objectWeatherToString);
        } catch (ExecutionException e) {
            e.printStackTrace();
            arrayWeather = null;
        } catch (InterruptedException e) {
            e.printStackTrace();
            arrayWeather = null;
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public void getWeather(View view)
    {
        if(arrayWeather != null)
        {
            for(int i=0; i<objectWeather.length(); i++)
            {
                try {
                    JSONObject jsonPart = arrayWeather.getJSONObject(i);

                    switch (jsonPart.getString("acName")){
                        case "Nivel 1":
                            image.setImageResource(R.drawable.ic_thunderstorm);
                            break;
                        case "Nivel 2":
                            image.setImageResource(R.drawable.ic_drizzle);
                            break;
                        case "Nivel 3":
                            image.setImageResource(R.drawable.ic_rain);
                            break;
                        case "Nivel 4":
                            image.setImageResource(R.drawable.ic_snow);
                            break;
                        case "1 Museu Visitado":
                            image.setImageResource(R.drawable.ic_clear);
                            break;
                        case "Clouds":
                            image.setImageResource(R.drawable.ic_cloud);
                            break;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}