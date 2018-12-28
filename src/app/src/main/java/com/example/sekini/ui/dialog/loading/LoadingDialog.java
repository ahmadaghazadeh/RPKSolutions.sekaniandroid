package com.example.sekini.ui.dialog.loading;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sekini.BR;
import com.example.sekini.R;
import com.example.sekini.databinding.DialogCorrectBinding;
import com.example.sekini.utils.base.dialog.BaseDialog;
import com.example.sekini.utils.base.dialog.IDialogDismiss;

import javax.inject.Inject;

public class LoadingDialog extends BaseDialog<DialogCorrectBinding, LoadingDialogViewModel> implements ILoadingDialogNavigator {

    private static final String RES_ID = "RES_ID";
    private static final String TITLE = "TITLE";
   public static String tag="ImageDialog";
      int  resId;
      String title;

    @Inject
    ViewModelProvider.Factory factory;

    private IDialogDismiss dialogDismiss;

    public void setDialogDismiss(IDialogDismiss dialogDismiss) {
        this.dialogDismiss = dialogDismiss;
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        if(dialogDismiss!=null)
            dialogDismiss.onDismiss();
    }

    public static LoadingDialog newInstance(int resId,String title) {
        LoadingDialog fragment = new LoadingDialog();
        Bundle bundle = new Bundle();
        bundle.putInt(RES_ID, resId);
        bundle.putString(TITLE, title);
        fragment.setArguments(bundle);
        return fragment;
    }

    public static void show(FragmentManager fm, int resId,String title,IDialogDismiss dialogDismiss) {
        LoadingDialog fragment = new LoadingDialog();
        Bundle bundle = new Bundle();
        bundle.putInt(RES_ID, resId);
        bundle.putString(TITLE, title);
        fragment.setArguments(bundle);
        fragment.setDialogDismiss(dialogDismiss);
        fragment.show(fm,tag);
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            resId = getArguments().getInt(RES_ID);
            title = getArguments().getString(TITLE);
        }
    }



    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        mViewModel.setNavigator(this);
        mViewModel.init(resId,title);
        return view;
    }


    @Override
    public LoadingDialogViewModel getViewModel() {
        return ViewModelProviders.of(this, factory).get(LoadingDialogViewModel.class);
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.dialog_loading;
    }


}
