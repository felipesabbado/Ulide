package com.example.ulide.ui.myProfile.tabs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.ulide.data.LoginDataSource;
import com.example.ulide.databinding.FragmentTabRoutesBinding;
import com.example.ulide.downloaders.JSONArrayDownloader;
import com.example.ulide.downloaders.JSONObjDownloader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class TabRoutes extends Fragment {

    private FragmentTabRoutesBinding binding;

    private ExpandableListView expandableListView;
    private List<String> listGroup;
    private HashMap<String, List<String>> listItem;
    private ExpListViewAdapter adapter;

    private ArrayList<String> favRoutesName;
    private ArrayList<String> routesRate;
    private ArrayList<String> routesComment;
    private ArrayList<String> routesEvalId;
    private ArrayList<String> routesEvalName;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentTabRoutesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // Get Favorites, Evaluations and Done Routes from User
        String id = String.valueOf(LoginDataSource.ID);
        String urlFav = "https://ulide.herokuapp.com/api/routes/fav/user/" + id;
        String urlEval = "https://ulide.herokuapp.com/api/routesEvaluations/user/" + id;
        String urlRoutes = "https://ulide.herokuapp.com/api/routes/";
        // String urlDone = "https://ulide.herokuapp.com/api/routes/fav/user/";

        favRoutesName = getJsonArray(urlFav, "rtName");
        routesRate = getJsonArray(urlEval, "reRate");
        routesComment = getJsonArray(urlEval, "reComment");
        routesEvalId = getJsonArray(urlEval, "reRtId");

        routesEvalName = new ArrayList<>();
        for (int i = 0; i < routesEvalId.size(); i++){
            String name = getJsonObj(urlRoutes + routesEvalId.get(i), "rtName");
            routesEvalName.add(name);
        }
        // getJsonArray(urlDone);

        // Expandable List View
        expandableListView = binding.profileRoutesList;
        listGroup = new ArrayList<>();
        listItem = new HashMap<>();

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
        for (int i = 0; i < routesRate.size(); i++){
            list1.add(routesEvalName.get(i) + " - Rate: " + routesRate.get(i));
        }

        ArrayList<String> list2 = new ArrayList<>();
        for (int i = 0; i < routesRate.size(); i++){
            list2.add(routesEvalName.get(i) + " - Comment: " + routesComment.get(i));
        }

        listItem.put(listGroup.get(0), favRoutesName);
        listItem.put(listGroup.get(1), list1);
        listItem.put(listGroup.get(2), list2);
        listItem.put(listGroup.get(3), routesEvalId);
        adapter.notifyDataSetChanged();
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

    public String getJsonObj(String url, String key){
        JSONObject jsonObject;
        JSONObjDownloader task = new JSONObjDownloader();
        try {
            jsonObject = task.execute(url).get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
            jsonObject = null;
        }

        String result = "";
        if (jsonObject != null) {
            try {
               result = (jsonObject.getString(key));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return result;
    }
}
