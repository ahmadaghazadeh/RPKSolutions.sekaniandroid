package com.example.sekini.utils.base.dialog.YesNoNeutral;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.view.View;

import com.example.sekini.R;

import dagger.android.support.DaggerAppCompatDialogFragment;

public class YesNoNeutralDialog extends DaggerAppCompatDialogFragment {

    public YesNoNeutralDialog() {
        // Empty constructor required for DialogFragment
    }


    private static final String MESSAGE = "MESSAGE";
    private static final String TITLE = "TITLE";
    private static final String POSITIVE_CAPTION = "POSITIVE_CAPTION";
    private static final String NEUTRAL_CAPTION = "NEUTRAL_CAPTION";
    private static final String NEGATIVE_CAPTION = "NEGATIVE_CAPTION";
    String title;
    String message;
    String positiveCaption;
    String neutralCaption;
    String negativeCaption;
    private Activity activity;
    private Runnable positiveRunnable;
    private Runnable negativeRunnable;
    private Runnable neutralRunnable;


    public static YesNoNeutralDialog newInstance(String title, String message,
                                                 String positiveCaption, String neutralCaption, String negativeCaption) {
        YesNoNeutralDialog dialog = new YesNoNeutralDialog();
        Bundle bundle = new Bundle();
        bundle.putString(TITLE, title);
        bundle.putString(MESSAGE, message);
        bundle.putString(POSITIVE_CAPTION, positiveCaption);
        bundle.putString(NEUTRAL_CAPTION, neutralCaption);
        bundle.putString(NEGATIVE_CAPTION, negativeCaption);
        dialog.setArguments(bundle);
        return dialog;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            title = getArguments().getString(TITLE);
            message = getArguments().getString(MESSAGE);
            positiveCaption = getArguments().getString(POSITIVE_CAPTION);
            neutralCaption = getArguments().getString(NEUTRAL_CAPTION);
            negativeCaption = getArguments().getString(NEGATIVE_CAPTION);
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(activity);
        alertDialogBuilder.setNegativeButton(negativeCaption, (dialog, which) -> {
            if (negativeRunnable != null)
                negativeRunnable.run();
            dialog.dismiss();
        });
        alertDialogBuilder.setMessage(message);
        alertDialogBuilder.setPositiveButton(positiveCaption, (dialog, which) -> {
            if (positiveRunnable != null)
                positiveRunnable.run();
            dialog.dismiss();
        });
        alertDialogBuilder.setNeutralButton(neutralCaption, (dialog, which) -> {
            if (neutralRunnable != null)
                neutralRunnable.run();
            dialog.dismiss();
        });
        alertDialogBuilder.setTitle(title);
        alertDialogBuilder.setIcon(R.mipmap.ic_launcher_round);

        return alertDialogBuilder.create();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

//    @Override
//    public void show(FragmentManager manager, String tag) {
//        FragmentTransaction ft = manager.beginTransaction();
//        ft.add(this, tag);
//        ft.commit();
//
//    }

    public void setPositiveRunnable(Runnable positiveRunnable) {

        this.positiveRunnable = positiveRunnable;
    }

    public void setNegativeRunnable(Runnable negativeRunnable) {
        this.negativeRunnable = negativeRunnable;
    }

    public void setNoRunnable(Runnable noRunnable) {
        this.neutralRunnable = noRunnable;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.activity = (Activity) context;
    }
}
