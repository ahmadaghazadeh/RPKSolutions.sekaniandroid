package com.example.sekini.utils.base;

import android.support.annotation.StringRes;
import android.support.v7.widget.Toolbar;

public interface INavigator {

    void handleError(Throwable throwable);

    void showProgress(Boolean isIndeterminate);
    void setProgress(String title);
    void setProgress(String title,String message);
    void setProgress(String title,String message, int progress,int max);
    void setProgress(String message, int progress,int max);
    void showYesNoDialog(String title,String message,  Runnable okRun, Runnable cancelRun);
    void showYesNoDialog(@StringRes int title, @StringRes int  message, Runnable okRun, Runnable cancelRun);

    void hideProgress();

    void toast(@StringRes int resId);

    void toast(String string);

    void showYesNoNeutralDialog(int title, int message, int yesCaption, int noCaption,
                                int neutralCaption, Runnable okRun, Runnable noRun,Runnable neutralRun);


}
