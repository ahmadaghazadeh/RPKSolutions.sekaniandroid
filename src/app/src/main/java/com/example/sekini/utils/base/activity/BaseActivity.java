package com.example.sekini.utils.base.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.graphics.drawable.ColorDrawable;
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
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import com.example.sekini.R;
import com.example.sekini.ui.dialog.loading.LoadingDialog;
import com.example.sekini.ui.dictionary.DictionaryFragment;
import com.example.sekini.ui.games.game1.Game1Fragment;
import com.example.sekini.ui.games.game2.Game2Fragment;
import com.example.sekini.ui.word.generic.GenericFragment;
import com.example.sekini.ui.word.possessednoun.PossessedNounFragment;
import com.example.sekini.ui.word.regularverb.RegularVerbFragment;
import com.example.sekini.utils.base.BaseViewModel;
import com.example.sekini.utils.base.dialog.IDialogDismiss;
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
    public static final String PROMPT_DIALOG = "PROMPT_DIALOG";

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
    private LoadingDialog loadingDialog;

    public abstract V getViewModel();

    public abstract int getBindingVariable();

    @LayoutRes
    public abstract int getLayoutId();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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

    public void setToolbarColor(@ColorRes int color) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources()
                    .getColor(color)));
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


    public void showProgress(Boolean isIndeterminate) {
        progress = new ProgressDialog(this);
        progress.setCancelable(false);

        if (isIndeterminate) {
            progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        } else {
            progress.setIndeterminate(isIndeterminate);
            progress.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        }
        progress.setMessage(getString(R.string.please_wait));
        progress.show();
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
        configToolbar(fragment);

        for (int entry = 0; entry < fragmentManager.getBackStackEntryCount(); entry++) {
            fragmentManager.popBackStack();
        }

        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(container, fragment, fragment.getClass().toString());
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void replaceFragment(@IdRes int resId, Fragment fragment) {
        replaceFragment(getSupportFragmentManager(),
                resId, fragment);
    }


    public void addFragment(FragmentManager fragmentManager, int container, Fragment fragment) {

        configToolbar(fragment);
        if (!(fragment instanceof GenericFragment ||
                fragment instanceof PossessedNounFragment ||
                fragment instanceof RegularVerbFragment)) {
            for (int entry = 0; entry < fragmentManager.getBackStackEntryCount(); entry++) {
                fragmentManager.popBackStack();
            }
        }


        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(container, fragment, fragment.getClass().toString());
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void addFragment(@IdRes int resId, Fragment fragment) {
        addFragment(getSupportFragmentManager(),
                resId, fragment);
    }

    public void addFragment(Fragment fragment) {
        addFragment(getSupportFragmentManager(),
                R.id.fragment_container, fragment);
    }

    @Override
    public void onBackPressed() {
        Fragment f = getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {

            if ((f instanceof Game2Fragment | f instanceof Game1Fragment)) {
                showYesNoDialog(getString(R.string.attention)
                        , getString(R.string.exit_prompt),
                        () -> getSupportFragmentManager().popBackStackImmediate(), null);
            } else {
                getSupportFragmentManager().popBackStackImmediate();
            }
            f = getSupportFragmentManager().findFragmentById(R.id.fragment_container);
            configToolbar(f);
        } else {
            super.onBackPressed();
        }
    }


    protected void configToolbar(Fragment f) {
        if (f instanceof DictionaryFragment) {
            getToolbar().setVisibility(View.GONE);
        } else {
            getToolbar().setVisibility(View.VISIBLE);
        }
        if (f instanceof Game2Fragment) {
            setSystemBarColor(R.color.toolbar_game2);
            setToolbarColor(R.color.toolbar_game2);
        } else {
            setSystemBarColor(R.color.colorPrimary);
            setToolbarColor(R.color.colorPrimary);
        }
    }

    public void popBackStack() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager().popBackStack();
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

    public void showLoadingDialog(int resId, String title) {
        loadingDialog = LoadingDialog.newInstance(resId, title);
        loadingDialog.setCancelable(false);
        loadingDialog.show(getSupportFragmentManager(), LoadingDialog.tag);
    }

    public void dismissLoadingDialog() {
        if (loadingDialog != null)
            loadingDialog.dismiss();
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

    public void snackBar(String title) {
        Snackbar snackbar = Snackbar.make(mViewDataBinding.getRoot(), title, Snackbar.LENGTH_LONG);
        snackbar.show();
    }

    public void snackBar(@StringRes int title) {
        Snackbar snackbar = Snackbar.make(mViewDataBinding.getRoot(), title, Snackbar.LENGTH_LONG);
        snackbar.show();
    }

    public Toolbar getToolbar() {

        return findViewById(R.id.toolbar);
    }

    public void showYesNoDialog(@StringRes int title, @StringRes int message,
                                @StringRes int okCaption, @StringRes int cancelCaption
            , Runnable okRun, Runnable cancelRun) {
        YesNoDialog yesNoDialog = YesNoDialog.newInstance(getString(title), getString(message),
                getString(okCaption), getString(cancelCaption));
        yesNoDialog.setOkRunnable(okRun);

        yesNoDialog.setCancelRunnable(cancelRun);
        yesNoDialog.show(getSupportFragmentManager(), YES_NO_DIALOG);
    }

    public void showPromptDialog(@StringRes int title, @StringRes int message
            , IDialogDismiss dialogDismiss) {
        PromptDialog promptDialog = PromptDialog.newInstance(getString(title), getString(message) );
        promptDialog.setDialogDismiss(dialogDismiss);
        promptDialog.show(getSupportFragmentManager(), PROMPT_DIALOG);
    }

    public void setToolbarTitle(String title) {
        getToolbar().setTitle(title);
    }
}
