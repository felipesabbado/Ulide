package com.example.ulide2.downloads;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class CreatSpotsDownloads extends AsyncTask<String, Void, JSONObject> {
    @Override
    protected JSONObject doInBackground(String... urls) {

        String result = "";
        URL url;
        HttpURLConnection urlConnection = null;

        try{

            url = new URL(urls[0]);
            urlConnection = (HttpURLConnection) url.openConnection();

            InputStream in = urlConnection.getInputStream();
            InputStreamReader reader = new InputStreamReader(in);

            int data = reader.read();
            while(data != -1)
            {
                char current = (char)data;
                result += current;
                data = reader.read();
            }

            Log.e("Json Result", "" + result);
            JSONObject jsonObject = new JSONObject(result);
            String weatherInfo = jsonObject.getString("uaAc");
            JSONObject arr = new JSONObject(weatherInfo);

            return arr;

        }catch (Exception e){
            Log.e("Nao deu", "Nao sei porque");
            e.printStackTrace();
            return null;
        }

    }

    @Override
    protected void onPostExecute(JSONObject jsonObject) {
        super.onPostExecute(jsonObject);
    }
}
