package com.example.sekini.utils.base.fragment;


import com.example.sekini.utils.base.INavigator;

public interface IFragmentNavigator extends INavigator {

    void setToolbarProgressBar(boolean isIndeterminate, int progress);
}
