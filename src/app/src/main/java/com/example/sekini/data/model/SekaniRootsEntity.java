package com.example.sekini.data.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;


@Entity(tableName = "SekaniRoots", primaryKeys = "id",
        foreignKeys = {@ForeignKey(entity = SekaniCategoriesEntity.class, parentColumns = "id", childColumns = "sekaniCategoryId"),
        @ForeignKey(entity = SekaniLevelsEntity.class,parentColumns = "id",childColumns = "sekaniLevelId"),
        @ForeignKey(entity = SekaniFormsEntity.class,parentColumns = "id",childColumns = "sekaniFormId")},
        indices = {@Index("sekaniLevelId"),@Index("sekaniCategoryId"),@Index("sekaniFormId")})
public class SekaniRootsEntity extends BaseEntity {
    public int id;
    public Boolean isNull;
    public String rootWord;
    public int sekaniCategoryId;
    public int sekaniLevelId;
    public int sekaniFormId;
    public String updateTime;

}

