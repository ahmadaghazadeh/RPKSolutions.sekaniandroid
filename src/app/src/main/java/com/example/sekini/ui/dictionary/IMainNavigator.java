package com.example.sekini.ui.dictionary;


import com.example.sekini.data.model.embedded.SekaniEnglishDicDto;
import com.example.sekini.utils.base.activity.IActivityNavigator;
import com.example.sekini.utils.recycler.BaseRecyclerView;

import java.util.List;

public interface IMainNavigator extends IActivityNavigator {

    void startSyncService();

    void init(List<BaseRecyclerView> items);
    void startGenericWord(SekaniEnglishDicDto items);
    void startImpersonalVerb(SekaniEnglishDicDto items);
    void startRegularVerb(SekaniEnglishDicDto items);
    void startPossessedNoun(SekaniEnglishDicDto items);
}
