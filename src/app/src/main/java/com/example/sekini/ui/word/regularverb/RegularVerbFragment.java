package com.example.sekini.ui.word.regularverb;

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
import com.example.sekini.databinding.FragmentRegularVerbBinding;
import com.example.sekini.ui.dictionary.dic.EmptyDicViewModel;
import com.example.sekini.utils.base.fragment.BaseFragment;
import com.example.sekini.utils.recycler.EasyAdapter;

import javax.inject.Inject;

public class RegularVerbFragment extends BaseFragment<FragmentRegularVerbBinding, RegularVerbViewModel>
        implements IRegularVerbNavigator {

    @Inject
    ViewModelProvider.Factory factory;

    int sekaniRootId = 0;


    public static RegularVerbFragment newInstance(  int sekaniRootId) {
        RegularVerbFragment fragment = new RegularVerbFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(BundleNames.Id, sekaniRootId);
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    public RegularVerbViewModel getViewModel() {
        return ViewModelProviders.of(this, factory).get(RegularVerbViewModel.class);
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_regular_verb;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            sekaniRootId = bundle.getInt(BundleNames.Id);
        }

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        mViewDataBinding.viewPager.setAdapter(new TensePageAdapter(getBaseActivity().getSupportFragmentManager(), sekaniRootId));
        mViewDataBinding.tabLayout.setupWithViewPager(mViewDataBinding.viewPager);
        showLoadingDialog(R.drawable.ic_dict,getString(R.string.dictionary));
        return view;
    }

}
