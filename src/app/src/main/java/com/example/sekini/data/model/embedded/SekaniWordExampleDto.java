package com.example.sekini.data.model.embedded;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Relation;

import com.example.sekini.data.model.SekaniWordAttributesEntity;
import com.example.sekini.data.model.SekaniWordExampleAudiosEntity;
import com.example.sekini.data.model.SekaniWordExamplesEntity;
import com.example.sekini.data.model.SekaniWordsEntity;

import java.util.List;

public class SekaniWordExampleDto {
    @Embedded(prefix = "sw_")
    public SekaniWordExamplesEntity sekaniWordExamplesEntity;

    @Embedded()
    public SekaniWordExampleAudiosEntity sekaniWordExampleAudiosEntity;
}
