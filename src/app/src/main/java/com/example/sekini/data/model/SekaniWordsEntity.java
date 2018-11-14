package com.example.sekini.data.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;


@Entity(tableName = "SekaniWords",primaryKeys = "id",
        foreignKeys = {@ForeignKey(entity = SekaniRootsEntity.class,
                parentColumns = "id", childColumns = "sekaniRootId")},
        indices = {@Index("sekaniRootId")})
public class SekaniWordsEntity extends BaseEntity{
    public int id;
    public String phonetic;
    public int sekaniRootId;
    public String word;
    public String updateTime;


}

