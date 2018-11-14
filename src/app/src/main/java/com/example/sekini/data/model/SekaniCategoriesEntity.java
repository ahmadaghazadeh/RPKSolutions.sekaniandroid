package com.example.sekini.data.model;

import android.arch.persistence.room.Entity;


@Entity(tableName = "SekaniCategories",primaryKeys = "id")
public class SekaniCategoriesEntity extends BaseEntity{
    public int id;
    public String notes;
    public String title;
    public String updateTime;

}

