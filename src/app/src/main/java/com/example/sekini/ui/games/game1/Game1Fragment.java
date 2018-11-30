package com.example.sekini.ui.games.game1;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.sekini.BR;
import com.example.sekini.R;
import com.example.sekini.databinding.FragmentGame1Binding;
import com.example.sekini.ui.dictionary.DictionaryFragment;
import com.example.sekini.utils.base.dialog.YesNoDialog.YesNoDialog;
import com.example.sekini.utils.base.dialog.prompt.PromptDialog;
import com.example.sekini.utils.base.fragment.BaseFragment;

import java.util.List;

import javax.inject.Inject;

import static com.example.sekini.utils.base.activity.BaseActivity.YES_NO_DIALOG;

public class Game1Fragment extends BaseFragment<FragmentGame1Binding, Game1ViewModel> implements IGame1Navigator {

    @Inject
    ViewModelProvider.Factory factory;
    int itemCount;
    SharedGame1ViewModel game1ViewModel;

    public static Game1Fragment newInstance() {
        Game1Fragment fragment = new Game1Fragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        mViewModel.setNavigator(this);
        mViewModel.init();
        game1ViewModel = ViewModelProviders.of(getBaseActivity()).get(SharedGame1ViewModel.class);
        game1ViewModel.getModel().observe(getBaseActivity(), bool -> {
            if (bool != null && bool) {
                if (itemCount > mViewDataBinding.viewPager.getCurrentItem() + 1) {
                    mViewDataBinding.viewPager.setCurrentItem(mViewDataBinding.viewPager.getCurrentItem() + 1);
                } else {
                    onGameComplete();
                }
            }
        });
        hideKeyboard();
        return view;
    }

    private void onGameComplete() {
        game1ViewModel.reset();
//        PromptDialog.show(getFragmentManager(),getString(R.string.game1_completed_title),
//                getString(R.string.game1_completed_desc),this::gotoMain);
        YesNoDialog yesNoDialog = YesNoDialog.newInstance(getString(R.string.game1_completed_title), getString(R.string.game1_completed_desc));
        yesNoDialog.setOkRunnable(() -> mViewModel.init());
        yesNoDialog.setCancelRunnable(this::gotoMain);
        yesNoDialog.show(getBaseActivity().getSupportFragmentManager(), YES_NO_DIALOG);

    }


    @Override
    public Game1ViewModel getViewModel() {
        return ViewModelProviders.of(this, factory).get(Game1ViewModel.class);
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_game1;
    }


    @Override
    public void initPager(List<Integer> ids) {
        itemCount = ids.size();
        mViewDataBinding.viewPager.setAdapter(new Game1PageAdapter(getBaseActivity().getSupportFragmentManager(), ids));
        mViewDataBinding.tabLayout.setupWithViewPager(mViewDataBinding.viewPager);
        disableTabLayoutClick();

    }

    @Override
    public void gotoMain() {
        getBaseActivity().addFragment(R.id.fragment_container, DictionaryFragment.newInstance(false));
    }

    private void disableTabLayoutClick() {
        LinearLayout tabStrip = ((LinearLayout) mViewDataBinding.tabLayout.getChildAt(0));
        for (int i = 0; i < tabStrip.getChildCount(); i++) {
            tabStrip.getChildAt(i).setOnTouchListener((v, event) -> true);
        }
    }
}
