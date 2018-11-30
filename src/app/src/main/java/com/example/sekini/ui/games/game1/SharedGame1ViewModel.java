package com.example.sekini.ui.games.game1;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.example.sekini.utils.base.SharedMainModel;


public class SharedGame1ViewModel extends ViewModel{

    public MutableLiveData<Boolean> getModel() {
        return model;
    }

    private MutableLiveData<Boolean> model = new MutableLiveData<>();

    public void nextPage(){
        model.setValue(true);
    }
    public void reset(){
        model.setValue(false);
    }

}
