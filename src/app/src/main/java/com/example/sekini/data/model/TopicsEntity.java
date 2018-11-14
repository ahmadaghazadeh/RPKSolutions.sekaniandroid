package com.example.sekini.data.model;

import android.arch.persistence.room.Entity;


@Entity(tableName = "Topics",primaryKeys = "id")
public class TopicsEntity extends BaseEntity{
    public int id;
    public String note;
    public String title;
    public String updateTime;
}

