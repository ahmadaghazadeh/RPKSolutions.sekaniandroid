package com.example.sekini.utils.base;

import android.support.annotation.StringRes;

public interface INavigator {

    void handleError(Throwable throwable);

    void showProgress(Boolean flag);

    void setProgress(String title);
    void setProgress(String title,String message);
    void setProgress(String title,String message, int progress,int max);
    void setProgress(String message, int progress,int max);
    void showYesNoDialog(String title,String message,  Runnable okRun, Runnable cancelRun);
    void showYesNoDialog(@StringRes int title, @StringRes int  message, Runnable okRun, Runnable cancelRun);

    void hideProgress();

    void toast(@StringRes int resId);

    void toast(String string);

    void hideKeyboard();

    void showYesNoNeutralDialog(int title, int message, int yesCaption, int noCaption,
                                int neutralCaption, Runnable okRun, Runnable noRun,Runnable neutralRun);
    void snackBar(String title,String btnCaption,Runnable runnable);

}
