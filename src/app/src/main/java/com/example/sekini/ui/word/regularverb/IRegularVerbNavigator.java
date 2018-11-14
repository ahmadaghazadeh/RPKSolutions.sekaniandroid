package com.example.sekini.ui.word.regularverb;


import com.example.sekini.utils.base.activity.IActivityNavigator;
import com.example.sekini.utils.recycler.BaseRecyclerView;

import java.util.List;

public interface IRegularVerbNavigator extends IActivityNavigator {

    void init(List<BaseRecyclerView> items);
}
