package com.example.ulide.ui.myProfile;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.ulide.databinding.FragmentMyProfileBinding;
import com.example.ulide.downloaders.JSONArrayDownloader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class MyProfileFragment extends Fragment {

    private MyProfileViewModel myProfileViewModel;
    private FragmentMyProfileBinding binding;


    ListView listViewRoutes;
    ArrayList<String> routes;
    ArrayList<String> routesId;
    ArrayList<String> routesName;
    ArrayAdapter<String> adapterRoutes;
    JSONArray objRoutesAvg = null;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        myProfileViewModel =
                new ViewModelProvider(this).get(MyProfileViewModel.class);

        binding = FragmentMyProfileBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final ListView listViewRoutes = binding.listRoutes;

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
            adapterRoutes = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, routes);
            listViewRoutes.setAdapter(adapterRoutes);


        }

        myProfileViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
            }
        });
        return root;
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}