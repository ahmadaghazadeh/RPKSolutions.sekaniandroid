package com.example.sekini.ui.word.regularverb.fragment;


import com.example.sekini.utils.base.INavigator;
import com.example.sekini.utils.base.fragment.IFragmentNavigator;
import com.example.sekini.utils.recycler.BaseRecyclerView;

import java.util.List;

public interface ITenseNavigator extends IFragmentNavigator {

    void init(List<BaseRecyclerView> items);

    void showImageDialog(byte[] param1);
}
