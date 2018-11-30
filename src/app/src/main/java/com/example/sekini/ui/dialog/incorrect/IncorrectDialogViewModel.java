package com.example.sekini.ui.dialog.incorrect;

import android.arch.lifecycle.MutableLiveData;

import com.example.sekini.R;
import com.example.sekini.data.IRepository;
import com.example.sekini.data.local.db.ISekaniRootImagesDao;
import com.example.sekini.data.local.db.IUserFailedWordDao;
import com.example.sekini.data.local.db.IUserLearnedWordDao;
import com.example.sekini.data.local.db.embedded.ISekaniWordDtoDao;
import com.example.sekini.data.local.pref.IAppPref;
import com.example.sekini.data.model.SekaniRootImagesEntity;
import com.example.sekini.data.model.UserFailedWord;
import com.example.sekini.data.model.UserLearnedWord;
import com.example.sekini.data.model.embedded.SekaniWordAudioDto;
import com.example.sekini.data.model.embedded.SekaniWordDto;
import com.example.sekini.utils.base.dialog.DialogBaseViewModel;
import com.example.sekini.utils.base.fragment.FragmentBaseViewModel;
import com.example.sekini.utils.common.CommonUtils;

import java.util.LinkedList;
import java.util.List;

import javax.inject.Inject;


public class IncorrectDialogViewModel extends DialogBaseViewModel<IIncorrectDialogNavigator> {

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
    public IncorrectDialogViewModel() {

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
