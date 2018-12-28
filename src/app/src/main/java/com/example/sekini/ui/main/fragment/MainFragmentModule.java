package com.example.sekini.ui.main.fragment;

import android.arch.lifecycle.ViewModelProvider;

import com.example.sekini.utils.base.ViewModelProviderFactory;

import dagger.Module;
import dagger.Provides;

@Module
public class MainFragmentModule {

    @Provides
    ViewModelProvider.Factory getFactory(MainViewModel model){
        return new ViewModelProviderFactory<>(model);
    }

}

