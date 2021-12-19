package com.example.ulide.ui.spot;

import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.ulide.MainActivity;
import com.example.ulide.databinding.FragmentSpotBinding;
import com.example.ulide.downloaders.ImageDownloader;
import com.example.ulide.downloaders.JSONArrayDownloader;
import com.example.ulide.downloaders.JSONObjDownloader;
import com.example.ulide.ui.spotsFromRoute.RecycleViewSpotsFromRoutesFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
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

    private ListView listViewSpotEvaluations;
    public ArrayAdapter<String> adapter;
    private ArrayList<String> spotsRate;
    private ArrayList<String> spotsComment;

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
        listViewSpotEvaluations = binding.listViewSpotEvaluations;

        getJSON(idSpot);
        getJSONTags(idSpot);
        getJSONEvaluations(idSpot);

        ((MainActivity) getActivity()).getSupportActionBar().setTitle(spotName);

        textViewSpotName.setText(spotName);
        textViewSpotBio.setText(spotBio);
        textViewSpotTags.setText(tags);

        initializeAdapter();

        downloadImage();

        return root;
    }

    private void initializeAdapter() {
        ArrayList<String> evaluations = new ArrayList<>();
        for (int i = 0; i < spotsRate.size(); i++) {
            if (spotsComment.get(i) == "null") {
                evaluations.add("Rate: " + spotsRate.get(i));
            } else {
                evaluations.add("Rate: " + spotsRate.get(i) + " - Comment: " + spotsComment.get(i));
            }
        }
        adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, evaluations);
        listViewSpotEvaluations.setAdapter(adapter);
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

    public void getJSONEvaluations(String id){
        JSONArrayDownloader task = new JSONArrayDownloader();
        String url = "https://ulide.herokuapp.com/api/spots/" + id + "/eval";
        JSONArray jsonArray;
        try {
            jsonArray = task.execute(url).get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
            jsonArray = null;
        }

        JSONObject obj;
        spotsRate = new ArrayList<>();
        spotsComment = new ArrayList<>();
        if (jsonArray != null) {
            for (int i = 0; i < jsonArray.length(); i++) {
                try {
                    obj = jsonArray.getJSONObject(i);
                    spotsRate.add(obj.getString("seRate"));
                    spotsComment.add(obj.getString("seComment"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}