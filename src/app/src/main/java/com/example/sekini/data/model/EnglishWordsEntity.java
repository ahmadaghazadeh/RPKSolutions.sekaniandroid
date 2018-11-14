package com.example.sekini.data.model;

import android.arch.persistence.room.Entity;

@Entity(tableName = "EnglishWords",primaryKeys = "id")
public class EnglishWordsEntity extends BaseEntity{
    public int id;
    public boolean standard;
    public String word;
    public String updateTime;
}

