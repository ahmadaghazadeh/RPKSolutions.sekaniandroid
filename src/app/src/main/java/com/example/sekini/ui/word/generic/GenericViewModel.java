package com.example.sekini.ui.word.generic;

import com.example.sekini.R;
import com.example.sekini.data.local.db.embedded.IDicDao;
import com.example.sekini.data.local.db.embedded.ISekaniRootDtoDao;
import com.example.sekini.data.local.db.embedded.ISekaniWordDtoDao;
import com.example.sekini.data.local.db.embedded.ISekaniWordExampleDtoDao;
import com.example.sekini.data.model.SekaniWordsEntity;
import com.example.sekini.data.model.embedded.SekaniRootDto;
import com.example.sekini.data.model.embedded.SekaniWordDto;
import com.example.sekini.ui.word.item.rootimage.RootImage;
import com.example.sekini.ui.word.item.word.Word;
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


public class GenericViewModel extends FragmentBaseViewModel<IGenericNavigator> {


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
    public GenericViewModel() {
    }


    public void init(int rootId) {

        getNavigator().showLoadingDialog(R.drawable.ic_dict,commonUtils.getString(R.string.dictionary));
        RunnableMethod<Object, RunnableModel<List<BaseRecyclerView>>> runnableMethod = (param, onProgressUpdate) -> {
            RunnableModel<List<BaseRecyclerView>> runnableModel = new RunnableModel<>();
            try {

                SekaniRootDto sekaniRootDto = sekaniRootDtoDao.getWord(rootId);
                RunnableIn<byte[]> runnableIn= param1 -> getNavigator().showImageDialog(param1);
                RootImage rootImage = new RootImage(sekaniRootDto,runnableIn);

                List<BaseRecyclerView> baseRecyclerViews = new LinkedList<>(rootImage.render());
                for (SekaniWordsEntity entity: sekaniRootDto.getSekaniWords) {
                    SekaniWordDto sekaniWordDto = sekaniWordDtoDao.getWord(entity.id);
                    Word word=new Word(sekaniWordDto,sekaniWordExampleDtoDao);
                    baseRecyclerViews.addAll(word.render());
                }
                runnableModel.setModel(baseRecyclerViews);
                //List<SekaniWordExampleDto> example = sekaniWordExampleDtoDao.getExample(1010);

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
