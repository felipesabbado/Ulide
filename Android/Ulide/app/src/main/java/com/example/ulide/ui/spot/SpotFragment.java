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
import com.example.ulide.downloaders.JSONObjDownloader;
import com.example.ulide.ui.spotsFromRoute.RecycleViewSpotsFromRoutesFragment;

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

    private ImageView spotImage;
    private TextView editTextSpotName;
    private TextView editTextSpotBio;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        idSpot = RecycleViewSpotsFromRoutesFragment.ID_SPOT;

        JSONObjDownloader task = new JSONObjDownloader();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentSpotBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        editTextSpotName = binding.textViewSpotName;
        editTextSpotBio = binding.textViewDescription;
        spotImage = binding.spotImage;

        getJSON(idSpot);

        editTextSpotName.setText(spotName);
        editTextSpotBio.setText(spotBio);

        downloadImage();

        return root;
    }

    public void downloadImage() {
        ImageDownloader task = new ImageDownloader();
        try {
            Bitmap myImage = task.execute("https://bit.ly/ulideSpot" + idSpot).get();
            spotImage.setImageBitmap(myImage);
            Log.e("Image", "OK");
        } catch (ExecutionException e) {
            e.printStackTrace();
            Log.e("Image", "ERRO01");
        } catch (InterruptedException e) {
            e.printStackTrace();
            Log.e("Image", "ERRO02");
        }
    }

    public void getJSON(String id){
        JSONObjDownloader task = new JSONObjDownloader();
        String url = "https://ulide.herokuapp.com/api/spots/" + id    ;
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
}