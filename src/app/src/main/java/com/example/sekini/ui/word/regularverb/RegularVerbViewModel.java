package com.example.sekini.ui.word.regularverb;

import com.example.sekini.data.local.db.embedded.ISekaniEnglishDicDao;
import com.example.sekini.data.local.db.embedded.ISekaniRootDtoDao;
import com.example.sekini.data.local.db.embedded.ISekaniWordDtoDao;
import com.example.sekini.data.local.db.embedded.ISekaniWordExampleDtoDao;
import com.example.sekini.data.model.embedded.SekaniRootDto;
import com.example.sekini.ui.word.item.rootimage.RootImage;
import com.example.sekini.ui.word.item.verb.Verb;
import com.example.sekini.utils.base.BaseViewModel;
import com.example.sekini.utils.common.CommonUtils;
import com.example.sekini.utils.common.RunnableIn;
import com.example.sekini.utils.common.RunnableMethod;
import com.example.sekini.utils.common.RunnableModel;
import com.example.sekini.utils.recycler.BaseRecyclerView;

import java.util.LinkedList;
import java.util.List;

import javax.inject.Inject;


public class RegularVerbViewModel extends BaseViewModel<IRegularVerbNavigator> {


    @Inject
    public CommonUtils commonUtils;

    @Inject
    public ISekaniEnglishDicDao sekaniWordsDao;

    @Inject
    public ISekaniRootDtoDao sekaniRootDtoDao;

    @Inject
    public ISekaniWordDtoDao sekaniWordDtoDao;

    @Inject
    public ISekaniWordExampleDtoDao sekaniWordExampleDtoDao;


    @Inject
    public RegularVerbViewModel() {
    }


    public void init(int rootId) {

        getNavigator().showProgress(false);
        RunnableMethod<Object, RunnableModel<List<BaseRecyclerView>>> runnableMethod = (param, onProgressUpdate) -> {
            RunnableModel<List<BaseRecyclerView>> runnableModel = new RunnableModel<>();
            try {
                SekaniRootDto sekaniRootDto = sekaniRootDtoDao.getWord(rootId);
                RootImage rootImage = new RootImage(sekaniRootDto);
                List<BaseRecyclerView> baseRecyclerViews = new LinkedList<>(rootImage.render());
                baseRecyclerViews.addAll(new
                        Verb(sekaniWordExampleDtoDao,
                        sekaniWordDtoDao,
                        Verb.TENSE.Imperfective,
                        Verb.PERSON.First
                ).render());

                baseRecyclerViews.addAll(new
                        Verb(sekaniWordExampleDtoDao,
                        sekaniWordDtoDao,
                        Verb.TENSE.Imperfective,
                        Verb.PERSON.Second
                ).render());

                baseRecyclerViews.addAll(new
                        Verb(sekaniWordExampleDtoDao,
                        sekaniWordDtoDao,
                        Verb.TENSE.Imperfective,
                        Verb.PERSON.Third
                ).render());
                runnableModel.setModel(baseRecyclerViews);
            } catch (Exception e) {
                runnableModel.setException(e);
            }
            return runnableModel;
        };
        RunnableIn<RunnableModel<List<BaseRecyclerView>>> post = (param) -> {
            if (param.hasError()) {
                getNavigator().handleError(param.getException());
            } else {
                getNavigator().init(param.getModel());
            }
            getNavigator().hideProgress();

        };
        runAsyncTask(runnableMethod, post);

    }


}