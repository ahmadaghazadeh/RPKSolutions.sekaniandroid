package com.example.sekini.ui.games.game1;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.sekini.BR;
import com.example.sekini.R;
import com.example.sekini.databinding.FragmentGame1Binding;
import com.example.sekini.ui.dictionary.DictionaryFragment;
import com.example.sekini.ui.games.FeedBack;
import com.example.sekini.ui.main.fragment.MainFragment;
import com.example.sekini.utils.base.dialog.YesNoDialog.YesNoDialog;
import com.example.sekini.utils.base.fragment.BaseFragment;
import com.example.sekini.utils.common.CommonUtils;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import static com.example.sekini.utils.base.activity.BaseActivity.YES_NO_DIALOG;

public class Game1Fragment extends BaseFragment<FragmentGame1Binding, Game1ViewModel> implements IGame1Navigator {

    @Inject
    CommonUtils commonUtils;
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
        Bundle bundle=getArguments();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        mViewModel.setNavigator(this);
        showLoadingDialog(R.drawable.ic_game1,getString(R.string.word_matcher));
        mViewModel.init();
        game1ViewModel = ViewModelProviders.of(getBaseActivity()).get(SharedGame1ViewModel.class);


        hideKeyboard();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        game1ViewModel.getNext().observe(getBaseActivity(), bool -> {
            if (itemCount >0 && bool != null && bool) {
                if ( itemCount > mViewDataBinding.viewPager.getCurrentItem() + 1) {
                    mViewDataBinding.viewPager.setCurrentItem(mViewDataBinding.viewPager.getCurrentItem() + 1);
                } else {
                    onGameComplete();
                }
            }
        });



        game1ViewModel.getFeedBack().observe(getBaseActivity(), lst -> {
            try {

                if(lst==null)
                    return;
                LinearLayout tabStrip = ((LinearLayout) mViewDataBinding.tabLayout.getChildAt(0));
                for (int i = 0; i < tabStrip.getChildCount(); i++) {
                    switch (lst.get(i)){
                        case False:
                            tabStrip.getChildAt(i).setBackground(getResources().getDrawable(R.drawable.selector_red_dot));
                            break;
                        case True:
                            tabStrip.getChildAt(i).setBackground(getResources().getDrawable(R.drawable.selector_green_dot));
                            break;
                        case None:
                            tabStrip.getChildAt(i).setBackground(getResources().getDrawable(R.drawable.selector_default_dot));
                            break;
                    }

                }
            }catch (Exception ex){
                ex.getMessage();
            }

        });
    }

    private void onGameComplete() {
        if(isAdded()){
            game1ViewModel.reset();
            YesNoDialog yesNoDialog = YesNoDialog.newInstance(
                    commonUtils.getString(R.string.game1_completed_title),
                    commonUtils.getString(R.string.game1_completed_desc));
            yesNoDialog.setOkRunnable(() -> mViewModel.init());
            yesNoDialog.setCancelRunnable(this::gotoMain);
            yesNoDialog.show(getBaseActivity().getSupportFragmentManager(), YES_NO_DIALOG);
        }
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

        List<FeedBack> feedBacks=new ArrayList<>();
        for (int i = 0; i < itemCount; i++) {
            feedBacks.add(FeedBack.None);
        }
        game1ViewModel.getFeedBack().setValue(feedBacks);

        mViewDataBinding.viewPager.setAdapter(new Game1PageAdapter(getBaseActivity().getSupportFragmentManager(), ids));
        mViewDataBinding.tabLayout.setupWithViewPager(mViewDataBinding.viewPager);
        disableTabLayoutClick();

    }

    @Override
    public void gotoMain() {
        getBaseActivity().addFragment(R.id.fragment_container, MainFragment.newInstance());
    }

    private void disableTabLayoutClick() {
        LinearLayout tabStrip = ((LinearLayout) mViewDataBinding.tabLayout.getChildAt(0));
        for (int i = 0; i < tabStrip.getChildCount(); i++) {
            tabStrip.getChildAt(i).setOnTouchListener((v, event) -> true);
        }
    }
}
