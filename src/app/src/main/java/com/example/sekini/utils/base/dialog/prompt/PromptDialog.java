package com.example.sekini.utils.base.dialog.prompt;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.view.View;

import com.example.sekini.R;
import com.example.sekini.utils.base.dialog.IDialogDismiss;

import dagger.android.support.DaggerAppCompatDialogFragment;

public class PromptDialog extends DaggerAppCompatDialogFragment {

    private static final String MESSAGE = "MESSAGE";
    private static final String TITLE = "TITLE";
    private static final String tag = "PromptDialog";
    String title;
    String message;
    private Context context;

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

    public static PromptDialog newInstance(String title, String message) {
        PromptDialog dialog = new PromptDialog();
        Bundle bundle = new Bundle();
        bundle.putString(TITLE, title);
        bundle.putString(MESSAGE, message);
        dialog.setArguments(bundle);
        return dialog;
    }

    public static void show(FragmentManager fm, String title, String message, IDialogDismiss dialogDismiss) {
        PromptDialog dialog = new PromptDialog();
        Bundle bundle = new Bundle();
        bundle.putString(TITLE, title);
        bundle.putString(MESSAGE, message);
        dialog.setArguments(bundle);
        dialog.setDialogDismiss(dialogDismiss);
        dialog.show(fm,tag);
    }

    public PromptDialog() {
        // Empty constructor required for DialogFragment
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            title = getArguments().getString(TITLE);
            message = getArguments().getString(MESSAGE);
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog alertDialog = new AlertDialog.Builder(context)
                .setNegativeButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setMessage(message)
                .setTitle(title)
                .create();
        alertDialog.setIcon(R.mipmap.ic_launcher_round);

        return alertDialog;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void show(FragmentManager manager, String tag) {
        FragmentTransaction ft = manager.beginTransaction();
        ft.add(this, tag);
        ft.commit();

    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context=context;
    }
}
