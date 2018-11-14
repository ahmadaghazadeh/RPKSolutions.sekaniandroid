package com.example.sekini.data.model;

import android.arch.persistence.room.Entity;


@Entity(tableName = "SekaniDictionary",primaryKeys = "sekaniId")
public class SekaniDictionaryEntity extends BaseEntity{
    public int sekaniId;
    public String phonetic;
    public String sekaniWord;
    public String englishWord;
    public int isNull;
    public String rootWord;
    public int sekaniCategoryId;
    public String category;
    public int sekaniLevelId;
    public String level;
    public int sekaniFormId;
    public String form;
    public byte[] image;
    public String imageFormat;
    public byte[] sound;
    public String soundFormat;
}

