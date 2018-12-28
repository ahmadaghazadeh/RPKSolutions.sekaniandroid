package com.example.sekini.utils.base.dialog.YesNoDialog;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DialogTitle;
import android.view.View;

import com.example.sekini.R;

import dagger.android.support.DaggerAppCompatDialogFragment;
import okhttp3.internal.tls.OkHostnameVerifier;

public class YesNoDialog extends DaggerAppCompatDialogFragment {

    private static final String MESSAGE = "MESSAGE";
    private static final String TITLE = "TITLE";
    private static final String OK_CAPTION = "OK_CAPTION";
    private static final String CANCEL_CAPTION = "CANCEL_CAPTION";
    String title;
    String message;
    String cancelCaption;
    String okCaption;
    private Context context;
    private Runnable okRunnable;
    private Runnable cancelRunnable;

    public YesNoDialog() {
        // Empty constructor required for DialogFragment
    }


    public static YesNoDialog newInstance(String title, String message) {
        YesNoDialog dialog = new YesNoDialog();
        Bundle bundle = new Bundle();
        bundle.putString(TITLE, title);
        bundle.putString(MESSAGE, message);
        dialog.setArguments(bundle);
        return dialog;
    }

    public static YesNoDialog newInstance(String title, String message,String okCaption,String cancelCaption) {
        YesNoDialog dialog = new YesNoDialog();
        Bundle bundle = new Bundle();
        bundle.putString(TITLE, title);
        bundle.putString(MESSAGE, message);
        bundle.putString(OK_CAPTION, okCaption);
        bundle.putString(CANCEL_CAPTION, cancelCaption);
        dialog.setArguments(bundle);
        return dialog;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            title = getArguments().getString(TITLE);
            message = getArguments().getString(MESSAGE);
            okCaption = getArguments().getString(CANCEL_CAPTION,getString(R.string.ok));
            cancelCaption = getArguments().getString(OK_CAPTION,getString(R.string.cancel));
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog alertDialog = new AlertDialog.Builder(context)
                .setNegativeButton(cancelCaption, (dialog, which) -> {
                    if (cancelRunnable != null)
                        cancelRunnable.run();
                    dialog.dismiss();
                })
                .setMessage(message)
                .setPositiveButton(okCaption, (dialog, which) -> {
                    if (okRunnable != null)
                        okRunnable.run();
                    dialog.dismiss();
                })
                .create();
        alertDialog.setIcon(R.mipmap.ic_launcher_round);

        @SuppressLint("RestrictedApi")
        DialogTitle titleView = new DialogTitle(context);
        titleView.setText(title);
        titleView.setPadding(32,
                32,
                32,
                16);
        alertDialog.setCustomTitle(titleView);
        return alertDialog;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void show(FragmentManager manager, String tag) {
        FragmentTransaction ft = manager.beginTransaction();
        show(ft, tag);
    }

    public void setOkRunnable(Runnable okRunnable) {
        this.okRunnable = okRunnable;
    }

    public void setCancelRunnable(Runnable cancelRunnable) {
        this.cancelRunnable = cancelRunnable;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }
}
