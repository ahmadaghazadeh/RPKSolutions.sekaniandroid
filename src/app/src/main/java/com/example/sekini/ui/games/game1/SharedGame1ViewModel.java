package com.example.sekini.ui.games.game1;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.example.sekini.ui.games.FeedBack;

import java.util.List;


public class SharedGame1ViewModel extends ViewModel{

    public MutableLiveData<Boolean> getNext() {
        return next;
    }

    public MutableLiveData<List<FeedBack>> getFeedBack() {
        return lstFeedBack;
    }
    private MutableLiveData<List<FeedBack>> lstFeedBack= new MutableLiveData<>();


    private MutableLiveData<Boolean> next = new MutableLiveData<>();

    public void nextPage(){
        next.setValue(true);
    }
    public void reset(){
        next.setValue(false);
    }

}
