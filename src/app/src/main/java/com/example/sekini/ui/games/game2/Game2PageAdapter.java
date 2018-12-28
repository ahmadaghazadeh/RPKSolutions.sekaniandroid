package com.example.sekini.ui.games.game2;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.sekini.ui.games.game1.fragment.Game1ItemFragment;
import com.example.sekini.ui.games.game2.fragment.Game2ItemFragment;
import com.example.sekini.ui.games.game2.fragment.Game2ItemViewModel;

import java.util.List;

public class Game2PageAdapter extends FragmentStatePagerAdapter {
    private Integer pageCount;

    public Game2PageAdapter(FragmentManager fm, Integer pageCount) {
        super(fm);
        this.pageCount = pageCount;
    }

    public Fragment getItem(int position) {
        return Game2ItemFragment.newInstance(position);
    }

    // Returns the page title for the top indicator
    @Override
    public CharSequence getPageTitle(int position) {
        return "";
    }

    @Override
    public int getCount() {
        return pageCount;
    }
}
