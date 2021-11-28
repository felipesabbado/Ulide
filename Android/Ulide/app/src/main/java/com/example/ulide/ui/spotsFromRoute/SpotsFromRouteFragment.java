package com.example.ulide.ui.spotsFromRoute;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.ulide.databinding.FragmentSpotsFromRouteBinding;
import com.example.ulide.downloaders.JSONArrayDownloader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class SpotsFromRouteFragment extends Fragment {

    private FragmentSpotsFromRouteBinding binding;
    private ListView listViewSpots;

    private ArrayAdapter<String> adapterSpots;
    private ArrayList<String> spots;
    private ArrayList<String> spotsId;
    private JSONArray spotsArray;

    public SpotsFromRouteFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSpotsFromRouteBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        listViewSpots = binding.listViewSpots;
        JSONArrayDownloader task = new JSONArrayDownloader();
        Intent intent = getActivity().getIntent();
        String id = intent.getStringExtra("id");
        String url = "https://ulide.herokuapp.com/api/routes/" + id + "/spots";
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
            InitalizeAdapter();
        }

        return root;
    }

    public void InitalizeAdapter() {
        adapterSpots = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, spots);
        listViewSpots.setAdapter(adapterSpots);
    }
}