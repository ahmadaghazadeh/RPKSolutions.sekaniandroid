package com.example.sekini.ui.mainbottom;

import android.arch.lifecycle.MutableLiveData;

import com.example.sekini.R;
import com.example.sekini.data.IRepository;
import com.example.sekini.data.local.pref.IAppPref;
import com.example.sekini.data.remote.UserInfo;
import com.example.sekini.data.sync.ISyncData;
import com.example.sekini.service.SyncService;
import com.example.sekini.utils.base.BaseViewModel;
import com.example.sekini.utils.common.CommonUtils;
import com.example.sekini.utils.common.RunnableIn;
import com.example.sekini.utils.common.RunnableMethod;
import com.example.sekini.utils.common.RunnableModel;

import javax.inject.Inject;


public class MainBottomViewModel extends BaseViewModel<IMainBottomNavigator> {


    @Inject
    public IRepository repository;

    @Inject
    public IAppPref appPref;

    @Inject
    public ISyncData syncData;

    @Inject
    public CommonUtils commonUtils;

    public MutableLiveData<String> userName = new MutableLiveData<>();
    public MutableLiveData<Integer> loginRes = new MutableLiveData<>();
    public MutableLiveData<Boolean> isLogin = new MutableLiveData<>();

    @Inject
    public MainBottomViewModel() {
    }


    public void init() {

        Runnable syncRun = () -> {
            getNavigator().showProgress(false);
            RunnableMethod<Object, RunnableModel> runnableMethod = (param, onProgressUpdate) -> {
                RunnableModel runnableModel = new RunnableModel();
                try {
                    syncData.syncTables(onProgressUpdate::onProgressUpdate);
                } catch (Exception e) {
                    runnableModel.setException(e);
                }
                return runnableModel;
            };

            RunnableIn<RunnableModel> post = (param) -> {
                if (param.hasError()) {
                    getNavigator().handleError(param.getException());
                } else {
                    getNavigator().toast(R.string.sync_complete);
                    appPref.setInitApp();
                }
                getNavigator().hideProgress();

            };
            runAsyncTask(runnableMethod, post);
        };

        Runnable syncBackgroundRun = () -> {
            if (!commonUtils.isRunningService(SyncService.class)) {
                getNavigator().startSyncService();
            } else {
                getNavigator().toast(R.string.sync_service_is_runnig);
            }

        };

        if (commonUtils.isInternetOn()) {
            if (!appPref.isInitApp()) {
                showYesNoNeutralDialog(R.string.attention, R.string.prompt_sync_message,
                        R.string.sync, R.string.sync_background, R.string.cancel, syncRun, syncBackgroundRun, null);
            }

        } else {
            Runnable runnable = this::init;
            getNavigator().snackBar(commonUtils.getString(R.string.no_internet_connection),
                    commonUtils.getString(R.string.try_sync), runnable);
        }

        userName.setValue(appPref.getUserName());
        isLogin.setValue(appPref.isLogin());
        loginRes.setValue(!appPref.isLogin() ? R.drawable.ic_login : R.drawable.ic_logout);
    }

    public void onClickLogin() {
        getNavigator().login();
    }

    public void resetLife() {
        RunnableIn<RunnableModel<Object>> post = (param) -> {
            if (param.hasError()) {
                getNavigator().handleError(param.getException());
            }
            getNavigator().dismissLoadingDialog();
            getNavigator().toast("5 Life Added");
        };
        runDialogAsyncTask((param, onProgressUpdate) -> {
            RunnableModel<Object> runnableModel = new RunnableModel<>();
            try {
                int tempScore = 5;
                appPref.setLife(tempScore);
                UserInfo ss = repository.putLife(appPref.getToken(), tempScore);

            } catch (Exception e) {
                runnableModel.setException(e);
            }
            return runnableModel;

        }, post);
    }
}
