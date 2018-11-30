package com.example.sekini.ui.games.game1.fragment;

import android.arch.lifecycle.MutableLiveData;
import android.text.TextUtils;

import com.example.sekini.R;
import com.example.sekini.data.IRepository;
import com.example.sekini.data.local.db.ISekaniRootImagesDao;
import com.example.sekini.data.local.db.IUserFailedWordDao;
import com.example.sekini.data.local.db.IUserLearnedWordDao;
import com.example.sekini.data.local.db.embedded.ISekaniWordAudioDtoDao;
import com.example.sekini.data.local.db.embedded.ISekaniWordDtoDao;
import com.example.sekini.data.local.pref.IAppPref;
import com.example.sekini.data.model.SekaniRootImagesEntity;
import com.example.sekini.data.model.UserFailedWord;
import com.example.sekini.data.model.UserLearnedWord;
import com.example.sekini.data.model.embedded.SekaniWordAudioDto;
import com.example.sekini.data.model.embedded.SekaniWordDto;
import com.example.sekini.data.remote.Token;
import com.example.sekini.utils.base.fragment.FragmentBaseViewModel;
import com.example.sekini.utils.common.CommonUtils;
import com.example.sekini.utils.common.RunnableIn;
import com.example.sekini.utils.common.RunnableMethod;
import com.example.sekini.utils.common.RunnableModel;

import java.util.LinkedList;
import java.util.List;

import javax.inject.Inject;


public class Game1ItemViewModel extends FragmentBaseViewModel<IGame1ItemNavigator> {

    public MutableLiveData<List<SekaniRootImagesEntity>> images = new MutableLiveData<>();
    public MutableLiveData<List<Integer>> drawableRes = new MutableLiveData<>();
    public MutableLiveData<String> word = new MutableLiveData<>();
    public MutableLiveData<String> score = new MutableLiveData<>();
    public MutableLiveData<Integer> life = new MutableLiveData<>();
    public MutableLiveData<Integer> backgroundTop = new MutableLiveData<>();
    public MutableLiveData<Boolean> isSelected = new MutableLiveData<>();
    private Integer[] pentagon = new Integer[]{R.drawable.pentagon1,
            R.drawable.pentagon2,
            R.drawable.pentagon3,
            R.drawable.pentagon4,
            R.drawable.pentagon5};

    private int answerIndex = -1;

    @Inject
    public CommonUtils commonUtils;

    @Inject
    public IAppPref appPref;

    @Inject
    public ISekaniRootImagesDao sekaniRootImagesDao;

    @Inject
    public IUserLearnedWordDao userLearnedWordDao;

    @Inject
    public IUserFailedWordDao userFailedWordDao;

    @Inject
    public ISekaniWordDtoDao sekaniWordDtoDao;

    @Inject
    public ISekaniWordAudioDtoDao sekaniWordAudioDtoDao;

    @Inject
    public IRepository repository;

    public SekaniWordDto wordDto;


    @Inject
    public Game1ItemViewModel() {

        backgroundTop.postValue(R.drawable.border_game_header);
        isSelected.postValue(false);

    }

    public void init(int sekaniWordId) {
        RunnableMethod<Object, RunnableModel<Token>> runnableMethod = (param, onProgressUpdate) -> {
            RunnableModel<Token> runnableModel = new RunnableModel<>();
            try {
                score.postValue(""+appPref.getScore());
                life.postValue(pentagon[5-appPref.getLife()]);
                wordDto = sekaniWordDtoDao.getWord(sekaniWordId);
                List<SekaniRootImagesEntity> sekaniRootImagesEntities = sekaniRootImagesDao.getRandom(wordDto.sekaniWordsEntity.sekaniRootId);
                word.postValue(wordDto.sekaniWordsEntity.word);
                images.postValue(sekaniRootImagesEntities);
                if (images.getValue() != null) {
                    setSelectedItem(-1);
                }
            } catch (Exception e) {
                runnableModel.setException(e);
            }
            return runnableModel;
        };

        runAsyncTask(runnableMethod, null);

    }

    private void setSelectedItem(int selectedIndex) {
        if (images.getValue() != null) {
            List<Integer> lstRes = new LinkedList<>();
            for (int i = 0; i < images.getValue().size(); i++) {
                if (selectedIndex == i)
                    lstRes.add(R.drawable.border_game_image_selected);
                else
                    lstRes.add(R.drawable.border_game_image);
            }
            drawableRes.postValue(lstRes);
            if (isSelected.getValue() != null && !isSelected.getValue()) {
                backgroundTop.postValue(R.drawable.border_game_header_filled);
                isSelected.postValue(true);
            }
        }
    }

    public void selectImage(int selectedId) {
        answerIndex = selectedId;
        if (images.getValue() != null) {
            setSelectedItem(selectedId);
        }
    }

    public void playWordSound() {
        CommonUtils.PlayAudio(wordDto.audio);
    }

    public void check() {
        if(answerIndex==-1){
            getNavigator().toast("Please Select");
            return;
        }

        if (images.getValue() != null && images.getValue().get(answerIndex).sekaniRootId == wordDto.sekaniWordsEntity.sekaniRootId) {
            UserLearnedWord userLearnedWord = new UserLearnedWord();
            userLearnedWord.sekaniWordId = wordDto.sekaniWordsEntity.id;
            userLearnedWord.userId = appPref.getUserId();
            userLearnedWord.updateTime = CommonUtils.getDateTime();
            userLearnedWord.id = userLearnedWordDao.getMaxId() + 1;
            userLearnedWordDao.insert(userLearnedWord);

            SekaniWordAudioDto sekaniWordAudioDto= sekaniWordAudioDtoDao.getWordRootId(images.getValue().get(answerIndex).sekaniRootId);
            getNavigator().correctDialog(sekaniWordAudioDto.sekaniWord,sekaniWordAudioDto.englishWord,sekaniWordAudioDto.audio);
        } else {
            UserFailedWord userFailedWord = new UserFailedWord();
            userFailedWord.sekaniWordId = wordDto.sekaniWordsEntity.id;
            userFailedWord.userId = appPref.getUserId();
            userFailedWord.updateTime = CommonUtils.getDateTime();
            userFailedWord.id = userLearnedWordDao.getMaxId() + 1;
            userFailedWordDao.insert(userFailedWord);

            SekaniWordAudioDto sekaniWordAudioDto= sekaniWordAudioDtoDao.getWordRootId(images.getValue().get(answerIndex).sekaniRootId);
            getNavigator().incorrectDialog(sekaniWordAudioDto.sekaniWord,sekaniWordAudioDto.englishWord,sekaniWordAudioDto.audio);
        }
    }

}
