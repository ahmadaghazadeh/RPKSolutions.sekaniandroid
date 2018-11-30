package com.example.sekini.ui.login;

import android.arch.lifecycle.ViewModelProvider;

import com.example.sekini.utils.base.ViewModelProviderFactory;

import dagger.Module;
import dagger.Provides;

@Module
public class LoginModule {

    @Provides
    ViewModelProvider.Factory getFactory(LoginViewModel model){
        return new ViewModelProviderFactory<>(model);
    }

}

