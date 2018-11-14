package com.example.sekini.ui.word.regularverb;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.sekini.ui.word.item.verb.Verb;

public class TensePageAdapter extends FragmentStatePagerAdapter {
    private static int NUM_ITEMS = 4;

    public TensePageAdapter(FragmentManager fm) {
        super(fm);
    }

    public Fragment getItem(int position) {

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
