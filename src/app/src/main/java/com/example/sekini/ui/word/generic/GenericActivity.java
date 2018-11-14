package com.example.sekini.ui.word.generic;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.sekini.BR;
import com.example.sekini.R;
import com.example.sekini.app.BundleNames;
import com.example.sekini.data.model.embedded.SekaniEnglishDicDto;
import com.example.sekini.databinding.ActivityGenericBinding;
import com.example.sekini.service.SyncService;
import com.example.sekini.ui.dictionary.dic.EmptyDicViewModel;
import com.example.sekini.utils.base.activity.BaseActivity;
import com.example.sekini.utils.recycler.BaseRecyclerView;
import com.example.sekini.utils.recycler.EasyAdapter;

import java.util.List;

import javax.inject.Inject;

public class GenericActivity extends BaseActivity<ActivityGenericBinding, GenericViewModel>
        implements IGenericNavigator {

    @Inject
    ViewModelProvider.Factory factory;

    EasyAdapter<BaseRecyclerView> adapter;

    int sekaniRootId = 0;

    public static void start(Context context, int sekaniRootId) {
        Intent intent = new Intent(context, GenericActivity.class);
        intent.putExtra(BundleNames.Id, sekaniRootId);
        context.startActivity(intent);
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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            sekaniRootId = bundle.getInt(BundleNames.Id);
        }
        mViewModel.setNavigator(this);
        mViewModel.init(sekaniRootId);
        initToolbar();
        adapter = new EasyAdapter<>();
        adapter.setEmptyLayout(new EmptyDicViewModel());
        mViewDataBinding.recyclerWord.setAdapter(adapter);
    }

    private void initToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // add back arrow to toolbar
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setTitle("Home");
        }
    }


    @Override
    public void init(List<BaseRecyclerView> items) {
        adapter.init(items);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }

        return super.onOptionsItemSelected(item);
    }

}
