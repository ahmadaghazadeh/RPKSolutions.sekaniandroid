package com.example.sekini.ui.dictionary;

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
import com.example.sekini.databinding.FragmentDictionaryBinding;
import com.example.sekini.ui.dictionary.dic.EmptyDicViewModel;
import com.example.sekini.ui.main.SharedMainViewModel;
import com.example.sekini.ui.word.generic.GenericFragment;
import com.example.sekini.ui.word.possessednoun.PossessedNounFragment;
import com.example.sekini.ui.word.regularverb.RegularVerbFragment;
import com.example.sekini.utils.base.fragment.BaseFragment;
import com.example.sekini.utils.recycler.BaseRecyclerView;
import com.example.sekini.utils.recycler.EasyAdapter;

import java.util.List;

import javax.inject.Inject;

public class DictionaryFragment extends BaseFragment<FragmentDictionaryBinding, DictionaryViewModel>
        implements IDictionaryNavigator {

    SharedMainViewModel sharedMainViewModel;
    @Inject
    ViewModelProvider.Factory factory;

    EasyAdapter<BaseRecyclerView> adapter;
    boolean isEnglish = false;

    public static DictionaryFragment newInstance(boolean isEnglish) {
        DictionaryFragment fragment = new DictionaryFragment();
        Bundle bundle = new Bundle();
        bundle.putBoolean(BundleNames.IsEnglish, isEnglish);
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    public DictionaryViewModel getViewModel() {
        return ViewModelProviders.of(this, factory).get(DictionaryViewModel.class);
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_dictionary;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            isEnglish = bundle.getBoolean(BundleNames.IsEnglish);
        }
    }



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        getBaseActivity().getToolbar().setVisibility(View.GONE);
        mViewModel.init(isEnglish);
        mViewModel.setNavigator(this);
        mViewDataBinding.toggleDic.setCheckedTogglePosition(isEnglish ? 0 : 1);
        adapter = new EasyAdapter<>();
        adapter.setEmptyLayout(new EmptyDicViewModel());
        mViewDataBinding.recyclerDic.setAdapter(adapter);
        sharedMainViewModel = ViewModelProviders.of(getBaseActivity()).get(SharedMainViewModel.class);
        return view;
    }

    @Override
    public void init(List<BaseRecyclerView> items) {
        adapter.init(items);
    }

    @Override
    public void startGenericWord(int sekaniRootId) {
        getBaseActivity().addFragment(GenericFragment.newInstance( sekaniRootId));
    }

    @Override
    public void startImpersonalVerb(int sekaniRootId) {

    }

    @Override
    public void startRegularVerb(int sekaniRootId) {
        getBaseActivity().addFragment(RegularVerbFragment.newInstance( sekaniRootId));
    }

    @Override
    public void startPossessedNoun(int sekaniRootId) {
        getBaseActivity().addFragment(PossessedNounFragment.newInstance( sekaniRootId));
    }

    @Override
    public void openDrawer() {
        sharedMainViewModel.openDrawer();
    }

}
