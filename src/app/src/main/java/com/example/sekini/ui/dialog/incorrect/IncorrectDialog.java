package com.example.sekini.ui.dialog.incorrect;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sekini.BR;
import com.example.sekini.R;
import com.example.sekini.databinding.DialogIncorrectBinding;
import com.example.sekini.utils.base.dialog.BaseDialog;
import com.example.sekini.utils.base.dialog.IDialogDismiss;

import javax.inject.Inject;

public class IncorrectDialog extends BaseDialog<DialogIncorrectBinding, IncorrectDialogViewModel> implements IIncorrectDialogNavigator {

    private static final String ENGLISH_WORD = "ENGLISH_WORD";
    private static final String SEKANI_WORD = "SEKANI_WORD";
    private static final String AUDIO = "AUDIO";
    String englishWord;
    String sekaniWord;
    byte[] audio;
    static String tag="CorrectDialog";



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

    @Inject
    ViewModelProvider.Factory factory;

    public static IncorrectDialog newInstance(String englishWord, String sekaniWord, byte[] audio) {
        IncorrectDialog fragment = new IncorrectDialog();
        Bundle bundle = new Bundle();
        bundle.putString(ENGLISH_WORD, englishWord);
        bundle.putString(SEKANI_WORD, sekaniWord);
        bundle.putByteArray(AUDIO, audio);

        fragment.setArguments(bundle);
        return fragment;
    }

    public static void show(FragmentManager fm, String englishWord, String sekaniWord, byte[] audio,IDialogDismiss dialogDismiss) {
        IncorrectDialog fragment = new IncorrectDialog();
        Bundle bundle = new Bundle();
        bundle.putString(ENGLISH_WORD, englishWord);
        bundle.putString(SEKANI_WORD, sekaniWord);
        bundle.putByteArray(AUDIO, audio);
        fragment.setArguments(bundle);
        fragment.setDialogDismiss(dialogDismiss);
        fragment.show(fm,tag);
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            englishWord = getArguments().getString(ENGLISH_WORD);
            sekaniWord = getArguments().getString(SEKANI_WORD);
            audio = getArguments().getByteArray(AUDIO);
        }
    }



    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        mViewModel.setNavigator(this);
        if(getDialog().getWindow()!=null){
            getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
        mViewModel.init(sekaniWord,englishWord,audio);
        return view;
    }

    @Override
    public IncorrectDialogViewModel getViewModel() {
        return ViewModelProviders.of(this, factory).get(IncorrectDialogViewModel.class);
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.dialog_incorrect;
    }


}
