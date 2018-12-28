package com.example.sekini.ui.games.game2;


import com.example.sekini.utils.base.fragment.IFragmentNavigator;

import java.util.List;

public interface IGame2Navigator extends IFragmentNavigator {

    void initPager(Integer count);

    void gotoMain();
}
