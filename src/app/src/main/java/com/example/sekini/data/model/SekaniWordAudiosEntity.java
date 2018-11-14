package com.example.sekini.data.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;


@Entity(tableName = "SekaniWordAudios", primaryKeys = "id",
        foreignKeys = {@ForeignKey(entity = SekaniWordsEntity.class,
                parentColumns = "id", childColumns = "sekaniWordId")},
        indices = {@Index("sekaniWordId")}
)
public class SekaniWordAudiosEntity extends BaseEntity {
    public int id;
    public byte[] content;
    public String format;
    public String notes;
    public int sekaniWordId;
    public String updateTime;

}

