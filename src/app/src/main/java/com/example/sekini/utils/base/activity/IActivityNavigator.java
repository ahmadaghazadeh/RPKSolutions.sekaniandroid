package com.example.sekini.utils.base.activity;

import android.support.annotation.StringRes;

import com.example.sekini.utils.base.INavigator;

public interface IActivityNavigator extends INavigator {


    void snackBar(String title,String btnCaption,Runnable runnable);
}
