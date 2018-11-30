package com.example.sekini.ui.games.game1;

import android.arch.lifecycle.ViewModelProvider;

import com.example.sekini.utils.base.ViewModelProviderFactory;

import dagger.Module;
import dagger.Provides;

@Module
public class Game1Module {

    @Provides
    ViewModelProvider.Factory getFactory(Game1ViewModel model){
        return new ViewModelProviderFactory<>(model);
    }

}

