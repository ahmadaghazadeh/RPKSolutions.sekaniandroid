package com.example.sekini.ui.dictionary;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.sekini.BR;
import com.example.sekini.R;
import com.example.sekini.app.BundleNames;
import com.example.sekini.data.model.embedded.SekaniEnglishDicDto;
import com.example.sekini.databinding.ActivityMainBinding;
import com.example.sekini.service.SyncService;
import com.example.sekini.ui.dictionary.dic.EmptyDicViewModel;
import com.example.sekini.ui.word.generic.GenericActivity;
import com.example.sekini.ui.word.regularverb.RegularVerbActivity;
import com.example.sekini.ui.word.possessednoun.PossessedNounActivity;
import com.example.sekini.utils.base.activity.BaseActivity;
import com.example.sekini.utils.common.CommonUtils;
import com.example.sekini.utils.recycler.BaseRecyclerView;
import com.example.sekini.utils.recycler.EasyAdapter;

import java.util.List;

import javax.inject.Inject;

public class MainActivity extends BaseActivity<ActivityMainBinding, MainViewModel>
        implements IMainNavigator {

    @Inject
    ViewModelProvider.Factory factory;

    EasyAdapter<BaseRecyclerView> adapter;

    public static Intent newInstance(Context context, int id) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra(BundleNames.Id, id);
        return intent;
    }


    @Override
    public MainViewModel getViewModel() {
        return ViewModelProviders.of(this, factory).get(MainViewModel.class);
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel.setNavigator(this);
        mViewModel.init();
        adapter = new EasyAdapter<>();
        adapter.setEmptyLayout(new EmptyDicViewModel());
        mViewDataBinding.recyclerDic.setAdapter(adapter);

        CommonUtils.exportDatabase(this, "Sekini.db");
    }

    @Override
    public void startSyncService() {
        SyncService.start(this);
    }

    @Override
    public void init(List<BaseRecyclerView> items) {
        adapter.init(items);
    }

    @Override
    public void startGenericWord(SekaniEnglishDicDto items) {
        GenericActivity.start(this, items.sekaniRootId);
    }

    @Override
    public void startImpersonalVerb(SekaniEnglishDicDto items) {

    }

    @Override
    public void startRegularVerb(SekaniEnglishDicDto items) {
        RegularVerbActivity.start(this, items.sekaniRootId);
    }

    @Override
    public void startPossessedNoun(SekaniEnglishDicDto items) {
        PossessedNounActivity.start(this, items.sekaniRootId);
    }

}
