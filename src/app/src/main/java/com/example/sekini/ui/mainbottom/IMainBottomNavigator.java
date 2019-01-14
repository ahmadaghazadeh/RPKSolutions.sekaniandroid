package com.example.sekini.ui.mainbottom;


import com.example.sekini.utils.base.activity.IActivityNavigator;

public interface IMainBottomNavigator extends IActivityNavigator {

    void startSyncService();

    void login();
}
