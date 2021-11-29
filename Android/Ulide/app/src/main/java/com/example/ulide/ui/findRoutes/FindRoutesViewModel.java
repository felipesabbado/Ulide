package com.example.ulide.ui.findRoutes;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class FindRoutesViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public FindRoutesViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is gallery fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}