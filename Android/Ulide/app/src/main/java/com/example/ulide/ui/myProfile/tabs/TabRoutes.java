package com.example.ulide.ui.myProfile.tabs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.ulide.databinding.FragmentTabRoutesBinding;

public class TabRoutes extends Fragment {

    private FragmentTabRoutesBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentTabRoutesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        return root;
    }
}
