package com.example.ulide.ui.myProfile;

import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.ulide.R;
import com.example.ulide.data.LoginDataSource;
import com.example.ulide.databinding.FragmentMyProfileBinding;
import com.example.ulide.ui.myProfile.tabs.TabRoutes;
import com.example.ulide.ui.myProfile.tabs.TabSpots;
import com.google.android.material.tabs.TabLayout;

public class MyProfileFragment extends Fragment {

    private FragmentMyProfileBinding binding;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentMyProfileBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        tabLayout = binding.tabProfile;
        viewPager = binding.viewPager;

        int id;

        id = LoginDataSource.ID;
        Log.e("hereeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee", ""+id);

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