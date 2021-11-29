package com.example.ulide.ui.findRoutes;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;

import androidx.fragment.app.Fragment;

import androidx.navigation.Navigation;

import com.example.ulide.R;
import com.example.ulide.databinding.FragmentFindRoutesBinding;
import com.example.ulide.downloaders.JSONArrayDownloader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class FindRoutesFragment extends Fragment {

    private FragmentFindRoutesBinding binding;
    private ListView listViewRoutes;

    public ArrayList<String> routes;
    public ArrayList<String> routesId;
    public ArrayList<String> routesName;
    public ArrayAdapter<String> adapterRoutes;
    public JSONArray objRoutesAvg;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentFindRoutesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        listViewRoutes = binding.listRoutes;
        JSONArrayDownloader task = new JSONArrayDownloader();

        try {
            objRoutesAvg = task.execute("https://ulide.herokuapp.com/api/routes/avg").get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
            objRoutesAvg = null;
        }

        JSONObject obj;
        routes = new ArrayList<>();
        routesId = new ArrayList<>();
        routesName = new ArrayList<>();
        if(objRoutesAvg != null) {
            for(int i = 0; i < objRoutesAvg.length(); i++) {
                try {
                    obj = objRoutesAvg.getJSONObject(i);
                    double routesAvg = obj.getDouble("rtAvg");
                    String routeName = obj.getString("rtName");
                    routes.add(String.format("%s - Rate: %.2f", routeName, routesAvg));
                    routesId.add(obj.getString("id"));
                    routesName.add(obj.getString("rtName"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            Log.e("Array List", routes.toString());
            InitalizeAdapter();
        }

        return root;
    }

    public void InitalizeAdapter() {
        adapterRoutes = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, routes);
        listViewRoutes.setAdapter(adapterRoutes);
        createListViewClickItemEvent(listViewRoutes, routes, routesId, routesName);
    }

    private void createListViewClickItemEvent(ListView list, final ArrayList<String> item,
                                              final ArrayList<String> id, final ArrayList<String> name) {
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.e("INFO", item.get(i));
                Log.e("INFO", id.get(i));
                //https://developer.android.com/guide/fragments/communicate
                Bundle result = new Bundle();
                result.putString("id", id.get(i));
                result.putString("name", name.get(i));
                getParentFragmentManager().setFragmentResult("route", result);

                //https://www.codegrepper.com/code-examples/java/intent+to+move+from+one+fragment+to+another+fragment+in+android
                Navigation.findNavController(view)
                        .navigate(R.id.action_nav_find_routes_to_nav_spots_from_route);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}