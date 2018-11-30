package com.example.sekini.data.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;

@Entity(tableName = "UserLearnedWord", primaryKeys = "id",
        foreignKeys = {@ForeignKey(entity = SekaniWordsEntity.class,
                parentColumns = "id", childColumns = "sekaniWordId")},
        indices = {@Index("sekaniWordId")})
public class UserLearnedWord extends BaseEntity {
    public int id;
    public int userId;
    public int sekaniWordId;
    public String updateTime;
}

