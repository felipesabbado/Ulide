package com.example.ulide.ui.spot;

import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ulide.databinding.FragmentSpotBinding;
import com.example.ulide.downloaders.ImageDownloader;
import com.example.ulide.downloaders.JSONArrayDownloader;
import com.example.ulide.downloaders.JSONObjDownloader;
import com.example.ulide.ui.spotsFromRoute.RecycleViewSpotsFromRoutesFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

public class SpotFragment extends Fragment {
    private FragmentSpotBinding binding;
    private String idSpot;
    private JSONObject jsonObjectSpot;

    protected static String spotName;
    protected String spotLat;
    protected String spotLong;
    protected String spotBio;

    private String tags = "";

    private ImageView spotImage;
    private TextView textViewSpotName;
    private TextView textViewSpotBio;
    private TextView textViewSpotTags;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        idSpot = RecycleViewSpotsFromRoutesFragment.ID_SPOT;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentSpotBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        textViewSpotName = binding.textViewSpotName;
        textViewSpotBio = binding.textViewDescription;
        textViewSpotTags = binding.textViewSpotTags;
        spotImage = binding.spotImage;

        getJSON(idSpot);
        getJSONTags(idSpot);

        textViewSpotName.setText(spotName);
        textViewSpotBio.setText(spotBio);
        textViewSpotTags.setText(tags);

        downloadImage();

        return root;
    }

    public void downloadImage() {
        ImageDownloader task = new ImageDownloader();
        try {
            //"https://bit.ly/ulidespot" + idSpot
            Bitmap myImage = task.execute("https://bit.ly/ulidespot" + idSpot).get();
            spotImage.setImageBitmap(myImage);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void getJSON(String id){
        JSONObjDownloader task = new JSONObjDownloader();
        String url = "https://ulide.herokuapp.com/api/spots/" + id;
        try {
            jsonObjectSpot = task.execute(url).get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
            jsonObjectSpot = null;
        }

        if (jsonObjectSpot != null) {
            for (int i = 0; i < jsonObjectSpot.length(); i++) {
                try {
                    spotName = jsonObjectSpot.getString("spName");
                    spotLat = jsonObjectSpot.getString("spLat");
                    spotLong = jsonObjectSpot.getString("spLong");
                    spotBio = jsonObjectSpot.getString("spBio");

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    public void getJSONTags(String id){
        JSONArrayDownloader task = new JSONArrayDownloader();
        String url = "https://ulide.herokuapp.com/api/tags/spot/" + id;
        JSONArray jsonArray;
        try {
            jsonArray = task.execute(url).get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
            jsonArray = null;
        }

        JSONObject obj;
        if (jsonArray != null) {
            for (int i = 0; i < jsonArray.length(); i++) {
                try {
                    obj = jsonArray.getJSONObject(i);
                    tags += obj.getString("tgName") + "; ";
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}