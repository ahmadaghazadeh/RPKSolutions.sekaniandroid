package com.example.sekini.ui.games.game1;

import com.example.sekini.R;
import com.example.sekini.data.IRepository;
import com.example.sekini.data.local.db.ISekaniWordsDao;
import com.example.sekini.data.local.db.embedded.ISekaniWordDtoDao;
import com.example.sekini.data.local.pref.IAppPref;
import com.example.sekini.data.remote.Token;
import com.example.sekini.utils.base.fragment.FragmentBaseViewModel;
import com.example.sekini.utils.common.CommonUtils;
import com.example.sekini.utils.common.RunnableIn;
import com.example.sekini.utils.common.RunnableMethod;
import com.example.sekini.utils.common.RunnableModel;
import com.example.sekini.utils.exception.BaseException;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;


public class Game1ViewModel extends FragmentBaseViewModel<IGame1Navigator> {

    @Inject
    public IAppPref appPref;
    @Inject
    public CommonUtils commonUtils;

    @Inject
    public ISekaniWordDtoDao sekaniWordDtoDao;

    @Inject
    public IRepository repository;

    @Inject
    public ISekaniWordsDao sekaniWordsDao;

    @Inject
    @Named("game1PageCount")
    public int game1PageCount;

    @Inject
    public Game1ViewModel() {
    }

    public void init() {
        RunnableMethod<Object, RunnableModel<List<Integer>>> runnableMethod = (param, onProgressUpdate) -> {
            RunnableModel<List<Integer>> runnableModel = new RunnableModel<>();
            try {
                List<Integer> ids = sekaniWordsDao.game1WordId(game1PageCount);
                runnableModel.setModel(ids);

                appPref.setScore(Integer.parseInt(repository.getScore(appPref.getToken()).value));
                appPref.setLife(Integer.parseInt(repository.getLife(appPref.getToken()).value));
            } catch (Exception e) {
                runnableModel.setException(e);
            }
            return runnableModel;
        };
        RunnableIn<RunnableModel<List<Integer>>> post = (param) -> {
            getNavigator().dismissLoadingDialog();
            if (param.hasError()) {
                if(param.getException()!=null && ((BaseException)param.getException()).getSystemMessage().equals("Unauthorized")){
                    getNavigator().snackBar(R.string.please_login_again);
                }else{
                    getNavigator().snackBar(R.string.no_internet_connection);
                }
                getNavigator().snackBar(R.string.no_internet_connection);
                return;
            }
            if (param.getModel().size() >= 1) {
                getNavigator().initPager(param.getModel());
            } else {
                getNavigator().showYesNoDialog("End Game", "You completed all- levels", () -> getNavigator().gotoMain(), null);
            }
        };
        if (appPref.getLife() == 0) {
            getNavigator().showPromptDialog(R.string.no_life,
                    R.string.please_come_back_tomorrow, () -> {
                        getNavigator().dismissLoadingDialog();
                        getNavigator().gotoMain();
                    }
            );
        } else if (commonUtils.isInternetOn()) {
            runAsyncTaskWithOutException(runnableMethod, post);
        } else {
            getNavigator().showYesNoDialog(R.string.internet_disconnects,
                    R.string.check_internet_try_again,
                    R.string.cancel,
                    R.string.try_again,
                    () -> {
                        getNavigator().dismissLoadingDialog();
                        init();
                    }, () -> {
                        getNavigator().dismissLoadingDialog();
                        getNavigator().gotoMain();
                    });

        }


    }


}
