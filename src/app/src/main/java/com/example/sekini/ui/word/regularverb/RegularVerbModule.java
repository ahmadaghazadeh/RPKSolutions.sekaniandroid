package com.example.sekini.ui.word.regularverb;

import android.arch.lifecycle.ViewModelProvider;

import com.example.sekini.utils.base.ViewModelProviderFactory;

import dagger.Module;
import dagger.Provides;

@Module
public class RegularVerbModule {

    @Provides
    ViewModelProvider.Factory getFactory(RegularVerbViewModel model){
        return new ViewModelProviderFactory<>(model);
    }

}

