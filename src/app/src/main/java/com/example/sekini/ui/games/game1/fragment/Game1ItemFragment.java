package com.example.sekini.ui.games.game1.fragment;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sekini.BR;
import com.example.sekini.R;
import com.example.sekini.app.BundleNames;
import com.example.sekini.databinding.FragmentGame1ItemBinding;
import com.example.sekini.ui.dialog.correct.CorrectDialog;
import com.example.sekini.ui.dialog.incorrect.IncorrectDialog;
import com.example.sekini.ui.games.game1.SharedGame1ViewModel;
import com.example.sekini.ui.games.FeedBack;
import com.example.sekini.ui.main.fragment.MainFragment;
import com.example.sekini.utils.base.fragment.BaseFragment;


import java.util.List;

import javax.inject.Inject;

public class Game1ItemFragment extends BaseFragment<FragmentGame1ItemBinding, Game1ItemViewModel> implements IGame1ItemNavigator {

    @Inject
    ViewModelProvider.Factory factory;
    SharedGame1ViewModel game1ViewModel;

    int sekaniRootId = 0;
    int index;

    public static Game1ItemFragment newInstance(int index,int rootWordId) {
        Game1ItemFragment fragment = new Game1ItemFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(BundleNames.Id, rootWordId);
        bundle.putInt(BundleNames.Index, index);
        fragment.setArguments(bundle);
        return fragment;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            sekaniRootId = getArguments().getInt(BundleNames.Id);
            index = getArguments().getInt(BundleNames.Index);
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        mViewModel.setNavigator(this);
        mViewModel.init(index,sekaniRootId);
        game1ViewModel = ViewModelProviders.of(getBaseActivity()).get(SharedGame1ViewModel.class);
        game1ViewModel.getNext().observe(getBaseActivity(), aBoolean -> mViewModel.setScores());
        return view;
    }

    @Override
    public Game1ItemViewModel getViewModel() {
        return ViewModelProviders.of(this, factory).get(Game1ItemViewModel.class);
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_game1_item;
    }

    @Override
    public void nextCard() {
        game1ViewModel.nextPage();
    }

    @Override
    public void incorrectDialog(String sekaniWord, String englishWord, byte[] audio) {
        IncorrectDialog.show(getFragmentManager(), sekaniWord, englishWord, audio, this::nextCard);
        if (game1ViewModel.getFeedBack().getValue() != null) {
            List<FeedBack> lst = game1ViewModel.getFeedBack().getValue();
            lst.set(index, FeedBack.False);
            game1ViewModel.getFeedBack().setValue(lst);
        }
    }

    @Override
    public void correctDialog(String sekaniWord, String englishWord, byte[] audio) {
        CorrectDialog.show(getFragmentManager(), sekaniWord, englishWord, audio, this::nextCard);
        if (game1ViewModel.getFeedBack().getValue() != null) {
            List<FeedBack> lst = game1ViewModel.getFeedBack().getValue();
            lst.set(index, FeedBack.True);
            game1ViewModel.getFeedBack().setValue(lst);
        }
    }

    @Override
    public void gotoMain() {
        getBaseActivity().addFragment(R.id.fragment_container, MainFragment.newInstance());
    }

}
