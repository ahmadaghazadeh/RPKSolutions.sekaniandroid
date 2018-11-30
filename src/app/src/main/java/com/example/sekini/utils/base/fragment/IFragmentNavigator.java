package com.example.sekini.utils.base.fragment;


import com.example.sekini.utils.base.INavigator;

public interface IFragmentNavigator extends INavigator {
    void snackBar(String title,String btnCaption,Runnable runnable);
}
