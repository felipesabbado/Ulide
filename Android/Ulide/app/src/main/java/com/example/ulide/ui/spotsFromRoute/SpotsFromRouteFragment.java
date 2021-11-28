package com.example.ulide.ui.spotsFromRoute;

import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.ulide.databinding.SpotsFromRouteFragmentBinding;
import com.example.ulide.downloaders.JSONArrayDownloader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class SpotsFromRouteFragment extends Fragment {

    ListView listViewRouteSpots;
    ArrayList<String> spots;
    ArrayList<String> spotsId;
    ArrayAdapter<String> adapterSpots;
    JSONArray spotsArray = null;
    private SpotsFromRouteViewModel spotsFromRouteViewModel;
    private SpotsFromRouteFragmentBinding binding;

    public static SpotsFromRouteFragment newInstance() {
        return new SpotsFromRouteFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        final ListView listViewRouteSpots = binding.ListViewRouteSpots;

        spotsFromRouteViewModel =
                new ViewModelProvider(this).get(SpotsFromRouteViewModel.class);

        binding = SpotsFromRouteFragmentBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        return root;
    }

    private void InicializeListView(){
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
            adapterSpots = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, spots);
            listViewRouteSpots.setAdapter(adapterSpots);
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        spotsFromRouteViewModel = new ViewModelProvider(this).get(SpotsFromRouteViewModel.class);
        // TODO: Use the ViewModel
    }

}