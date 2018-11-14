package com.example.sekini.data.model;

import android.arch.persistence.room.Entity;


@Entity(tableName = "SekaniForms",primaryKeys = "id")
public class SekaniFormsEntity extends BaseEntity{
    public int id;
    public String notes;
    public String title;
    public String updateTime;

}

