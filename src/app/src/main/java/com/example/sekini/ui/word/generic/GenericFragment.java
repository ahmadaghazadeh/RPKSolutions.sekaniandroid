package com.example.sekini.ui.word.generic;

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
import com.example.sekini.databinding.ActivityGenericBinding;
import com.example.sekini.ui.dictionary.dic.EmptyDicViewModel;
import com.example.sekini.utils.base.fragment.BaseFragment;
import com.example.sekini.utils.recycler.BaseRecyclerView;
import com.example.sekini.utils.recycler.EasyAdapter;

import java.util.List;

import javax.inject.Inject;

public class GenericFragment extends BaseFragment<ActivityGenericBinding, GenericViewModel>
        implements IGenericNavigator {

    @Inject
    ViewModelProvider.Factory factory;

    EasyAdapter<BaseRecyclerView> adapter;

    int sekaniRootId = 0;


    public static GenericFragment newInstance(int sekaniRootId) {
        GenericFragment fragment = new GenericFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(BundleNames.Id, sekaniRootId);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public GenericViewModel getViewModel() {
        return ViewModelProviders.of(this, factory).get(GenericViewModel.class);
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_generic;
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
        mViewModel.setNavigator(this);
        mViewModel.init(sekaniRootId);
        adapter = new EasyAdapter<>();
        adapter.setEmptyLayout(new EmptyDicViewModel());
        mViewDataBinding.recyclerWord.setAdapter(adapter);
        return view;
    }


    @Override
    public void init(List<BaseRecyclerView> items) {
        adapter.init(items);
    }




}
