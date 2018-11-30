package com.example.sekini.ui.dictionary;

import android.arch.lifecycle.ViewModelProvider;

import com.example.sekini.utils.base.ViewModelProviderFactory;

import dagger.Module;
import dagger.Provides;

@Module
public class DictionaryModule {

    @Provides
    ViewModelProvider.Factory getFactory(DictionaryViewModel model){
        return new ViewModelProviderFactory<>(model);
    }

}

