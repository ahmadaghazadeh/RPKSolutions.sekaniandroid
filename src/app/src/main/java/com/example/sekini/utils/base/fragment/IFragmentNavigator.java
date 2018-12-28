package com.example.sekini.utils.base.fragment;


import android.support.annotation.IdRes;
import android.support.annotation.StringRes;

import com.example.sekini.utils.base.INavigator;
import com.example.sekini.utils.base.dialog.IDialogDismiss;

public interface IFragmentNavigator extends INavigator {
    void snackBar(String title,String btnCaption,Runnable runnable);
    void snackBar(String title);
    void snackBar(@StringRes int title);
    void showLoadingDialog(int resId,String title);
    void dismissLoadingDialog() ;
    void showPromptDialog(@StringRes int titleId, @StringRes int messageId, IDialogDismiss dialogDismiss);
}
