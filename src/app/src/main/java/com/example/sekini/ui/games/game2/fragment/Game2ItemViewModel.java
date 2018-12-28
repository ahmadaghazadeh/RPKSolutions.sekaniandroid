package com.example.sekini.ui.games.game2.fragment;

import android.arch.lifecycle.MutableLiveData;

import com.example.sekini.R;
import com.example.sekini.data.IRepository;
import com.example.sekini.data.local.db.IUserFailedWordDao;
import com.example.sekini.data.local.db.IUserLearnedWordDao;
import com.example.sekini.data.local.db.embedded.IGame2DtoDao;
import com.example.sekini.data.local.pref.IAppPref;
import com.example.sekini.data.model.embedded.Game2Dto;
import com.example.sekini.data.remote.Token;
import com.example.sekini.data.remote.api.IApi;
import com.example.sekini.utils.base.fragment.FragmentBaseViewModel;
import com.example.sekini.utils.common.CommonUtils;
import com.example.sekini.utils.common.Converter;
import com.example.sekini.utils.common.RunnableIn;
import com.example.sekini.utils.common.RunnableMethod;
import com.example.sekini.utils.common.RunnableModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.function.BiPredicate;

import javax.inject.Inject;
import javax.inject.Named;


public class Game2ItemViewModel extends FragmentBaseViewModel<IGame2ItemNavigator> {

    public MutableLiveData<List<Game2Dto>> question = new MutableLiveData<>();
    public MutableLiveData<List<Game2Dto>> answer = new MutableLiveData<>();
    public MutableLiveData<List<Integer>> lamps = new MutableLiveData<>();
    public MutableLiveData<String> score = new MutableLiveData<>();
    public MutableLiveData<Integer> life = new MutableLiveData<>();
    public MutableLiveData<Boolean> isSelected = new MutableLiveData<>();
    public MutableLiveData<Integer> backgroundTop = new MutableLiveData<>();
    public MutableLiveData<Integer> textColor = new MutableLiveData<>();
    public MutableLiveData<String> title = new MutableLiveData<>();
    public MutableLiveData<String> point = new MutableLiveData<>();
    public MutableLiveData<Boolean> canNext = new MutableLiveData<>();

    private int index;
    private int attempt = 0;

    @Inject
    @Named("scoreCorrectPerfectGame2")
    public int scoreCorrectPerfectGame2;


    @Inject
    @Named("scoreCorrectGame2")
    public int scoreCorrectGame2;

    @Inject
    public IAppPref appPref;


    @Inject
    public CommonUtils commonUtils;


    @Inject
    public IUserLearnedWordDao userLearnedWordDao;

    @Inject
    public IUserFailedWordDao userFailedWordDao;

    @Inject
    public IRepository repository;

    @Inject
    public IGame2DtoDao game2DtoDao;


    @Inject
    public Game2ItemViewModel() {
        backgroundTop.postValue(R.drawable.border_game2_header_grey);
        isSelected.postValue(false);
    }

    public void setScores() {
        score.postValue("" + appPref.getScore());
        life.postValue(commonUtils.getLifeResId(appPref.getLife()));
    }

