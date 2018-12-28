package com.example.sekini.ui.games.game2.fragment;

import android.arch.lifecycle.ViewModelProvider;

import com.example.sekini.utils.base.ViewModelProviderFactory;

import dagger.Module;
import dagger.Provides;

@Module
public class Game2ItemModule {

    @Provides
    ViewModelProvider.Factory getFactory(Game2ItemViewModel model){
        return new ViewModelProviderFactory<>(model);
    }

}

