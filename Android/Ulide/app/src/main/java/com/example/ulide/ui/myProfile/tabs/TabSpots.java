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

import com.example.ulide.databinding.FragmentTabSpotsBinding;
import com.example.ulide.ui.myProfile.MyAdapter;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TabSpots extends Fragment {

    private FragmentTabSpotsBinding binding;

    private ExpandableListView expandableListView;
    private List<String> listGroup;
    private HashMap<String, List<String>> listItem;
    private ExpListViewAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentTabSpotsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        expandableListView = binding.profileSpotsList;
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
        listGroup.add("Done");

        List<String> list = new ArrayList<>();
        list.add("Spot1");
        list.add("Spot2");
        list.add("Spot3");
        list.add("Spot4");
        list.add("Spot5");

        listItem.put(listGroup.get(0), list);
        listItem.put(listGroup.get(1), list);
        listItem.put(listGroup.get(2), list);
        adapter.notifyDataSetChanged();
    }
}
