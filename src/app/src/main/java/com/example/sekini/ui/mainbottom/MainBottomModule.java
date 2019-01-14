package com.example.sekini.ui.mainbottom;

import android.arch.lifecycle.ViewModelProvider;

import com.example.sekini.utils.base.ViewModelProviderFactory;

import dagger.Module;
import dagger.Provides;

@Module
public class MainBottomModule {

    @Provides
    ViewModelProvider.Factory getFactory(MainBottomViewModel model){
        return new ViewModelProviderFactory<>(model);
    }

}

