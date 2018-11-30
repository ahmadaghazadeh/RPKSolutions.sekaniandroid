package com.example.sekini.ui.word.generic;


import com.example.sekini.utils.base.INavigator;
import com.example.sekini.utils.base.fragment.IFragmentNavigator;
import com.example.sekini.utils.recycler.BaseRecyclerView;

import java.util.List;

public interface IGenericNavigator extends IFragmentNavigator {

    void init(List<BaseRecyclerView> items);
}
