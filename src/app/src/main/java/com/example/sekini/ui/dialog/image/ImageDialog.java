package com.example.sekini.ui.dialog.image;

import android.app.Dialog;
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
import android.view.Window;

import com.example.sekini.BR;
import com.example.sekini.R;
import com.example.sekini.databinding.DialogCorrectBinding;
import com.example.sekini.databinding.DialogImageBinding;
import com.example.sekini.utils.base.dialog.BaseDialog;
import com.example.sekini.utils.base.dialog.IDialogDismiss;

import javax.inject.Inject;

public class ImageDialog extends BaseDialog<DialogImageBinding, ImageDialogViewModel> implements IImageDialogNavigator {

    private static final String BYTES = "BYTES";
    private static final String TITLE = "TITLE";
    public static String tag = "ImageDialog";
    byte[] bytes;
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
        if (dialogDismiss != null)
            dialogDismiss.onDismiss();
    }

    public static ImageDialog newInstance(byte[] bytes, String title) {
        ImageDialog fragment = new ImageDialog();
        Bundle bundle = new Bundle();
        bundle.putByteArray(BYTES, bytes);
        bundle.putString(TITLE, title);
        fragment.setArguments(bundle);
        return fragment;
    }

    public static void show(FragmentManager fm, byte[] bytes, String title, IDialogDismiss dialogDismiss) {
        ImageDialog fragment = new ImageDialog();
        Bundle bundle = new Bundle();
        bundle.putByteArray(BYTES, bytes);
        bundle.putString(TITLE, title);
        fragment.setArguments(bundle);
        fragment.setDialogDismiss(dialogDismiss);
        fragment.show(fm, tag);
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            bytes = getArguments().getByteArray(BYTES);
            title = getArguments().getString(TITLE);
        }
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        mViewModel.setNavigator(this);
        mViewModel.init(bytes, title);
        return view;
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        return dialog;
    }


    @Override
    public ImageDialogViewModel getViewModel() {
        return ViewModelProviders.of(this, factory).get(ImageDialogViewModel.class);
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.dialog_image;
    }


}
