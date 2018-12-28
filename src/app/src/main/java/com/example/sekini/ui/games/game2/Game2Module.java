package com.example.sekini.ui.games.game2;

import android.arch.lifecycle.ViewModelProvider;

import com.example.sekini.utils.base.ViewModelProviderFactory;

import dagger.Module;
import dagger.Provides;

@Module
public class Game2Module {

    @Provides
    ViewModelProvider.Factory getFactory(Game2ViewModel model){
        return new ViewModelProviderFactory<>(model);
    }

}

