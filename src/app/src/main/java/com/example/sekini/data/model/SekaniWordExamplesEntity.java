package com.example.sekini.data.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;


@Entity(tableName = "SekaniWordExamples",primaryKeys = "id",
        foreignKeys = {@ForeignKey(entity = SekaniWordsEntity.class,
                parentColumns = "id", childColumns = "sekaniWordId")},indices = {@Index("sekaniWordId")})
public class SekaniWordExamplesEntity extends BaseEntity{
    public int id;
    public String english;
    public String sekani;
    public int sekaniWordId;
    public String updateTime;

}

