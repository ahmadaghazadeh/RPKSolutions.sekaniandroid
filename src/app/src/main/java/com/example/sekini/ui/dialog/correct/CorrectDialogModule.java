package com.example.sekini.ui.dialog.correct;

import android.arch.lifecycle.ViewModelProvider;

import com.example.sekini.utils.base.ViewModelProviderFactory;

import dagger.Module;
import dagger.Provides;

@Module
public class CorrectDialogModule {

    @Provides
    ViewModelProvider.Factory getFactory(CorrectDialogViewModel model){
        return new ViewModelProviderFactory<>(model);
    }

}

