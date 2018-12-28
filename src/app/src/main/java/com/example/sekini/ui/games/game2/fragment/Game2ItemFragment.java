package com.example.sekini.ui.games.game2.fragment;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sekini.BR;
import com.example.sekini.R;
import com.example.sekini.app.BundleNames;
import com.example.sekini.databinding.FragmentGame2ItemBinding;
import com.example.sekini.ui.games.FeedBack;
import com.example.sekini.ui.games.game2.SharedGame2ViewModel;
import com.example.sekini.ui.main.fragment.MainFragment;
import com.example.sekini.utils.base.fragment.BaseFragment;

import java.util.List;

import javax.inject.Inject;

public class Game2ItemFragment extends BaseFragment<FragmentGame2ItemBinding, Game2ItemViewModel> implements IGame2ItemNavigator {

    @Inject
    ViewModelProvider.Factory factory;

    SharedGame2ViewModel game2ViewModel;

    private int index;


    public static Game2ItemFragment newInstance(int index) {
        Game2ItemFragment fragment = new Game2ItemFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(BundleNames.Id, index);
        fragment.setArguments(bundle);
        return fragment;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            index = bundle.getInt(BundleNames.Id);
        }

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        mViewModel.setNavigator(this);
        mViewModel.init(index);
        game2ViewModel = ViewModelProviders.of(getBaseActivity()).get(SharedGame2ViewModel.class);
        game2ViewModel.getNext().observe(getBaseActivity(), aBoolean -> mViewModel.setScores());
        return view;
    }

    @Override
    public Game2ItemViewModel getViewModel() {
        return ViewModelProviders.of(this, factory).get(Game2ItemViewModel.class);
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_game2_item;
    }


    @Override
    public void next() {
        game2ViewModel.nextPage();
    }

    @Override
    public void win() {
        if (game2ViewModel.getFeedBack().getValue() != null) {
            List<FeedBack> lst = game2ViewModel.getFeedBack().getValue();
            lst.set(index, FeedBack.True);
            game2ViewModel.getFeedBack().setValue(lst);
        }

    }

    @Override
    public void lose() {
        if (game2ViewModel.getFeedBack().getValue() != null) {
            List<FeedBack> lst = game2ViewModel.getFeedBack().getValue();
            lst.set(index, FeedBack.False);
            game2ViewModel.getFeedBack().setValue(lst);
        }
    }
    @Override
    public void gotoMain() {
        getBaseActivity().addFragment(R.id.fragment_container, MainFragment.newInstance());
    }

}
