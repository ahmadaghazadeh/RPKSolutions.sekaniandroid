package com.example.sekini.utils.base.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.ColorRes;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatDelegate;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import com.example.sekini.R;
import com.example.sekini.utils.base.BaseViewModel;
import com.example.sekini.utils.base.dialog.YesNoNeutral.YesNoNeutralDialog;
import com.example.sekini.utils.base.dialog.YesNoDialog.YesNoDialog;
import com.example.sekini.utils.base.dialog.prompt.PromptDialog;
import com.example.sekini.utils.common.CommonUtils;
import com.example.sekini.utils.exception.HandleException;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;


public abstract class BaseActivity<T extends ViewDataBinding, V extends BaseViewModel> extends DaggerAppCompatActivity {

    public static final int MULTIPLE_PERMISSIONS = 101;
    public static final String YES_NO_DIALOG = "YES_NO_DIALOG";
    public static final String YES_NEUTRAL_NO_DIALOG = "YES_NEUTRAL_NO_DIALOG";
    public static final String ERROR_DIALOG = "ERROR_DIALOG";

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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        progress = new ProgressDialog(this);
        progress.setCancelable(false);
        progress.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        performDataBinding();
        setSystemBarColor(R.color.colorPrimary);
    }

    public void setSystemBarColor(@ColorRes int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(getResources().getColor(color));
        }
    }


    public void handleError(Throwable throwable) {
        hideProgress();
        handleException.newException(throwable);
        FragmentManager manager = getSupportFragmentManager();
        Fragment frag = manager.findFragmentByTag(ERROR_DIALOG);
        if (frag != null) {
            manager.beginTransaction().remove(frag).commit();
        }
        PromptDialog prompt = PromptDialog.newInstance(handleException.getUserTitle(), handleException.getUserMessage());
        prompt.show(manager, ERROR_DIALOG);
    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    private void performDataBinding() {
        mViewDataBinding = DataBindingUtil.setContentView(this, getLayoutId());

        this.mViewModel = mViewModel == null ? getViewModel() : mViewModel;
        mViewDataBinding.setVariable(getBindingVariable(), mViewModel);
        mViewDataBinding.setLifecycleOwner(this);

    }

    public void toast(int resId) {
        commonUtils.toast(resId);
    }

    public void toast(String string) {
        commonUtils.toast(string);
    }


    public void showProgress(Boolean flag) {
        if (progress != null) {
            progress.setIndeterminate(flag);
            progress.setMessage(getString(R.string.please_wait));
            progress.show();
        }
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        hideProgress();
    }

    public void hideProgress() {
        if (progress != null) {
            Activity activity = progress.getOwnerActivity();
            if (activity != null && !activity.isFinishing() && progress.isShowing()) {
                progress.dismiss();
            } else if (progress.isShowing()) {
                progress.dismiss();

            }
        }

    }

    public void finishActivity() {
        this.finish();
    }

    public void replaceFragment(FragmentManager fragmentManager, int container, Fragment fragment) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(container, fragment);
        transaction.addToBackStack(fragment.getClass().getName());
        transaction.commit();
    }

    public void changeFragment(@IdRes int resId, Fragment fragment) {
        replaceFragment(getSupportFragmentManager(),
                resId, fragment);
    }

    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() > 0) {
            getFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }

    public void hideKeyboard() {
        View view = getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm != null) {
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        }
    }

    public void showYesNoDialog(String title, String message, Runnable okRun, Runnable cancelRun) {
        YesNoDialog yesNoDialog = YesNoDialog.newInstance(title, message);
        yesNoDialog.setOkRunnable(okRun);
        yesNoDialog.setCancelRunnable(cancelRun);
        yesNoDialog.show(getSupportFragmentManager(), YES_NO_DIALOG);

    }

    public void showYesNoDialog(@StringRes int title, @StringRes int message, Runnable okRun, Runnable cancelRun) {
        YesNoDialog yesNoDialog = YesNoDialog.newInstance(getString(title), getString(message));
        yesNoDialog.setOkRunnable(okRun);
        yesNoDialog.setCancelRunnable(cancelRun);
        yesNoDialog.show(getSupportFragmentManager(), YES_NO_DIALOG);

    }


    public void showYesNoNeutralDialog(int title, int message, int yesCaption, int noCaption,
                                       int neutralCaption, Runnable yesRun,
                                       Runnable neutralRun, Runnable noRun) {
        YesNoNeutralDialog yesNoNeutralDialog = YesNoNeutralDialog.newInstance(getString(title), getString(message),
                getString(yesCaption), getString(noCaption), getString(neutralCaption));
        yesNoNeutralDialog.setPositiveRunnable(yesRun);
        yesNoNeutralDialog.setNegativeRunnable(noRun);
        yesNoNeutralDialog.setNoRunnable(neutralRun);
        yesNoNeutralDialog.show(getSupportFragmentManager(), YES_NEUTRAL_NO_DIALOG);
    }

    public void snackBar(String title, String btnCaption, Runnable runnable) {
        Snackbar snackbar = Snackbar.make(mViewDataBinding.getRoot(), title, Snackbar.LENGTH_LONG)
                .setAction(btnCaption, view -> runnable.run());
        snackbar.show();
    }
}
