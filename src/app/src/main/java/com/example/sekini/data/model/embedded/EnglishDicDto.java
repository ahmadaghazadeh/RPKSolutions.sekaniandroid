package com.example.sekini.data.model.embedded;

import android.arch.persistence.room.Embedded;

import com.example.sekini.data.model.EnglishWordsEntity;
import com.example.sekini.data.model.SekaniFormsEntity;

public class EnglishDicDto {

    @Embedded(prefix = "e_")
    public EnglishWordsEntity englishWordsEntity;

    @Embedded
    public SekaniFormsEntity sekaniFormsEntity;

    public int sekaniRootId;

}