    public void init(int index) {
        this.index = index;
        title.setValue(commonUtils.getString(R.string.quick_minds));
        point.setValue(commonUtils.getString(R.string.match_the_words));
        canNext.setValue(false);
        textColor.setValue(commonUtils.getContext().getResources().getColor(R.color.text_game2_grey));
        List<Integer> tempLamp = Arrays.asList(R.drawable.lamp4, R.drawable.lamp4, R.drawable.lamp4);
        lamps.postValue(tempLamp);
        RunnableMethod<Object, RunnableModel<Token>> runnableMethod = (param, onProgressUpdate) -> {
            RunnableModel<Token> runnableModel = new RunnableModel<>();
            try {
                setScores();
                List<Game2Dto> words = getWords();
                question.postValue(words);
                answer.postValue(shuffle(words));

            } catch (Exception e) {
                runnableModel.setException(e);
            }
            return runnableModel;
        };

        RunnableIn<RunnableModel<Object>> post = (param) -> {
//            if (param.hasError()) {
//                getNavigator().handleError(param.getException());
//            }
            getNavigator().dismissLoadingDialog();

        };

        if (appPref.getLife() == 0) {
            getNavigator().showPromptDialog(R.string.no_life,
                    R.string.please_come_back_tomorrow, () -> {
                        getNavigator().gotoMain();
                    }
            );
        } else if (commonUtils.isInternetOn()) {
            runAsyncTask(runnableMethod, post);
        } else {

            getNavigator().showYesNoDialog(R.string.internet_disconnects,
                    R.string.check_internet_try_again,
                    R.string.try_again,
                    R.string.cancel,
                    () -> init(index), () -> getNavigator().gotoMain());


        }

    }

    public void changeAnswer(int answerId) {
        List<Game2Dto> tempAnswer = answer.getValue();
        if (tempAnswer != null && tempAnswer.get(answerId) != null) {
            tempAnswer.get(answerId).answeredColor = (tempAnswer.get(answerId).answeredColor + 1) % 3;
        }
        answer.postValue(tempAnswer);
    }


    public void check() {
        if (canNext.getValue() != null && canNext.getValue()) {
            getNavigator().next();
        } else {
            int lostCount = 0;
            List<Integer> lstLamps = new ArrayList<>();
            if (question.getValue() != null && answer.getValue() != null && lamps.getValue() != null) {
                for (int i = 0; i < question.getValue().size(); i++) {
                    for (int j = 0; j < answer.getValue().size(); j++) {
                        if (question.getValue().get(i).englishWordsEntity.id == answer.getValue().get(j).englishWordsEntity.id) {
                            boolean flag = question.getValue().get(i).questionColor == answer.getValue().get(j).answeredColor;
                            int resLamp = flag ? R.drawable.lamp4 : R.drawable.lamp1;
                            lostCount += !flag ? 1 : 0;
                            lstLamps.add(resLamp);
                        }
                    }
                }
            }
            lamps.setValue(lstLamps);
            if (lostCount > 0) {
                attempt++;
                if (attempt == 2)
                    lose();
                else
                    tryAgain();

            } else {
                win();
                canNext.setValue(true);
            }
        }

    }

    private void win() {
        int score;
        if (attempt < 1) {
            title.setValue(commonUtils.getString(R.string.correct));
            point.setValue(commonUtils.getString(R.string.attempt_one_point));
            score = appPref.getScore() + scoreCorrectPerfectGame2;
        } else {
            title.setValue(commonUtils.getString(R.string.correct));
            point.setValue(commonUtils.getString(R.string.attempt_two_point));
            score = appPref.getScore() + scoreCorrectGame2;
        }

        int finalScore = score;

        if (commonUtils.isInternetOn()) {
            RunnableIn<RunnableModel<Object>> post = (param) -> {
                if (param.hasError()) {
                    getNavigator().handleError(param.getException());
                }
                getNavigator().dismissLoadingDialog();

            };
            runDialogAsyncTask((param, onProgressUpdate) -> {
                RunnableModel<Object> runnableModel = new RunnableModel<>();
                try {
                    appPref.setScore(finalScore);
                    repository.putScore(appPref.getToken(), finalScore);
                    if (question.getValue() != null) {
                        repository.setLearntWords(appPref.getToken(), "" + question.getValue().get(0).sekaniWordsEntity.id);
                        repository.setLearntWords(appPref.getToken(), "" + question.getValue().get(1).sekaniWordsEntity.id);
                        repository.setLearntWords(appPref.getToken(), "" + question.getValue().get(2).sekaniWordsEntity.id);
                    }

                } catch (Exception e) {
                    runnableModel.setException(e);
                }
                return runnableModel;


            }, post);

        } else {

            getNavigator().showYesNoDialog(R.string.internet_disconnects,
                    R.string.check_internet_try_again,
                    R.string.try_again,
                    R.string.cancel,
                    this::win, () -> getNavigator().gotoMain());


        }


        textColor.setValue(commonUtils.getContext().getResources().getColor(R.color.text_game2_green));
        backgroundTop.setValue(R.drawable.border_game2_header_green);
        canNext.setValue(true);
        getNavigator().win();
    }

