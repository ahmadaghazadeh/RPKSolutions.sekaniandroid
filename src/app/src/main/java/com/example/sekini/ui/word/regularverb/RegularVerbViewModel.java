package com.example.sekini.ui.word.regularverb;

import com.example.sekini.data.local.db.embedded.IDicDao;
import com.example.sekini.data.local.db.embedded.ISekaniRootDtoDao;
import com.example.sekini.data.local.db.embedded.ISekaniWordDtoDao;
import com.example.sekini.data.local.db.embedded.ISekaniWordExampleDtoDao;
import com.example.sekini.utils.base.BaseViewModel;
import com.example.sekini.utils.base.fragment.FragmentBaseViewModel;
import com.example.sekini.utils.common.CommonUtils;

import javax.inject.Inject;


public class RegularVerbViewModel extends FragmentBaseViewModel<IRegularVerbNavigator> {


    @Inject
    public CommonUtils commonUtils;

    @Inject
    public IDicDao sekaniWordsDao;

    @Inject
    public ISekaniRootDtoDao sekaniRootDtoDao;

    @Inject
    public ISekaniWordDtoDao sekaniWordDtoDao;

    @Inject
    public ISekaniWordExampleDtoDao sekaniWordExampleDtoDao;


    @Inject
    public RegularVerbViewModel() {
    }





}
