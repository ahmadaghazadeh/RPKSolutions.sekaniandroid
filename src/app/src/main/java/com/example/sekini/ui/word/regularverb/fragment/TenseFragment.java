package com.example.sekini.ui.word.regularverb.fragment;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sekini.BR;
import com.example.sekini.R;
import com.example.sekini.app.BundleNames;
import com.example.sekini.databinding.FragmentTenseBinding;
import com.example.sekini.ui.dictionary.dic.EmptyDicViewModel;
import com.example.sekini.utils.base.fragment.BaseFragment;
import com.example.sekini.utils.recycler.BaseRecyclerView;
import com.example.sekini.utils.recycler.EasyAdapter;

import java.util.List;

import javax.inject.Inject;

public class TenseFragment extends BaseFragment<FragmentTenseBinding, TenseViewModel> implements ITenseNavigator {

    @Inject
    ViewModelProvider.Factory factory;

    EasyAdapter<BaseRecyclerView> adapter;

    int sekaniRootId = 0;

    public static TenseFragment newInstance(int rootWordId) {
        TenseFragment fragment = new TenseFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(BundleNames.Id, rootWordId);
        fragment.setArguments(bundle);
        return fragment;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            sekaniRootId = getArguments().getInt(BundleNames.Id);
        }
        mViewModel.setNavigator(this);
        mViewModel.init(sekaniRootId);
        adapter = new EasyAdapter<>();
        adapter.setEmptyLayout(new EmptyDicViewModel());
        mViewDataBinding.recyclerWord.setAdapter(adapter);

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        return view;
    }

    @Override
    public TenseViewModel getViewModel() {
        return ViewModelProviders.of(this, factory).get(TenseViewModel.class);
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_tense;
    }

    @Override
    public void init(List<BaseRecyclerView> items) {

    }
}
