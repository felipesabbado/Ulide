package com.example.ulide.ui.myProfile;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.SurfaceControl;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.ulide.R;
import com.example.ulide.databinding.FragmentMyProfileBinding;
import com.example.ulide.downloaders.JSONArrayDownloader;
import com.example.ulide.ui.home.MapsHomeFragment;
import com.example.ulide.ui.spotsFromRoute.SpotsFromRouteFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class MyProfileFragment extends Fragment {

    private MyProfileViewModel myProfileViewModel;
    private FragmentMyProfileBinding binding;


    public ListView listViewRoutes;
    public ArrayList<String> routes;
    public ArrayList<String> routesId;
    public ArrayList<String> routesName;
    public ArrayAdapter<String> adapterRoutes;
    public JSONArray objRoutesAvg = null;
    NavController navController;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        myProfileViewModel =
                new ViewModelProvider(this).get(MyProfileViewModel.class);

        binding = FragmentMyProfileBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final ListView listViewRoutes = binding.listRoutes;


        myProfileViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                InicializerListView();
                listViewRoutes.setAdapter(adapterRoutes);
                listViewRoutes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Intent SpotsFromRouteFragment = new Intent(getActivity(), SpotsFromRouteFragment.class);
                        SpotsFromRouteFragment.putExtra("id", routesId.get(i));
                        SpotsFromRouteFragment.putExtra("name", routesName.get(i));

                        // Create new fragment and transaction
                        FragmentManager fragmentManager = getParentFragmentManager();
                        FragmentTransaction transaction = fragmentManager.beginTransaction();
                        transaction.setReorderingAllowed(true);

                        transaction.replace(R.id.nav_spotsFromRouteFragment, SpotsFromRouteFragment.class, null);

                        transaction.commit();
                    }
                });
            }
        });
        return root;
    }

    private void InicializerListView(){
        JSONArrayDownloader taskJson = new JSONArrayDownloader();
        try {
            objRoutesAvg = taskJson.execute("https://ulide.herokuapp.com/api/routes/avg").get();
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
        }
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}