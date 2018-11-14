package com.example.sekini.data.model.embedded;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Relation;

import com.example.sekini.data.model.SekaniWordAttributesEntity;
import com.example.sekini.data.model.SekaniWordExamplesEntity;
import com.example.sekini.data.model.SekaniWordsEntity;

import java.util.List;

public class SekaniWordDto {
    @Embedded()
    public SekaniWordsEntity sekaniWordsEntity;

    public byte[] audio;

    @Relation(parentColumn = "id",
            entityColumn = "sekaniWordId")
    public List<SekaniWordAttributesEntity> getAttributes;

    @Relation(parentColumn = "id",
            entityColumn = "sekaniWordId")
    public List<SekaniWordExamplesEntity> getExamples;
}
