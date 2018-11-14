package com.example.sekini.data.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;


@Entity(tableName = "SekaniRoots_EnglishWords", primaryKeys = "id"
        , foreignKeys = {@ForeignKey(entity = EnglishWordsEntity.class,
        parentColumns = "id", childColumns = "englishWordId"),
        @ForeignKey(entity = SekaniRootsEntity.class,
                parentColumns = "id", childColumns = "sekaniRootId")},
        indices = {@Index("sekaniRootId"),@Index("englishWordId")})
public class SekaniRoots_EnglishWordsEntity extends BaseEntity {
    public int id;
    public int englishWordId;
    public int sekaniRootId;
    public String updateTime;

}

