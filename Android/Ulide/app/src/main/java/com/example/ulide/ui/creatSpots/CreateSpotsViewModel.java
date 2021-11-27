package com.example.ulide.ui.creatSpots;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CreateSpotsViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public CreateSpotsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is slideshow fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}