package com.example.sekini.ui.word.possessednoun;


import com.example.sekini.data.model.embedded.SekaniEnglishDicDto;
import com.example.sekini.utils.base.activity.IActivityNavigator;
import com.example.sekini.utils.recycler.BaseRecyclerView;

import java.util.List;

public interface IPossessedNounNavigator extends IActivityNavigator {

    void init(List<BaseRecyclerView> items);
}
