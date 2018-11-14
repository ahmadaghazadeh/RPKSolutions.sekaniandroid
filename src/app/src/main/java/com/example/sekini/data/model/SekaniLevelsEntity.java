package com.example.sekini.data.model;

import android.arch.persistence.room.Entity;


@Entity(tableName = "SekaniLevels",primaryKeys = "id")
public class SekaniLevelsEntity extends BaseEntity{
    public int id;
    public String notes;
    public String title;
    public String updateTime;

}

