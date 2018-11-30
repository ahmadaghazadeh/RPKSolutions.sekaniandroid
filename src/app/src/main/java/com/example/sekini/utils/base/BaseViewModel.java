package com.example.sekini.utils.base;

import android.arch.lifecycle.ViewModel;
import android.support.annotation.StringRes;

import com.example.sekini.utils.base.activity.IActivityNavigator;
import com.example.sekini.utils.base.dialog.YesNoDialog.YesNoDialog;
import com.example.sekini.utils.common.OnProgressUpdate;
import com.example.sekini.utils.common.RunnableIn;
import com.example.sekini.utils.common.RunnableMethod;
import com.example.sekini.utils.common.RunnableModel;
import com.example.sekini.utils.common.SimpleAsyncTask;

import java.lang.ref.WeakReference;


public abstract class BaseViewModel<N extends IActivityNavigator> extends ViewModel {

    private WeakReference<N> mNavigator;

    public N getNavigator() {
        return mNavigator.get();
    }

    public void setNavigator(N navigator) {
        this.mNavigator = new WeakReference<>(navigator);
    }

    OnProgressUpdate onProgressUpdate = new OnProgressUpdate() {
        @Override
        public void onProgressUpdate(String message) {
            getNavigator().setProgress(message);
        }

        @Override
        public void onProgressUpdate(String title, String message) {
            getNavigator().setProgress(title, message);
        }

        @Override
        public void onProgressUpdate(String title, String message, int count, int max) {
            getNavigator().setProgress(title, message, count, max);
        }

        @Override
        public void onProgressUpdate(String message, int count, int max) {
            getNavigator().setProgress(message, count, max);
        }
    };

    public void runDialogAsyncTask(RunnableMethod method, RunnableIn post) {

        RunnableMethod pre = (param, onProgressUpdate1) -> {
            getNavigator().showProgress(false);
            return null;
        };

        RunnableIn<RunnableModel> postInternal = (param) -> {
            if (param.getException() != null) {
                getNavigator().handleError(param.getException());
            }
            if (post != null) {
                post.run(param);
            }
            getNavigator().hideProgress();
        };


        SimpleAsyncTask simpleAsyncTask = new SimpleAsyncTask(pre, method, postInternal, onProgressUpdate);
        simpleAsyncTask.execute();
    }

    public void runAsyncTask(RunnableMethod method, RunnableIn post) {
        RunnableIn<RunnableModel> postInternal = (param) -> {
            if (param.getException() != null) {
                getNavigator().toast(param.getException().toString());
            }
            if (post != null) {
                post.run(param);
            }
        };

        SimpleAsyncTask simpleAsyncTask = new SimpleAsyncTask(null, method, postInternal, onProgressUpdate);
        simpleAsyncTask.execute();
    }

    public void showYesNoDialog(String title, String message, Runnable okRun, Runnable noRun) {
        getNavigator().showYesNoDialog(title, message, okRun, noRun);
    }

    public void showYesNoDialog(String title, String message, Runnable okRun) {
        getNavigator().showYesNoDialog(title, message, okRun, null);
    }

    public void showYesNoDialog(@StringRes int title, @StringRes int message, Runnable okRun, Runnable noRun) {
        getNavigator().showYesNoDialog(title, message, okRun, noRun);
    }

    public void showYesNoDialog(@StringRes int title, @StringRes int message, Runnable okRun) {
        getNavigator().showYesNoDialog(title, message, okRun, null);
    }

    public void showYesNoNeutralDialog(@StringRes int title, @StringRes int message,
                                       @StringRes int yesCaption, @StringRes int noCaption,
                                       @StringRes int neutralCaption, Runnable okRun, Runnable noRun, Runnable neutralRun) {
        getNavigator().showYesNoNeutralDialog(title, message, yesCaption, noCaption, neutralCaption, okRun, noRun, neutralRun);
    }


}
