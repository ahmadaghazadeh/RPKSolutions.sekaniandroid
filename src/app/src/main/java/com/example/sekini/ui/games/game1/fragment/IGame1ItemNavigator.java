package com.example.sekini.ui.games.game1.fragment;


import com.example.sekini.utils.base.fragment.IFragmentNavigator;
import com.example.sekini.utils.recycler.BaseRecyclerView;

import java.util.List;

public interface IGame1ItemNavigator extends IFragmentNavigator {

    void nextCard();

    void incorrectDialog(String sekaniWord, String englishWord, byte[] audio);

    void correctDialog(String sekaniWord, String englishWord, byte[] audio);

    void gotoMain();
}
