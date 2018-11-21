package com.example.sekini.ui.word.generic;


import com.example.sekini.utils.base.activity.IActivityNavigator;
import com.example.sekini.utils.recycler.BaseRecyclerView;

import java.util.List;

public interface IGenericNavigator extends IActivityNavigator {

    void init(List<BaseRecyclerView> items);
}
