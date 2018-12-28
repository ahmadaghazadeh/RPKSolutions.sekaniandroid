package com.example.sekini.ui.dictionary;


import com.example.sekini.utils.base.INavigator;
import com.example.sekini.utils.base.fragment.IFragmentNavigator;
import com.example.sekini.utils.recycler.BaseRecyclerView;

import java.util.List;

public interface IDictionaryNavigator extends IFragmentNavigator {


    void init(List<BaseRecyclerView> items);
    void startGenericWord(int sekaniRootId);
    void startImpersonalVerb(int sekaniRootId);
    void startRegularVerb(int sekaniRootId);
    void startPossessedNoun(int sekaniRootId);

    void openDrawer();

    void setDrawerMenuDictionary(boolean isEnglish);
}
