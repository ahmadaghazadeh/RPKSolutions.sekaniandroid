package com.example.sekini.utils.base.dialog;

import android.arch.lifecycle.ViewModel;

import com.example.sekini.utils.common.OnProgressUpdate;
import com.example.sekini.utils.common.RunnableIn;
import com.example.sekini.utils.common.RunnableMethod;
import com.example.sekini.utils.common.RunnableModel;
import com.example.sekini.utils.common.SimpleAsyncTask;

import java.lang.ref.WeakReference;


public abstract class DialogBaseViewModel<N extends IDialogNavigator> extends ViewModel {

    private WeakReference<N> mNavigator;

    public N getNavigator() {
        return mNavigator.get();
    }

    public void setNavigator(N navigator) {
        this.mNavigator = new WeakReference<>(navigator);
    }

    OnProgressUpdate onProgressUpdate=new OnProgressUpdate() {
        @Override
        public void onProgressUpdate(String message) {
            getNavigator().setProgress(message);
        }

        @Override
        public void onProgressUpdate(String title, String message) {
            getNavigator().setProgress(title,message);
        }

        @Override
        public void onProgressUpdate(String title, String message, int count, int max) {
            getNavigator().setProgress(title,message,count,max);
        }

        @Override
        public void onProgressUpdate(String message, int count, int max) {
            getNavigator().setProgress(message,count,max);
        }
    };

    public void runDialogAsyncTask(RunnableMethod method, RunnableIn post) {

        RunnableMethod pre = (param, onProgressUpdate) -> {
            getNavigator().showProgress(false);
            return null;
        };

        RunnableIn<RunnableModel> postInternal = (param) -> {
            if (param.getException() != null) {
                getNavigator().handleError(param.getException());
            }
            if(post!=null){
                post.run(param);
            }
            getNavigator().hideProgress();
        };

        SimpleAsyncTask simpleAsyncTask = new SimpleAsyncTask(pre, method, postInternal,onProgressUpdate);
        simpleAsyncTask.execute();
    }

    public void runAsyncTask(RunnableMethod method, RunnableIn post) {
        RunnableIn<RunnableModel> postInternal = (param) -> {
            if (param.getException() != null) {
                getNavigator().toast(param.getException().toString());
            }
            if(post!=null){
                post.run(param);
            }
        };

        SimpleAsyncTask simpleAsyncTask = new SimpleAsyncTask(null, method, postInternal,onProgressUpdate);
        simpleAsyncTask.execute();
    }


}
