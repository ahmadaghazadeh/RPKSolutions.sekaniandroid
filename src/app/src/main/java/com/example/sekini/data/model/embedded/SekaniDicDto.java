package com.example.sekini.data.model.embedded;

import android.arch.persistence.room.Embedded;

import com.example.sekini.data.model.EnglishWordsEntity;
import com.example.sekini.data.model.SekaniFormsEntity;
import com.example.sekini.data.model.SekaniWordsEntity;

public class SekaniDicDto {

    @Embedded(prefix = "s_")
    public SekaniWordsEntity sekaniWordsEntity;

    @Embedded
    public SekaniFormsEntity sekaniFormsEntity;

    public String englishWord;


}
