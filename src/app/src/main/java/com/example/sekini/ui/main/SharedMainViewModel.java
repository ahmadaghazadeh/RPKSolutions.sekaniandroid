package com.example.sekini.ui.main;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.example.sekini.utils.base.SharedMainModel;


public class SharedMainViewModel extends ViewModel{

    public MutableLiveData<SharedMainModel> getModel() {
        return model;
    }

    private MutableLiveData<SharedMainModel> model = new MutableLiveData<>();

    public void openDrawer(){
        SharedMainModel sharedMainModel=new SharedMainModel();
        sharedMainModel.setOpenDrawer(true);
        model.setValue(sharedMainModel);
    }

}
