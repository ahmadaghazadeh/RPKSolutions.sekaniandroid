package com.example.sekini.ui.word.possessednoun;

import android.arch.lifecycle.ViewModelProvider;

import com.example.sekini.utils.base.ViewModelProviderFactory;

import dagger.Module;
import dagger.Provides;

@Module
public class PossessedNounModule {

    @Provides
    ViewModelProvider.Factory getFactory(PossessedNounViewModel model){
        return new ViewModelProviderFactory<>(model);
    }

}

