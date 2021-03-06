package com.example.sekini.ui.games.game1.fragment;

import android.arch.lifecycle.ViewModelProvider;

import com.example.sekini.utils.base.ViewModelProviderFactory;

import dagger.Module;
import dagger.Provides;

@Module
public class Game1ItemModule {

    @Provides
    ViewModelProvider.Factory getFactory(Game1ItemViewModel model){
        return new ViewModelProviderFactory<>(model);
    }

}

