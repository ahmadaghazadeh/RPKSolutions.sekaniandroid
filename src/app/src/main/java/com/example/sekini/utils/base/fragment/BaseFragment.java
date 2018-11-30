package com.example.sekini.utils.base.fragment;

import android.app.ProgressDialog;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatDelegate;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import com.example.sekini.R;
import com.example.sekini.ui.main.SharedMainViewModel;
import com.example.sekini.utils.base.activity.BaseActivity;
import com.example.sekini.utils.base.dialog.YesNoDialog.YesNoDialog;
import com.example.sekini.utils.base.dialog.YesNoNeutral.YesNoNeutralDialog;
import com.example.sekini.utils.common.CommonUtils;
import com.example.sekini.utils.exception.HandleException;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

import static com.example.sekini.utils.base.activity.BaseActivity.YES_NEUTRAL_NO_DIALOG;
import static com.example.sekini.utils.base.activity.BaseActivity.YES_NO_DIALOG;

public abstract class BaseFragment<T extends ViewDataBinding, V extends FragmentBaseViewModel> extends DaggerFragment {

    public static final String ERROR_DIALOG = "ERROR_DIALOG";

    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    protected T mViewDataBinding;
    protected V mViewModel;

    SharedMainViewModel sharedMainViewModel;

    BaseActivity mActivity;

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
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        progress = new ProgressDialog(getActivity());
        progress.setCancelable(false);
        sharedMainViewModel = ViewModelProviders.of(getBaseActivity()).get(SharedMainViewModel.class);
    }

    public BaseActivity getBaseActivity() {
        return mActivity;
    }



    public ActionBar getActionBar(){
        return getBaseActivity().getSupportActionBar();
    }

    public void handleError(Throwable throwable) {
        handleException.newException(throwable);
        FragmentManager manager = mActivity.getSupportFragmentManager();
        Fragment frag = manager.findFragmentByTag(ERROR_DIALOG);
        if (frag != null) {
            manager.beginTransaction().remove(frag).commit();
        }
        //TO DO show error message
    }


    public void toast(int resId) {
        commonUtils.toast(resId);
    }

    public void toast(String string) {
        commonUtils.toast(string);
    }


    public void showProgress(Boolean flag) {
        if (flag) {
            progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        }
        progress.setIndeterminate(flag);
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

    public void hideProgress() {
        if (progress.isShowing())
            progress.dismiss();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        try {
            mActivity = (BaseActivity) getActivity();
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
            this.mActivity = (BaseActivity) context;
        }
    }

    @Override
    public void onDetach() {
        mActivity = null;
        super.onDetach();
    }



    public void hideKeyboard() {
        View view = getBaseActivity().getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getBaseActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm != null) {
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        }
    }

    public void showYesNoDialog(String title, String message, Runnable okRun, Runnable cancelRun) {
        YesNoDialog yesNoDialog = YesNoDialog.newInstance(title, message);
        yesNoDialog.setOkRunnable(okRun);
        yesNoDialog.setCancelRunnable(cancelRun);
        yesNoDialog.show(getBaseActivity().getSupportFragmentManager(), YES_NO_DIALOG);

    }

    public void showYesNoDialog(@StringRes int title, @StringRes int message, Runnable okRun, Runnable cancelRun) {
        YesNoDialog yesNoDialog = YesNoDialog.newInstance(getString(title), getString(message));
        yesNoDialog.setOkRunnable(okRun);
        yesNoDialog.setCancelRunnable(cancelRun);
        yesNoDialog.show(getBaseActivity().getSupportFragmentManager(), YES_NO_DIALOG);

    }


    public void showYesNoNeutralDialog(int title, int message, int yesCaption, int noCaption,
                                       int neutralCaption, Runnable yesRun,
                                       Runnable neutralRun, Runnable noRun) {
        YesNoNeutralDialog yesNoNeutralDialog = YesNoNeutralDialog.newInstance(getString(title), getString(message),
                getString(yesCaption), getString(noCaption), getString(neutralCaption));
        yesNoNeutralDialog.setPositiveRunnable(yesRun);
        yesNoNeutralDialog.setNegativeRunnable(noRun);
        yesNoNeutralDialog.setNoRunnable(neutralRun);
        yesNoNeutralDialog.show(getBaseActivity().getSupportFragmentManager(), YES_NEUTRAL_NO_DIALOG);
    }

    public void snackBar(String title, String btnCaption, Runnable runnable) {
        Snackbar snackbar = Snackbar.make(mViewDataBinding.getRoot(), title, Snackbar.LENGTH_LONG)
                .setAction(btnCaption, view -> runnable.run());
        snackbar.show();
    }
}
