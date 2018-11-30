package com.example.sekini.ui.main;

import com.example.sekini.R;
import com.example.sekini.data.local.db.embedded.IDicDao;
import com.example.sekini.data.local.db.embedded.ISekaniRootDtoDao;
import com.example.sekini.data.local.db.embedded.ISekaniWordDtoDao;
import com.example.sekini.data.local.db.embedded.ISekaniWordExampleDtoDao;
import com.example.sekini.data.local.pref.IAppPref;
import com.example.sekini.data.model.SekaniWordsEntity;
import com.example.sekini.data.model.embedded.SekaniRootDto;
import com.example.sekini.data.model.embedded.SekaniWordDto;
import com.example.sekini.data.sync.ISyncData;
import com.example.sekini.service.SyncService;
import com.example.sekini.ui.word.item.rootimage.RootImage;
import com.example.sekini.ui.word.item.word.Word;
import com.example.sekini.utils.base.BaseViewModel;
import com.example.sekini.utils.common.CommonUtils;
import com.example.sekini.utils.common.RunnableIn;
import com.example.sekini.utils.common.RunnableMethod;
import com.example.sekini.utils.common.RunnableModel;
import com.example.sekini.utils.recycler.BaseRecyclerView;

import java.util.LinkedList;
import java.util.List;

import javax.inject.Inject;


public class MainViewModel extends BaseViewModel<IMainNavigator> {


    @Inject
    public IAppPref appPref;

    @Inject
    public ISyncData syncData;

    @Inject
    public CommonUtils commonUtils;

    @Inject
    public IDicDao sekaniWordsDao;

    @Inject
    public ISekaniRootDtoDao sekaniRootDtoDao;

    @Inject
    public ISekaniWordDtoDao sekaniWordDtoDao;

    @Inject
    public ISekaniWordExampleDtoDao sekaniWordExampleDtoDao;


    @Inject
    public MainViewModel() {
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
            if(!appPref.isInitApp()){
                showYesNoNeutralDialog(R.string.attention, R.string.prompt_sync_message,
                        R.string.sync, R.string.sync_background, R.string.cancel, syncRun, syncBackgroundRun, null);
            }

        } else {
            Runnable runnable = this::init;
            getNavigator().snackBar(commonUtils.getString(R.string.no_internet_connection),
                    commonUtils.getString(R.string.try_sync), runnable);
        }

    }


}
