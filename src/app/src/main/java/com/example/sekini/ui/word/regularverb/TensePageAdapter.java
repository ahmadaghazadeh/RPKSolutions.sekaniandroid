package com.example.sekini.ui.word.regularverb;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.sekini.ui.word.item.verb.Verb;
import com.example.sekini.ui.word.regularverb.fragment.TenseFragment;

public class TensePageAdapter extends FragmentStatePagerAdapter {
    private static int NUM_ITEMS = 4;
    private int sekaniRootId;

    public TensePageAdapter(FragmentManager fm, int sekaniRootId) {
        super(fm);
        this.sekaniRootId = sekaniRootId;
    }

    public Fragment getItem(int position) {
        return TenseFragment.newInstance(Verb.TENSE.values()[position], sekaniRootId);
    }

    // Returns the page title for the top indicator
    @Override
    public CharSequence getPageTitle(int position) {
        return Verb.TENSE.values()[position].toString();
    }

    @Override
    public int getCount() {
        return NUM_ITEMS;
    }
}
