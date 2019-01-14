package com.example.sekini.ui.mainbottom;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.sekini.BR;
import com.example.sekini.R;
import com.example.sekini.data.local.pref.IAppPref;
import com.example.sekini.databinding.ActivityMainBinding;
import com.example.sekini.databinding.ActivityMainBottomBinding;
import com.example.sekini.service.SyncService;
import com.example.sekini.ui.dictionary.DictionaryFragment;
import com.example.sekini.ui.login.LoginFragment;
import com.example.sekini.ui.main.MainActivity;
import com.example.sekini.ui.main.SharedMainViewModel;
import com.example.sekini.utils.base.activity.BaseActivity;
import com.luseen.spacenavigation.SpaceItem;
import com.luseen.spacenavigation.SpaceNavigationView;
import com.luseen.spacenavigation.SpaceOnClickListener;

import javax.inject.Inject;

public class MainBottomActivity extends BaseActivity<ActivityMainBottomBinding, MainBottomViewModel>
        implements IMainBottomNavigator{
    SpaceNavigationView spaceNavigationView;
    @Inject
    public IAppPref appPref;

    @Inject
    ViewModelProvider.Factory factory;

    @Override
    public MainBottomViewModel getViewModel() {
        return ViewModelProviders.of(this, factory).get(MainBottomViewModel.class);
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main_bottom;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mViewModel.setNavigator(this);
        //CommonUtils.exportDatabase(this, "Sekini.db");

        SharedMainViewModel sharedMainViewModel = ViewModelProviders.of(this).get(SharedMainViewModel.class);
        sharedMainViewModel.getModel().observe(this, sharedMainModel -> {

        });

        sharedMainViewModel.getMenu().observe(this, integer -> {
            if (integer != null) {

            }
        });

        sharedMainViewModel.getRefreshMenu().observe(this, bool -> {
            if (bool != null) {

            }
        });

        spaceNavigationView = (SpaceNavigationView) findViewById(R.id.space);
        spaceNavigationView.initWithSaveInstanceState(savedInstanceState);
        spaceNavigationView.addSpaceItem(new SpaceItem("", R.drawable.ic_menu_game1));
        spaceNavigationView.addSpaceItem(new SpaceItem("", R.drawable.ic_menu_game2));

        spaceNavigationView.setSpaceOnClickListener(new SpaceOnClickListener() {
            @Override
            public void onCentreButtonClick() {
                Toast.makeText(MainBottomActivity.this,"onCentreButtonClick", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemClick(int itemIndex, String itemName) {
                Toast.makeText(MainBottomActivity.this, itemIndex + " " + itemName, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemReselected(int itemIndex, String itemName) {
                Toast.makeText(MainBottomActivity.this, itemIndex + " " + itemName, Toast.LENGTH_SHORT).show();
            }
        });
        //replaceFragment(R.id.fragment_container, DictionaryFragment.newInstance(true));
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        spaceNavigationView.onSaveInstanceState(outState);
    }


    @Override
    public void startSyncService() {
        SyncService.start(this);
    }

    @Override
    public void login() {
        replaceFragment(R.id.fragment_container, LoginFragment.newInstance());
    }


}
