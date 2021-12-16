package com.example.ulide.ui.myProfile.tabs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.ulide.data.LoginDataSource;
import com.example.ulide.databinding.FragmentTabSpotsBinding;
import com.example.ulide.downloaders.JSONArrayDownloader;
import com.example.ulide.downloaders.JSONObjDownloader;
import com.example.ulide.ui.myProfile.MyAdapter;
import com.google.android.material.tabs.TabLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class TabSpots extends Fragment {

    private FragmentTabSpotsBinding binding;

    private ExpandableListView expandableListView;
    private List<String> listGroup;
    private HashMap<String, List<String>> listItem;
    private ExpListViewAdapter adapter;

    private ArrayList<String> favSpotsName;
    private ArrayList<String> spotsRate;
    private ArrayList<String> spotsComment;
    private ArrayList<String> spotsEvalName;
    private ArrayList<String> spotsDoneName;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentTabSpotsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        expandableListView = binding.profileSpotsList;
        listGroup = new ArrayList<>();
        listItem = new HashMap<>();

        // Get Favorites, Evaluations and Done Spots from User
        String id = String.valueOf(LoginDataSource.ID);
        String urlFav = "https://ulide.herokuapp.com/api/spots/fav/user/" + id;
        String urlEval = "https://ulide.herokuapp.com/api/spotsEvaluations/user/" + id;
        String urlEvalSpots = "https://ulide.herokuapp.com/api/spots/eval/user/" + id;
        String urlDone = "https://ulide.herokuapp.com/api/spots/done/user/" + id;

        favSpotsName = getJsonArray(urlFav, "spName");
        spotsRate = new ArrayList<>();
        spotsComment = new ArrayList<>();
        getEvalArray(urlEval);
        spotsEvalName = getJsonArray(urlEvalSpots, "spName");
        spotsDoneName = getJsonArray(urlDone, "spName");

        // Expandable List View
        adapter = new ExpListViewAdapter(getContext(), listGroup, listItem);
        expandableListView.setAdapter(adapter);
        initListData();

        return root;
    }

    private void initListData() {
        listGroup.add("Favorites");
        listGroup.add("Evaluations");
        listGroup.add("Comments");
        listGroup.add("Done");

        ArrayList<String> list1 = new ArrayList<>();
        for (int i = 0; i < spotsEvalName.size(); i++){
            list1.add(spotsEvalName.get(i) + " - Rate: " + spotsRate.get(i));
        }

        ArrayList<String> list2 = new ArrayList<>();
        for (int i = 0; i < spotsEvalName.size(); i++){
            list2.add(spotsEvalName.get(i) + " - Comment: " + spotsComment.get(i));
        }

        listItem.put(listGroup.get(0), favSpotsName);
        listItem.put(listGroup.get(1), list1);
        listItem.put(listGroup.get(2), list2);
        listItem.put(listGroup.get(3), spotsDoneName);
        adapter.notifyDataSetChanged();
    }

    public void getEvalArray(String url){
        JSONArray jsonArray;
        JSONArrayDownloader task = new JSONArrayDownloader();
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
                    spotsRate.add(obj.getString("seRate"));
                    spotsComment.add(obj.getString("seComment"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public ArrayList<String> getJsonArray(String url, String key){
        JSONArray jsonArray;
        JSONArrayDownloader task = new JSONArrayDownloader();
        try {
            jsonArray = task.execute(url).get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
            jsonArray = null;
        }

        JSONObject obj;
        ArrayList<String> arrayList = new ArrayList<>();
        if (jsonArray != null) {
            for (int i = 0; i < jsonArray.length(); i++) {
                try {
                    obj = jsonArray.getJSONObject(i);
                    arrayList.add(obj.getString(key));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }

        return arrayList;
    }
}
