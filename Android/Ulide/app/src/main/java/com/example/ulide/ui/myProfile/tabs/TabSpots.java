package com.example.ulide.ui.myProfile.tabs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.ulide.databinding.FragmentTabSpotsBinding;
import com.example.ulide.ui.myProfile.MyAdapter;
import com.google.android.material.tabs.TabLayout;

public class TabSpots extends Fragment {

    private FragmentTabSpotsBinding binding;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentTabSpotsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        tabLayout = binding.tabSpots;
        viewPager = binding.viewerPageSpots;

        tabLayout.setupWithViewPager(viewPager);

        MyAdapter adapter = new MyAdapter(getActivity().getSupportFragmentManager(),
                FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        adapter.addFragment(new TabSpotsDone(), "Done");
        adapter.addFragment(new TabSpotsFav(), "Favorites");
        adapter.addFragment(new TabSpotsEval(), "Evaluations");
        viewPager.setAdapter(adapter);

        return root;
    }
}
