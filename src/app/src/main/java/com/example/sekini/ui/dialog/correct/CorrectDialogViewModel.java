package com.example.sekini.ui.dialog.correct;

import android.arch.lifecycle.MutableLiveData;

import com.example.sekini.data.IRepository;
import com.example.sekini.data.local.db.embedded.ISekaniWordDtoDao;
import com.example.sekini.data.local.pref.IAppPref;
import com.example.sekini.utils.base.dialog.DialogBaseViewModel;
import com.example.sekini.utils.common.CommonUtils;

import javax.inject.Inject;


public class CorrectDialogViewModel extends DialogBaseViewModel<ICorrectDialogNavigator> {

    public MutableLiveData<String> sekaniWord=new MutableLiveData<>();
    public MutableLiveData<String> englishWord=new MutableLiveData<>();
    public  MutableLiveData<byte[]>  audio=new MutableLiveData<>();

    @Inject
    public CommonUtils commonUtils;

    @Inject
    public IAppPref appPref;

    @Inject
    public ISekaniWordDtoDao sekaniWordDtoDao;

    @Inject
    public IRepository repository;


    @Inject
    public CorrectDialogViewModel() {

    }

    public void init(String sekaniWord,String englishWord,byte[] audio) {
        this.audio.setValue(audio);
        this.englishWord.setValue(englishWord);
        this.sekaniWord.setValue(sekaniWord);
    }

    public void onClickAudio() {
        CommonUtils.PlayAudio(audio.getValue());
    }


}
