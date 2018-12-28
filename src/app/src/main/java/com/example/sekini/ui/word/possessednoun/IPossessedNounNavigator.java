package com.example.sekini.ui.word.possessednoun;


import com.example.sekini.utils.base.INavigator;
import com.example.sekini.utils.base.activity.IActivityNavigator;
import com.example.sekini.utils.base.fragment.IFragmentNavigator;
import com.example.sekini.utils.recycler.BaseRecyclerView;

import java.util.List;

public interface IPossessedNounNavigator extends IFragmentNavigator{

    void init(List<BaseRecyclerView> items);

    void showImageDialog(byte[] param);
}
