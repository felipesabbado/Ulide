package com.example.ulide.ui.myProfile.tabs;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.ulide.data.LoginDataSource;
import com.example.ulide.databinding.FragmentTabRoutesFavBinding;
import com.example.ulide.downloaders.JSONArrayDownloader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class TabRoutesFav extends Fragment {
    private FragmentTabRoutesFavBinding binding;
    private ListView listView;
    private JSONArray favRoutesArray;
    private ArrayList<String> favRoutesName;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentTabRoutesFavBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        listView = binding.listViewRoutesFav;

        String id = String.valueOf(LoginDataSource.ID);

        getJSON("3");

        return root;
    }

    public void getJSON(String id){
        JSONArrayDownloader task = new JSONArrayDownloader();
        String url = "https://ulide.herokuapp.com/api/routes/fav/user/" + id;
        try {
            favRoutesArray = task.execute(url).get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
            favRoutesArray = null;
        }

        JSONObject obj;
        favRoutesName = new ArrayList<>();
        if (favRoutesArray != null) {
            for (int i = 0; i < favRoutesArray.length(); i++) {
                try {
                    obj = favRoutesArray.getJSONObject(i);
                    favRoutesName.add(obj.getString("rtName"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            Log.e("FAV SPOTS", favRoutesName.toString());
            InitalizeAdapter();
        }
    }

    public void InitalizeAdapter() {
        ArrayAdapter<String> adapterFavRoutes = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_list_item_1, favRoutesName);
        listView.setAdapter(adapterFavRoutes);
        // createListViewClickItemEvent(listView, favRoutesName);
    }
}