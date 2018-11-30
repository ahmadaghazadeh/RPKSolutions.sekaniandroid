package com.example.sekini.utils.base.dialog;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDelegate;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sekini.R;
import com.example.sekini.utils.base.BaseViewModel;
import com.example.sekini.utils.base.activity.BaseActivity;
import com.example.sekini.utils.base.dialog.YesNoDialog.YesNoDialog;
import com.example.sekini.utils.base.dialog.YesNoNeutral.YesNoNeutralDialog;
import com.example.sekini.utils.common.CommonUtils;
import com.example.sekini.utils.exception.HandleException;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatDialogFragment;

import static com.example.sekini.utils.base.activity.BaseActivity.YES_NEUTRAL_NO_DIALOG;
import static com.example.sekini.utils.base.activity.BaseActivity.YES_NO_DIALOG;


public abstract class BaseDialog<T extends ViewDataBinding, V extends DialogBaseViewModel> extends DaggerAppCompatDialogFragment implements IDialogNavigator {


    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    protected T mViewDataBinding;
    protected V mViewModel;
    @Inject
    CommonUtils commonUtils;
    @Inject
    HandleException handleException;
    private ProgressDialog progress;

    public abstract V getViewModel();

    public abstract int getBindingVariable();

    @LayoutRes
    public abstract int getLayoutId();

    BaseActivity baseActivity;


    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {



        performDataBinding();
        return new AlertDialog.Builder(baseActivity)
                .setView(mViewDataBinding.getRoot())
                .create();
    }



    private void performDataBinding() {
        mViewDataBinding = DataBindingUtil.inflate(LayoutInflater.from(getContext()),
                getLayoutId(), null, false);
        this.mViewModel = mViewModel == null ? getViewModel() : mViewModel;
        mViewDataBinding.setVariable(getBindingVariable(), mViewModel);
        mViewDataBinding.setLifecycleOwner(this);
    }

    @Override
    public void handleError(Throwable throwable) {
        handleException.newException(throwable);
        toast(handleException.getUserMessage());
    }

    public void toast(int resId) {
        commonUtils.toast(resId);
    }

    public void toast(String string) {
        commonUtils.toast(string);
    }

    public void showProgress(Boolean flag) {
        if (progress == null) {
            FragmentActivity activity = getActivity();
            progress = new ProgressDialog(activity);
        }

        if (flag) {
            progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        }
        progress.setIndeterminate(flag);
        progress.setMessage(getString(R.string.please_wait));
        progress.show();
    }

    public void setProgress(String message, int pos) {
        progress.setMessage(message);
        progress.setProgress(pos);
    }

    public void hideProgress() {
        if (progress.isShowing())
            progress.dismiss();
    }

    public void dialogDismiss() {
        dismiss();
    }


    public void setProgress(String message) {
        if (progress != null) {
            progress.setMessage(message);
        }
    }

    public void setProgress(String title, String message) {
        if (progress != null) {
            progress.setMessage(message);
            progress.setTitle(title);
        }
    }

    public void setProgress(String message, int pos, int max) {
        if (progress != null) {
            progress.setMessage(message);
            progress.setProgress(pos);
            progress.setMax(max);
        }
    }

    public void setProgress(String title, String message, int pos, int max) {
        if (progress != null) {
            progress.setMessage(message);
            progress.setProgress(pos);
            progress.setMax(max);
            progress.setTitle(title);
        }
    }



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        try {
            mViewDataBinding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false);
            this.mViewModel = mViewModel == null ? getViewModel() : mViewModel;
            mViewDataBinding.setVariable(getBindingVariable(), mViewModel);
            mViewDataBinding.setLifecycleOwner(this);
        } catch (Exception ex) {
            ex.getMessage();
        }
        return mViewDataBinding.getRoot();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof BaseActivity) {
            this.baseActivity = (BaseActivity) context;
        }
    }

    @Override
    public void onDetach() {
        baseActivity = null;
        super.onDetach();
    }


    @Override
    public void show(FragmentManager manager, String tag) {
        FragmentTransaction ft = manager.beginTransaction();
        ft.add(this, tag);
        ft.commit();
    }


    public void showYesNoDialog(String title, String message, Runnable okRun, Runnable cancelRun) {
        YesNoDialog yesNoDialog = YesNoDialog.newInstance(title, message);
        yesNoDialog.setOkRunnable(okRun);
        yesNoDialog.setCancelRunnable(cancelRun);
        yesNoDialog.show(baseActivity.getSupportFragmentManager(), YES_NO_DIALOG);

    }

    public void showYesNoDialog(@StringRes int title, @StringRes int message, Runnable okRun, Runnable cancelRun) {
        YesNoDialog yesNoDialog = YesNoDialog.newInstance(getString(title), getString(message));
        yesNoDialog.setOkRunnable(okRun);
        yesNoDialog.setCancelRunnable(cancelRun);
        yesNoDialog.show(baseActivity.getSupportFragmentManager(), YES_NO_DIALOG);

    }


    public void showYesNoNeutralDialog(int title, int message, int yesCaption, int noCaption,
                                       int neutralCaption, Runnable yesRun,
                                       Runnable neutralRun, Runnable noRun) {
        YesNoNeutralDialog yesNoNeutralDialog = YesNoNeutralDialog.newInstance(getString(title), getString(message),
                getString(yesCaption), getString(noCaption), getString(neutralCaption));
        yesNoNeutralDialog.setPositiveRunnable(yesRun);
        yesNoNeutralDialog.setNegativeRunnable(noRun);
        yesNoNeutralDialog.setNoRunnable(neutralRun);
        yesNoNeutralDialog.show(baseActivity.getSupportFragmentManager(), YES_NEUTRAL_NO_DIALOG);
    }

}
