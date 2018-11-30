package com.example.sekini.ui.games.game1;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.sekini.ui.games.game1.fragment.Game1ItemFragment;
import com.example.sekini.ui.word.item.verb.Verb;
import com.example.sekini.ui.word.regularverb.fragment.TenseFragment;

import java.util.List;

public class Game1PageAdapter extends FragmentStatePagerAdapter {
    private List<Integer> sekaniRootIds;

    public Game1PageAdapter(FragmentManager fm, List<Integer> sekaniRootIds) {
        super(fm);
        this.sekaniRootIds = sekaniRootIds;
    }

    public Fragment getItem(int position) {
        return Game1ItemFragment.newInstance(sekaniRootIds.get(position));
    }

    // Returns the page title for the top indicator
    @Override
    public CharSequence getPageTitle(int position) {
        return "";
    }

    @Override
    public int getCount() {
        return sekaniRootIds.size();
    }
}
