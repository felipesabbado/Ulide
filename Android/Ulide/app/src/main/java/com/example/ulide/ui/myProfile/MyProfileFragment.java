package com.example.ulide.ui.myProfile;

import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.ulide.R;
import com.example.ulide.data.LoginDataSource;
import com.example.ulide.databinding.FragmentMyProfileBinding;
import com.example.ulide.downloaders.JSONObjDownloader;
import com.example.ulide.ui.myProfile.tabs.TabRoutes;
import com.example.ulide.ui.myProfile.tabs.TabSpots;
import com.google.android.material.tabs.TabLayout;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

public class MyProfileFragment extends Fragment {

    private FragmentMyProfileBinding binding;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private TextView profileName, profileEmail, profileBio;
    private JSONObject jsonUser;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentMyProfileBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        tabLayout = binding.tabProfile;
        viewPager = binding.viewPager;
        profileName = binding.profileName;
        profileEmail = binding.profileEmail;
        profileBio = binding.profileBio;

        int id;
        id = LoginDataSource.ID;
        Log.e("USER_ID", ""+id);

        // Buscar as infos do user na BD
        JSONObjDownloader task = new JSONObjDownloader();
        String url = "https://ulide.herokuapp.com/api/users/"+id;
        try {
            jsonUser = task.execute(url).get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
            jsonUser = null;
        }

        if(jsonUser != null) {
            try {
                profileName.setText(jsonUser.getString("usName"));
                profileEmail.setText(jsonUser.getString("usEmail"));
                profileBio.setText(jsonUser.getString("usBio"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else {
            Log.e("USER_JSON", "NULL");
        }

        tabLayout.setupWithViewPager(viewPager);
        tabLayout.addTab(tabLayout.newTab().setText("Routes"));
        tabLayout.addTab(tabLayout.newTab().setText("Spots"));
        MyAdapter adapter = new MyAdapter(getActivity().getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        adapter.addFragment(new TabRoutes(), "Routes");
        adapter.addFragment(new TabSpots(), "Spots");
        viewPager.setAdapter(adapter);

        return root;
    }
}