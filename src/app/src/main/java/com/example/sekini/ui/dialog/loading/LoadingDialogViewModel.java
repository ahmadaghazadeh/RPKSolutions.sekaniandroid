package com.example.sekini.ui.dialog.loading;

import android.arch.lifecycle.MutableLiveData;

import com.example.sekini.data.IRepository;
import com.example.sekini.data.local.db.embedded.ISekaniWordDtoDao;
import com.example.sekini.data.local.pref.IAppPref;
import com.example.sekini.utils.base.dialog.DialogBaseViewModel;
import com.example.sekini.utils.common.CommonUtils;

import javax.inject.Inject;


public class LoadingDialogViewModel extends DialogBaseViewModel<ILoadingDialogNavigator> {

    public MutableLiveData<Integer> resId=new MutableLiveData<>();
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
    public LoadingDialogViewModel() {

    }

    public void init(int resId,String title) {
        this.resId.setValue(resId);
        this.title.setValue(title);
    }

}
