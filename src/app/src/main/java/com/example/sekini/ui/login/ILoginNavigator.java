package com.example.sekini.ui.login;


import com.example.sekini.utils.base.fragment.IFragmentNavigator;

public interface ILoginNavigator extends IFragmentNavigator {

    void loginSuccessful();
    void refreshMenu();
}
