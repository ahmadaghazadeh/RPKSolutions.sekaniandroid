package com.example.sekini.data.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;


@Entity(tableName = "SekaniRootImages",primaryKeys = "id",
        foreignKeys = {@ForeignKey(entity = SekaniRootsEntity.class,
        parentColumns = "id", childColumns = "sekaniRootId")},
        indices = {@Index("sekaniRootId")})
public class SekaniRootImagesEntity extends BaseEntity{
    public int id;
    public byte[] content;
    public String format;
    public String notes;
    public int sekaniRootId;
    public String updateTime;
}

