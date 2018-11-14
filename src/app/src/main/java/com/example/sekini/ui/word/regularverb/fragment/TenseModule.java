package com.example.sekini.ui.word.regularverb.fragment;

import android.arch.lifecycle.ViewModelProvider;

import com.example.sekini.utils.base.ViewModelProviderFactory;

import dagger.Module;
import dagger.Provides;

@Module
public class TenseModule {

    @Provides
    ViewModelProvider.Factory getFactory(TenseViewModel model){
        return new ViewModelProviderFactory<>(model);
    }

}

