package com.example.sekini.data.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;


@Entity(tableName = "SekaniWordAttributes", primaryKeys = "id",
        foreignKeys = {@ForeignKey(entity = SekaniWordsEntity.class,
                parentColumns = "id", childColumns = "sekaniWordId")},
        indices = {@Index("sekaniWordId")})
public class SekaniWordAttributesEntity extends BaseEntity {
    public int id;
    public String key;
    public String value;
    public int sekaniWordId;
    public String updateTime;

}

