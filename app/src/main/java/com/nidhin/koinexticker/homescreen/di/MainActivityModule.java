package com.nidhin.koinexticker.homescreen.di;

import com.google.gson.Gson;
import com.nidhin.koinexticker.api.ApiManager;
import com.nidhin.koinexticker.homescreen.data.IMainActivityRepository;
import com.nidhin.koinexticker.homescreen.domain.CheckLastCheckedAt;
import com.nidhin.koinexticker.homescreen.domain.FetchLatestPrices;
import com.nidhin.koinexticker.homescreen.domain.MainActivityRepository;
import com.nidhin.koinexticker.persistance.SharedPrefsManager;

import dagger.Module;
import dagger.Provides;

@Module
public class MainActivityModule {

    @Provides
    MainActivityRepository provideHomeRepository(ApiManager apiManager, SharedPrefsManager sharedPrefsManager,Gson gson) {
        return new IMainActivityRepository(apiManager,sharedPrefsManager,gson);
    }

    @Provides
    MainViewModelFactory provideViewModelFactory(FetchLatestPrices fetchLatestPrices, CheckLastCheckedAt checkLastCheckedAt) {
        return new MainViewModelFactory(fetchLatestPrices,checkLastCheckedAt);
    }
}
