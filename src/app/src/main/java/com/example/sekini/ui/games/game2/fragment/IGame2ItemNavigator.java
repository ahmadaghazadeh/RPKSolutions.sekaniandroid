package com.example.sekini.ui.games.game2.fragment;


import com.example.sekini.utils.base.fragment.IFragmentNavigator;

public interface IGame2ItemNavigator extends IFragmentNavigator {

    void next();

    void win();

    void lose();

    void gotoMain();
}
