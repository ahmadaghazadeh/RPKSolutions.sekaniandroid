package com.example.sekini.ui.dialog.image;

import android.arch.lifecycle.ViewModelProvider;

import com.example.sekini.utils.base.ViewModelProviderFactory;

import dagger.Module;
import dagger.Provides;

@Module
public class ImageDialogModule {

    @Provides
    ViewModelProvider.Factory getFactory(ImageDialogViewModel model){
        return new ViewModelProviderFactory<>(model);
    }

}

