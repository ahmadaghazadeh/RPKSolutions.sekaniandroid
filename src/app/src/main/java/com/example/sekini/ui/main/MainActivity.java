package com.example.sekini.ui.main;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.sekini.BR;
import com.example.sekini.R;
import com.example.sekini.app.C;
import com.example.sekini.databinding.ActivityMainBinding;
import com.example.sekini.service.SyncService;
import com.example.sekini.ui.dictionary.DictionaryFragment;
import com.example.sekini.ui.login.LoginFragment;
import com.example.sekini.ui.setting.SettingsActivity;
import com.example.sekini.utils.base.activity.BaseActivity;
import com.example.sekini.utils.common.CommonUtils;

import javax.inject.Inject;

public class MainActivity extends BaseActivity<ActivityMainBinding, MainViewModel>
        implements IMainNavigator, NavigationView.OnNavigationItemSelectedListener {

    @Inject
    ViewModelProvider.Factory factory;


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
        setToolbarTitle();
        mViewModel.init();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mViewDataBinding.drawerLayout, getToolbar(), R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mViewDataBinding.drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        mViewDataBinding.navView.setNavigationItemSelectedListener(this);

        CommonUtils.exportDatabase(this, "Sekini.db");

        SharedMainViewModel sharedMainViewModel = ViewModelProviders.of(this).get(SharedMainViewModel.class);
        sharedMainViewModel.getModel().observe(this,sharedMainModel -> {
            if(sharedMainModel!=null && sharedMainModel.isOpenDrawer()){
                mViewDataBinding.drawerLayout.openDrawer(GravityCompat.START);
            }
        });
        addFragment(R.id.fragment_container, DictionaryFragment.newInstance(false));
    }


    public void setToolbarTitle() {
        Toolbar toolbar = getToolbar();
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }



    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.nav_sekani_to_english) {
            addFragment(DictionaryFragment.newInstance(false));
        } else if (id == R.id.nav_english_to_sekani) {
            addFragment(DictionaryFragment.newInstance(true));
        } else if (id == R.id.nav_game1) {
            addFragment(R.id.fragment_container, LoginFragment.newInstance(C.GameType.Game1));
        } else if (id == R.id.nav_game2) {
            replaceFragment(R.id.fragment_container, LoginFragment.newInstance(C.GameType.Game2));
        } else if (id == R.id.nav_settings) {
            SettingsActivity.start(this);
        } else if (id == R.id.nav_about) {

        }
        mViewDataBinding.drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void startSyncService() {
        SyncService.start(this);
    }
}
