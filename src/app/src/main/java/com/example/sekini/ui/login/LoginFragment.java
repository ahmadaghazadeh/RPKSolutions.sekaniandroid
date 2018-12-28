package com.example.sekini.ui.login;

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
import com.example.sekini.databinding.FragmentLoginBinding;
import com.example.sekini.ui.main.SharedMainViewModel;
import com.example.sekini.utils.base.fragment.BaseFragment;

import javax.inject.Inject;

public class LoginFragment extends BaseFragment<FragmentLoginBinding, LoginViewModel>
        implements ILoginNavigator {

    @Inject
    ViewModelProvider.Factory factory;
    SharedMainViewModel sharedMainViewModel;
    public static LoginFragment newInstance( ) {
        LoginFragment fragment = new LoginFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();

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

        sharedMainViewModel = ViewModelProviders.of(getBaseActivity()).get(SharedMainViewModel.class);
        //mViewModel.init();
        return view;
    }

    @Override
    public void loginSuccessful() {
        hideKeyboard();
        getBaseActivity().onBackPressed();
        toast(R.string.login_success);

    }

    public void refreshMenu(){
        sharedMainViewModel.refreshMenu();
    }


}
