package com.example.sekini.ui.dictionary;


import com.example.sekini.data.model.embedded.EnglishDicDto;
import com.example.sekini.utils.base.activity.IActivityNavigator;
import com.example.sekini.utils.recycler.BaseRecyclerView;

import java.util.List;

public interface IMainNavigator extends IActivityNavigator {

    void startSyncService();

    void init(List<BaseRecyclerView> items);
    void startGenericWord(int sekaniRootId);
    void startImpersonalVerb(int sekaniRootId);
    void startRegularVerb(int sekaniRootId);
    void startPossessedNoun(int sekaniRootId);
}
