package com.example.sekini.ui.word.regularverb.fragment;


import com.example.sekini.utils.base.activity.IActivityNavigator;
import com.example.sekini.utils.base.fragment.IFragmentNavigator;
import com.example.sekini.utils.recycler.BaseRecyclerView;

import java.util.List;

public interface ITenseNavigator extends IFragmentNavigator {

    void init(List<BaseRecyclerView> items);
}
