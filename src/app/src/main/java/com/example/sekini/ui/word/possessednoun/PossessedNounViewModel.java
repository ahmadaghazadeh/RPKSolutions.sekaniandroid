package com.example.sekini.ui.word.possessednoun;

import com.example.sekini.data.local.db.embedded.IDicDao;
import com.example.sekini.data.local.db.embedded.ISekaniRootDtoDao;
import com.example.sekini.data.local.db.embedded.ISekaniWordDtoDao;
import com.example.sekini.data.local.db.embedded.ISekaniWordExampleDtoDao;
import com.example.sekini.data.model.embedded.SekaniRootDto;
import com.example.sekini.ui.word.item.attribute.DisjointReference;
import com.example.sekini.ui.word.item.attribute.FirstPerson;
import com.example.sekini.ui.word.item.attribute.Indefinite;
import com.example.sekini.ui.word.item.attribute.Reciprocal;
import com.example.sekini.ui.word.item.attribute.Reflexive;
import com.example.sekini.ui.word.item.attribute.SecondPerson;
import com.example.sekini.ui.word.item.attribute.ThirdPerson;
import com.example.sekini.ui.word.item.rootimage.RootImage;
import com.example.sekini.utils.base.BaseViewModel;
import com.example.sekini.utils.base.fragment.FragmentBaseViewModel;
import com.example.sekini.utils.common.CommonUtils;
import com.example.sekini.utils.common.RunnableIn;
import com.example.sekini.utils.common.RunnableMethod;
import com.example.sekini.utils.common.RunnableModel;
import com.example.sekini.utils.recycler.BaseRecyclerView;

import java.util.LinkedList;
import java.util.List;

import javax.inject.Inject;


public class PossessedNounViewModel extends FragmentBaseViewModel<IPossessedNounNavigator> {


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
    public PossessedNounViewModel() {
    }


    public void init(int rootId) {

        getNavigator().showProgress(false);
        RunnableMethod<Object, RunnableModel<List<BaseRecyclerView>>> runnableMethod = (param, onProgressUpdate) -> {
            RunnableModel<List<BaseRecyclerView>> runnableModel = new RunnableModel<>();
            try {
                SekaniRootDto sekaniRootDto = sekaniRootDtoDao.getWord(rootId);
                RootImage rootImage = new RootImage(sekaniRootDto);
                List<BaseRecyclerView> baseRecyclerViews = new LinkedList<>(rootImage.render());
                baseRecyclerViews.addAll(new FirstPerson(sekaniRootDto, sekaniWordExampleDtoDao, sekaniWordDtoDao).render());
                baseRecyclerViews.addAll(new SecondPerson(sekaniRootDto, sekaniWordExampleDtoDao, sekaniWordDtoDao).render());
                baseRecyclerViews.addAll(new ThirdPerson(sekaniRootDto, sekaniWordExampleDtoDao, sekaniWordDtoDao).render());
                baseRecyclerViews.addAll(new Reflexive(sekaniRootDto, sekaniWordExampleDtoDao, sekaniWordDtoDao).render());
                baseRecyclerViews.addAll(new DisjointReference(sekaniRootDto, sekaniWordExampleDtoDao, sekaniWordDtoDao).render());
                baseRecyclerViews.addAll(new Reciprocal(sekaniRootDto, sekaniWordExampleDtoDao, sekaniWordDtoDao).render());
                baseRecyclerViews.addAll(new Indefinite(sekaniRootDto, sekaniWordExampleDtoDao, sekaniWordDtoDao).render());
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
