package com.example.ulide.ui.myProfile.tabs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.ulide.databinding.FragmentTabRoutesBinding;
import com.example.ulide.ui.myProfile.MyAdapter;
import com.google.android.material.tabs.TabLayout;

public class TabRoutes extends Fragment {

    private FragmentTabRoutesBinding binding;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentTabRoutesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        tabLayout = binding.tabRoutes;
        viewPager = binding.viewerPageRoutes;

        tabLayout.setupWithViewPager(viewPager);

        MyAdapter adapter = new MyAdapter(getActivity().getSupportFragmentManager(),
                FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        adapter.addFragment(new TabRoutesDone(), "Done");
        adapter.addFragment(new TabRoutesFav(), "Favorites");
        adapter.addFragment(new TabRoutesEval(), "Evaluations");
        viewPager.setAdapter(adapter);

        return root;
    }
}
