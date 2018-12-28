package com.example.sekini.ui.main;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.example.sekini.utils.base.SharedMainModel;


public class SharedMainViewModel extends ViewModel {

    public MutableLiveData<SharedMainModel> getModel() {
        return model;
    }

    private MutableLiveData<SharedMainModel> model = new MutableLiveData<>();
    private MutableLiveData<Integer> menu = new MutableLiveData<>();

    public MutableLiveData<Boolean> getRefreshMenu() {
        return refreshMenu;
    }

    public void refreshMenu() {
        this.refreshMenu.setValue(true);
    }

    private MutableLiveData<Boolean> refreshMenu = new MutableLiveData<>();

    public void openDrawer() {
        SharedMainModel sharedMainModel = new SharedMainModel();
        sharedMainModel.setOpenDrawer(true);
        model.setValue(sharedMainModel);
    }

    public MutableLiveData<Integer> getMenu() {
        return menu;
    }

    public void setMenu(Integer menu) {
        this.menu.setValue(menu);
    }
}
