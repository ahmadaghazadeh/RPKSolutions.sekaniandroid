package com.example.sekini.ui.dialog.incorrect;

import android.arch.lifecycle.ViewModelProvider;

import com.example.sekini.utils.base.ViewModelProviderFactory;

import dagger.Module;
import dagger.Provides;

@Module
public class IncorrectDialogModule {

    @Provides
    ViewModelProvider.Factory getFactory(IncorrectDialogViewModel model){
        return new ViewModelProviderFactory<>(model);
    }

}

