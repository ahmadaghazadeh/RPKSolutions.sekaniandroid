package com.example.sekini.ui.dialog.image;

import android.arch.lifecycle.MutableLiveData;

import com.example.sekini.data.IRepository;
import com.example.sekini.data.local.db.embedded.ISekaniWordDtoDao;
import com.example.sekini.data.local.pref.IAppPref;
import com.example.sekini.utils.base.dialog.DialogBaseViewModel;
import com.example.sekini.utils.common.CommonUtils;

import javax.inject.Inject;


public class ImageDialogViewModel extends DialogBaseViewModel<IImageDialogNavigator> {

    public MutableLiveData<byte[]> base64=new MutableLiveData<>();
    public MutableLiveData<String> title=new MutableLiveData<>();

    @Inject
    public CommonUtils commonUtils;

    @Inject
    public IAppPref appPref;

    @Inject
    public ISekaniWordDtoDao sekaniWordDtoDao;

    @Inject
    public IRepository repository;


    @Inject
    public ImageDialogViewModel() {

    }

    public void init(byte[] bytes,String title) {
        this.base64.setValue(bytes);
        this.title.setValue(title);
    }

}
