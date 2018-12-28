package com.example.sekini.ui.dialog.loading;

import android.arch.lifecycle.ViewModelProvider;

import com.example.sekini.utils.base.ViewModelProviderFactory;

import dagger.Module;
import dagger.Provides;

@Module
public class LoadingDialogModule {

    @Provides
    ViewModelProvider.Factory getFactory(LoadingDialogViewModel model){
        return new ViewModelProviderFactory<>(model);
    }

}

