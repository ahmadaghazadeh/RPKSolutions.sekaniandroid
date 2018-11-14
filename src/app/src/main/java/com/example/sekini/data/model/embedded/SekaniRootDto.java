package com.example.sekini.data.model.embedded;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Relation;

import com.example.sekini.data.model.SekaniFormsEntity;
import com.example.sekini.data.model.SekaniRootImagesEntity;
import com.example.sekini.data.model.SekaniRootsEntity;
import com.example.sekini.data.model.SekaniWordsEntity;

import java.util.List;

public class SekaniRootDto {

    @Embedded()
    public SekaniRootsEntity sekaniRootsEntity;

    public String englishWords;

    @Relation(parentColumn = "id",
            entityColumn = "sekaniRootId")
    public List<SekaniRootImagesEntity> getImages;

    @Embedded(prefix = "sf_")
    public SekaniFormsEntity sekaniFormsEntity;

    @Relation(parentColumn = "id",
            entityColumn = "sekaniRootId")
    public List<SekaniWordsEntity> getSekaniWords;
}
