package com.nidhin.koinexticker.homescreen.di;

import com.google.gson.Gson;
import com.nidhin.koinexticker.api.ApiManager;
import com.nidhin.koinexticker.homescreen.data.IMainActivityRepository;
import com.nidhin.koinexticker.homescreen.domain.FetchLatestPrices;
import com.nidhin.koinexticker.homescreen.domain.MainActivityRepository;

import dagger.Module;
import dagger.Provides;

@Module
public class MainActivityModule {

    @Provides
    MainActivityRepository provideHomeRepository(ApiManager apiManager, Gson gson) {
        return new IMainActivityRepository(apiManager,gson);
    }

    @Provides
    MainViewModelFactory provideViewModelFactory(FetchLatestPrices fetchLatestPrices) {
        return new MainViewModelFactory(fetchLatestPrices);
    }
}
