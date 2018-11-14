package com.example.sekini.ui.dictionary;

import com.example.sekini.R;
import com.example.sekini.data.local.db.embedded.ISekaniEnglishDicDao;
import com.example.sekini.data.sync.ISyncData;
import com.example.sekini.service.SyncService;
import com.example.sekini.ui.dictionary.dic.EnglishDicItem;
import com.example.sekini.utils.base.BaseViewModel;
import com.example.sekini.utils.common.CommonUtils;
import com.example.sekini.utils.common.Converter;
import com.example.sekini.utils.common.RunnableIn;
import com.example.sekini.utils.common.RunnableMethod;
import com.example.sekini.utils.common.RunnableModel;
import com.example.sekini.utils.recycler.BaseRecyclerView;

import java.util.List;

import javax.inject.Inject;

import belka.us.androidtoggleswitch.widgets.BaseToggleSwitch;


public class MainViewModel extends BaseViewModel<IMainNavigator> implements EnglishDicItem.IDicListener {

    private boolean isEnglish = true;


    @Inject
    public ISyncData syncData;
    @Inject
    public CommonUtils commonUtils;

    @Inject
    public ISekaniEnglishDicDao sekaniWordsDao;

    private CharSequence lastWord = "";


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
            showYesNoNeutralDialog(R.string.attention, R.string.prompt_sync_message,
                    R.string.sync, R.string.sync_background, R.string.cancel, syncRun, syncBackgroundRun, null);
        } else {
            Runnable runnable = this::init;
            getNavigator().snackBar(commonUtils.getString(R.string.no_internet_connection),
                    commonUtils.getString(R.string.try_sync), runnable);
        }

    }

    public BaseToggleSwitch.OnToggleSwitchChangeListener onToggleSwitchChangeListener = new BaseToggleSwitch.OnToggleSwitchChangeListener() {
        @Override
        public void onToggleSwitchChangeListener(int position, boolean isChecked) {
            isEnglish = position == 0;
            findWord(lastWord);
        }
    };

    public void onTextChanged(CharSequence s, int start, int before, int count) {
        findWord(s);
        lastWord = s;
    }

    private void findWord(CharSequence s) {

        RunnableMethod<Object, RunnableModel<List<BaseRecyclerView>>> runnableMethod = (param, onProgressUpdate) -> {
            RunnableModel<List<BaseRecyclerView>> runnableModel = new RunnableModel<>();
            if (isEnglish) {
                runnableModel.setModel(Converter.toDicEnglishWord(sekaniWordsDao.likeEnglish(commonUtils.getLikeString(s)), MainViewModel.this));
            } else {
                runnableModel.setModel(Converter.toDicEnglishWord(sekaniWordsDao.likeSekani(commonUtils.getLikeString(s)), MainViewModel.this));
            }
            return runnableModel;
        };

        RunnableIn<RunnableModel<List<BaseRecyclerView>>> post = (param) -> getNavigator().init(param.getModel());
        runAsyncTask(runnableMethod, post);

    }

    @Override
    public void onClick(EnglishDicItem itemSample) {
        switch (itemSample.sekaniEnglishDicDto.sekaniFormsEntity.id) {
            case 13://Generic
                getNavigator().startGenericWord(itemSample.sekaniEnglishDicDto);
                break;
            case 12://Impersonal Verb
                getNavigator().startImpersonalVerb(itemSample.sekaniEnglishDicDto);
                break;
            case 9://Regular Verb
                getNavigator().startRegularVerb(itemSample.sekaniEnglishDicDto);
                break;
            case 6://Possessed Noun
                getNavigator().startPossessedNoun(itemSample.sekaniEnglishDicDto);
                break;

            default:
                getNavigator().startGenericWord(itemSample.sekaniEnglishDicDto);
        }

    }


}
