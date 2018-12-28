package com.example.sekini.ui.word.possessednoun;

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
import com.example.sekini.databinding.FragmentPossessedNounBinding;
import com.example.sekini.ui.dialog.image.ImageDialog;
import com.example.sekini.ui.dictionary.dic.EmptyDicViewModel;
import com.example.sekini.utils.base.fragment.BaseFragment;
import com.example.sekini.utils.recycler.BaseRecyclerView;
import com.example.sekini.utils.recycler.EasyAdapter;

import java.util.List;

import javax.inject.Inject;

public class PossessedNounFragment extends BaseFragment<FragmentPossessedNounBinding, PossessedNounViewModel>
        implements IPossessedNounNavigator {

    @Inject
    ViewModelProvider.Factory factory;

    EasyAdapter<BaseRecyclerView> adapter;

    int sekaniRootId = 0;


    public static PossessedNounFragment newInstance(int sekaniRootId) {
        PossessedNounFragment fragment = new PossessedNounFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(BundleNames.Id, sekaniRootId);
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    public PossessedNounViewModel getViewModel() {
        return ViewModelProviders.of(this, factory).get(PossessedNounViewModel.class);
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_possessed_noun;
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

    @Override
    public void showImageDialog(byte[] param) {
        ImageDialog.show(getChildFragmentManager(),param,"",null);
    }


}
