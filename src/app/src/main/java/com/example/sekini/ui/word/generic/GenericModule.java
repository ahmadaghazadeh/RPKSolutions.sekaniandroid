package com.example.sekini.ui.word.generic;

import android.arch.lifecycle.ViewModelProvider;

import com.example.sekini.utils.base.ViewModelProviderFactory;

import dagger.Module;
import dagger.Provides;

@Module
public class GenericModule {

    @Provides
    ViewModelProvider.Factory getFactory(GenericViewModel model){
        return new ViewModelProviderFactory<>(model);
    }

}

