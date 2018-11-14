package com.example.sekini.data.model.embedded;

import android.arch.persistence.room.Embedded;

import com.example.sekini.data.model.EnglishWordsEntity;
import com.example.sekini.data.model.SekaniFormsEntity;

public class SekaniEnglishDicDto {

    @Embedded(prefix = "e_")
    public EnglishWordsEntity sekaniWordsEntity;

    @Embedded
    public SekaniFormsEntity sekaniFormsEntity;

    public String sekaniWords;

    public int sekaniRootId;

    public byte[]  image;

}
