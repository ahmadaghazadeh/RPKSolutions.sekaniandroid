package com.example.sekini.ui.word.regularverb.fragment;

import com.example.sekini.data.local.db.embedded.IDicDao;
import com.example.sekini.data.local.db.embedded.ISekaniRootDtoDao;
import com.example.sekini.data.local.db.embedded.ISekaniWordDtoDao;
import com.example.sekini.data.local.db.embedded.ISekaniWordExampleDtoDao;
import com.example.sekini.data.model.embedded.SekaniRootDto;
import com.example.sekini.ui.word.item.rootimage.RootImage;
import com.example.sekini.ui.word.item.verb.Verb;
import com.example.sekini.utils.base.fragment.FragmentBaseViewModel;
import com.example.sekini.utils.common.CommonUtils;
import com.example.sekini.utils.common.RunnableIn;
import com.example.sekini.utils.common.RunnableMethod;
import com.example.sekini.utils.common.RunnableModel;
import com.example.sekini.utils.recycler.BaseRecyclerView;

import java.util.LinkedList;
import java.util.List;

import javax.inject.Inject;


public class TenseViewModel extends FragmentBaseViewModel<ITenseNavigator> {


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
    public TenseViewModel() {
    }


    public void init(Verb.TENSE tense, int rootId) {

        RunnableMethod<Object, RunnableModel<List<BaseRecyclerView>>> runnableMethod = (param, onProgressUpdate) -> {
            RunnableModel<List<BaseRecyclerView>> runnableModel = new RunnableModel<>();
            try {
                SekaniRootDto sekaniRootDto = sekaniRootDtoDao.getWord(rootId);
                RunnableIn<byte[]> runnableIn= param1 -> getNavigator().showImageDialog(param1);
                RootImage rootImage = new RootImage(sekaniRootDto,runnableIn);
                List<BaseRecyclerView> baseRecyclerViews = new LinkedList<>(rootImage.render());
                baseRecyclerViews.addAll(new
                        Verb(sekaniWordExampleDtoDao,
                        sekaniWordDtoDao, rootId,
                        tense,
                        Verb.PERSON.First
                ).render());

                baseRecyclerViews.addAll(new
                        Verb(sekaniWordExampleDtoDao,
                        sekaniWordDtoDao, rootId,
                        tense,
                        Verb.PERSON.Second
                ).render());

                baseRecyclerViews.addAll(new
                        Verb(sekaniWordExampleDtoDao,
                        sekaniWordDtoDao, rootId,
                        tense,
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
            getNavigator().dismissLoadingDialog();
        };
        runAsyncTask(runnableMethod, post);

    }


}
