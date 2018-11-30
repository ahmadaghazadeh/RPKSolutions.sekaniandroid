package com.example.sekini.ui.games.game1;


import com.example.sekini.utils.base.fragment.IFragmentNavigator;
import com.example.sekini.utils.recycler.BaseRecyclerView;

import java.util.List;

public interface IGame1Navigator extends IFragmentNavigator {

    void initPager(List<Integer> ids);

    void gotoMain();
}