    private void lose() {
        title.setValue(commonUtils.getString(R.string.incorrect));
        point.setValue(commonUtils.getString(R.string.attempt_two));
        textColor.setValue(commonUtils.getContext().getResources().getColor(R.color.text_game2_red));
        backgroundTop.setValue(R.drawable.border_game2_header_red);
        canNext.setValue(true);
        getNavigator().lose();

        if (commonUtils.isInternetOn()) {

            RunnableIn<RunnableModel<Object>> post = (param) -> {
                if (param.hasError()) {
                    getNavigator().handleError(param.getException());
                }
                getNavigator().dismissLoadingDialog();

            };
            runDialogAsyncTask((param, onProgressUpdate) -> {
                RunnableModel<Object> runnableModel = new RunnableModel<>();
                try {
                    int tempLife = appPref.getLife() - 1;
                    appPref.setLife(tempLife);
                    repository.putLife(appPref.getToken(), tempLife);
                    if (question.getValue() != null) {
                        repository.setFailedWords(appPref.getToken(), "" + question.getValue().get(0).sekaniWordsEntity.id);
                        repository.setFailedWords(appPref.getToken(), "" + question.getValue().get(1).sekaniWordsEntity.id);
                        repository.setFailedWords(appPref.getToken(), "" + question.getValue().get(2).sekaniWordsEntity.id);
                    }
                } catch (Exception e) {
                    runnableModel.setException(e);
                }
                return runnableModel;

            }, post);


        } else {


            getNavigator().showYesNoDialog(R.string.internet_disconnects,
                    R.string.check_internet_try_again,
                    R.string.try_again,
                    R.string.cancel,
                    this::lose, () -> getNavigator().gotoMain());
        }


    }

    private void tryAgain() {
        title.setValue(commonUtils.getString(R.string.try_again));
        point.setValue(commonUtils.getString(R.string.attempt_one));
        if (answer.getValue() != null) {
            List<Game2Dto> temp = answer.getValue();
            for (Game2Dto game2Dto : temp) {
                game2Dto.answeredColor = 3;
            }
            answer.setValue(temp);
        }
    }

    private List<Game2Dto> getWords() {
        List<Game2Dto> words = new LinkedList<>();
        List<String> sekaniWords = new LinkedList<>();
        List<String> englishWords = new LinkedList<>();
        Game2Dto w1 = game2DtoDao.getRandom(sekaniWords, englishWords);
        w1.questionColor = 0;
        w1.answeredColor = 3;
        words.add(w1);
        sekaniWords.add(w1.sekaniWordsEntity.word);
        englishWords.add(w1.englishWordsEntity.word);
        Game2Dto w2 = game2DtoDao.getRandom(sekaniWords, englishWords);
        words.add(w2);
        w2.questionColor = 1;
        w2.answeredColor = 3;
        sekaniWords.add(w2.sekaniWordsEntity.word);
        englishWords.add(w2.englishWordsEntity.word);
        Game2Dto w3 = game2DtoDao.getRandom(sekaniWords, englishWords);
        w3.questionColor = 2;
        w3.answeredColor = 3;
        words.add(w3);
        return words;
    }

    private List<Game2Dto> shuffle(List<Game2Dto> words) {
        List<Game2Dto> tempList = new ArrayList<>();
        for (Game2Dto w : words) {
            tempList.add(Converter.clone(w));
        }
        Collections.shuffle(tempList);
        return tempList;
    }

}
