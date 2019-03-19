package com.nidhin.koinexticker.di;

import com.nidhin.koinexticker.homescreen.presentation.MainActivity;
import com.nidhin.koinexticker.homescreen.di.MainActivityModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuilderModule {
    @ContributesAndroidInjector(modules = {MainActivityModule.class})
    abstract MainActivity mainActivity();

}
