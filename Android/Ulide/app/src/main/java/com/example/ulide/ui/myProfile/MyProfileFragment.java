package com.example.ulide.ui.myProfile;


import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import androidx.fragment.app.Fragment;

import com.example.ulide.data.LoginDataSource;
import com.example.ulide.databinding.FragmentMyProfileBinding;


public class MyProfileFragment extends Fragment {

    private FragmentMyProfileBinding binding;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentMyProfileBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        int id;

        id = LoginDataSource.ID;
        Log.e("hereeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee", ""+id);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}