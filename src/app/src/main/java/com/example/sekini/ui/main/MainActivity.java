package com.example.sekini.ui.main;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.sekini.BR;
import com.example.sekini.R;
import com.example.sekini.data.local.pref.IAppPref;
import com.example.sekini.data.remote.api.IApi;
import com.example.sekini.databinding.ActivityMainBinding;
import com.example.sekini.service.SyncService;
import com.example.sekini.ui.dictionary.DictionaryFragment;
import com.example.sekini.ui.games.game1.Game1Fragment;
import com.example.sekini.ui.games.game2.Game2Fragment;
import com.example.sekini.ui.login.LoginFragment;
import com.example.sekini.ui.setting.SettingsActivity;
import com.example.sekini.utils.base.activity.BaseActivity;
import com.example.sekini.utils.common.RunnableIn;
import com.example.sekini.utils.common.RunnableModel;

import javax.inject.Inject;

public class MainActivity extends BaseActivity<ActivityMainBinding, MainViewModel>
        implements IMainNavigator, NavigationView.OnNavigationItemSelectedListener {



    @Inject
    public IAppPref appPref;

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
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mViewDataBinding.drawerLayout, getToolbar(), R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mViewDataBinding.drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        mViewDataBinding.navView.setNavigationItemSelectedListener(this);
        mViewDataBinding.navView.setItemIconTintList(null);
        // CommonUtils.exportDatabase(this, "Sekini.db");

        SharedMainViewModel sharedMainViewModel = ViewModelProviders.of(this).get(SharedMainViewModel.class);
        sharedMainViewModel.getModel().observe(this, sharedMainModel -> {
            if (sharedMainModel != null && sharedMainModel.isOpenDrawer()) {
                mViewDataBinding.drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        sharedMainViewModel.getMenu().observe(this, integer -> {
            if (integer != null) {
                Menu menuNav = mViewDataBinding.navView.getMenu();
                MenuItem navGame1 = menuNav.findItem(integer);
                navGame1.setChecked(true);
            }
        });

        sharedMainViewModel.getRefreshMenu().observe(this, bool -> {
            if (bool != null) {
                setUpDrawer();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpDrawer();
    }

    private void setUpDrawer() {
        boolean flag = appPref.isLogin();
        Menu menuNav = mViewDataBinding.navView.getMenu();
        MenuItem navGame1 = menuNav.findItem(R.id.nav_game1);
        MenuItem navGame2 = menuNav.findItem(R.id.nav_game2);
        if (flag) {
            navGame1.setIcon(R.drawable.ic_menu_game1_colored);
            navGame2.setIcon(R.drawable.ic_menu_game2_colored);
        } else {
            navGame1.setIcon(R.drawable.ic_menu_game1);
            navGame2.setIcon(R.drawable.ic_menu_game2);
        }
        MenuItem navGames = menuNav.findItem(R.id.nav_games);
        navGames.setTitle(!flag ? R.string.loginGame : R.string.normalGame);
        mViewModel.init();
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
            if (appPref.isLogin()) {
                addFragment(R.id.fragment_container, Game1Fragment.newInstance());
            }else {
                login();
            }
        } else if (id == R.id.nav_game2) {
            if (appPref.isLogin()) {
                replaceFragment(R.id.fragment_container, Game2Fragment.newInstance());
            }else {
                login();
            }
        } else if (id == R.id.nav_settings) {
            SettingsActivity.start(this);
        } else if (id == R.id.nav_about) {

        } else if (id == R.id.nav_reset_life) {
          mViewModel.resetLife();
        }
        mViewDataBinding.drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void startSyncService() {
        SyncService.start(this);
    }

    @Override
    public void login() {
        replaceFragment(R.id.fragment_container, LoginFragment.newInstance());
        mViewDataBinding.drawerLayout.closeDrawer(GravityCompat.START);
    }


}
