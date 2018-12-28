package com.example.sekini.utils.base.activity;

import android.support.annotation.StringRes;

import com.example.sekini.utils.base.INavigator;
import com.example.sekini.utils.base.dialog.IDialogDismiss;

public interface IActivityNavigator extends INavigator {


    void snackBar(String title,String btnCaption,Runnable runnable);
    void snackBar(String title);
    void snackBar(@StringRes int title);
    void showLoadingDialog(int resId,String title);
    void showPromptDialog(@StringRes int titleId, @StringRes int messageId, IDialogDismiss dialogDismiss);
    void dismissLoadingDialog() ;
}
