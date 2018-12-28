package com.example.sekini.data.model.embedded;

import android.arch.persistence.room.Embedded;

import com.example.sekini.data.model.EnglishWordsEntity;
import com.example.sekini.data.model.SekaniWordsEntity;

public class Game2Dto {

    @Embedded()
    public SekaniWordsEntity sekaniWordsEntity;

    @Embedded(prefix = "e_")
    public EnglishWordsEntity englishWordsEntity;

    public int questionColor;

    public int answeredColor;



}
