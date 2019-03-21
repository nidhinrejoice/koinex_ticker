package com.nidhin.koinexticker.homescreen.di;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.nidhin.koinexticker.homescreen.domain.CheckLastCheckedAt;
import com.nidhin.koinexticker.homescreen.domain.FetchLatestPrices;
import com.nidhin.koinexticker.homescreen.presentation.MainViewModel;

public class MainViewModelFactory implements ViewModelProvider.Factory {


    private FetchLatestPrices mFetchLatestPrices;
    private CheckLastCheckedAt mCheckLastCheckedAt;

    public MainViewModelFactory(FetchLatestPrices fetchLatestPrices, CheckLastCheckedAt checkLastCheckedAt) {
        mFetchLatestPrices = fetchLatestPrices;
        mCheckLastCheckedAt = checkLastCheckedAt;
    }


    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        if (modelClass.isAssignableFrom(MainViewModel.class)) {
            return (T) new MainViewModel(mFetchLatestPrices,mCheckLastCheckedAt);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
