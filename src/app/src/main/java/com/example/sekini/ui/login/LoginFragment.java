package com.example.sekini.ui.login;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sekini.BR;
import com.example.sekini.R;
import com.example.sekini.app.BundleNames;
import com.example.sekini.app.C;
import com.example.sekini.databinding.FragmentLoginBinding;
import com.example.sekini.ui.games.game1.Game1Fragment;
import com.example.sekini.utils.base.fragment.BaseFragment;

import javax.inject.Inject;

public class LoginFragment extends BaseFragment<FragmentLoginBinding, LoginViewModel>
        implements ILoginNavigator {

    @Inject
    ViewModelProvider.Factory factory;
    C.GameType gameType = C.GameType.Game1;

    public static LoginFragment newInstance(C.GameType gameType) {
        LoginFragment fragment = new LoginFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(BundleNames.GameType, gameType.ordinal());
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            gameType = C.GameType.values()[bundle.getInt(BundleNames.GameType)];
        }

    }

    @Override
    public LoginViewModel getViewModel() {
        return ViewModelProviders.of(this, factory).get(LoginViewModel.class);
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_login;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        mViewModel.setNavigator(this);
        mViewModel.init();
        return view;
    }

    @Override
    public void loginSuccessful() {
        hideKeyboard();
        getBaseActivity().popBackStack();
        switch (gameType) {
            case Game1:
                getBaseActivity().addFragment(R.id.fragment_container, Game1Fragment.newInstance());
                return;
            case Game2:
                getBaseActivity().addFragment(R.id.fragment_container, Game1Fragment.newInstance());
                return;
            default:
                getBaseActivity().addFragment(R.id.fragment_container, Game1Fragment.newInstance());
        }


    }


}
