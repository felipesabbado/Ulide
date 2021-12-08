package com.example.ulide.ui.myProfile.tabs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.ulide.databinding.FragmentTabSpotsBinding;

public class TabSpots extends Fragment {

    private FragmentTabSpotsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentTabSpotsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        return root;
    }
}
