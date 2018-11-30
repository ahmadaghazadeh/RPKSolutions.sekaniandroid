package com.example.sekini.data.model.embedded;

import android.arch.persistence.room.Embedded;

import com.example.sekini.data.model.SekaniWordsEntity;

public class SekaniWordAudioDto {

    public String sekaniWord;

    public byte[] audio;

    public String englishWord;

}
