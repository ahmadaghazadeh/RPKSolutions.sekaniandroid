package com.example.sekini.ui.dictionary;

import com.example.sekini.data.local.db.embedded.IDicDao;
import com.example.sekini.data.sync.ISyncData;
import com.example.sekini.ui.dictionary.dic.EnglishDicItem;
import com.example.sekini.ui.dictionary.dic.SekaniDicItem;
import com.example.sekini.utils.base.BaseViewModel;
import com.example.sekini.utils.base.fragment.FragmentBaseViewModel;
import com.example.sekini.utils.common.CommonUtils;
import com.example.sekini.utils.common.Converter;
import com.example.sekini.utils.common.RunnableIn;
import com.example.sekini.utils.common.RunnableMethod;
import com.example.sekini.utils.common.RunnableModel;
import com.example.sekini.utils.recycler.BaseRecyclerView;

import java.util.List;

import javax.inject.Inject;

import belka.us.androidtoggleswitch.widgets.BaseToggleSwitch;


public class DictionaryViewModel extends FragmentBaseViewModel<IDictionaryNavigator> implements EnglishDicItem.IDicListener, SekaniDicItem.IDicListener {

    private boolean isEnglish = true;


    @Inject
    public ISyncData syncData;
    @Inject
    public CommonUtils commonUtils;

    @Inject
    public IDicDao sekaniWordsDao;

    private CharSequence lastWord = "";


    @Inject
    public DictionaryViewModel() {
    }


    public void init(boolean isEnglish) {

        this.isEnglish = isEnglish;

        findWord(lastWord);
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
                runnableModel.setModel(Converter.toDicEnglishWord(
                        sekaniWordsDao.likeEnglish(CommonUtils.getFirstLikeString(s), CommonUtils.getLikeString(s)),
                        DictionaryViewModel.this));
            } else {
                runnableModel.setModel(Converter.toDicSekaniWord(
                        sekaniWordsDao.likeSekani(CommonUtils.getFirstLikeString(s), CommonUtils.getLikeString(s)),
                        DictionaryViewModel.this));
            }
            return runnableModel;
        };

        RunnableIn<RunnableModel<List<BaseRecyclerView>>> post = (param) -> getNavigator().init(param.getModel());
        runAsyncTask(runnableMethod, post);

    }

    @Override
    public void onClick(EnglishDicItem itemSample) {
        gotoWord(itemSample.englishDicDto.sekaniFormsEntity.id, itemSample.englishDicDto.sekaniRootId);

    }

    public void onClickMenu() {
        getNavigator().openDrawer();
    }

    @Override
    public void onClick(SekaniDicItem itemSample) {
        gotoWord(itemSample.sekaniDicDto.sekaniFormsEntity.id, itemSample.sekaniDicDto.sekaniWordsEntity.sekaniRootId);
    }

    private void gotoWord(int sekaniForm, int sekaniRootId) {
        switch (sekaniForm) {
            case 13://Generic
                getNavigator().startGenericWord(sekaniRootId);
                break;
            case 12://Impersonal Verb
                getNavigator().startImpersonalVerb(sekaniRootId);
                break;
            case 9://Regular Verb
                getNavigator().startRegularVerb(sekaniRootId);
                break;
            case 6://Possessed Noun
                getNavigator().startPossessedNoun(sekaniRootId);
                break;
            default:
                getNavigator().startGenericWord(sekaniRootId);
        }
    }
}
