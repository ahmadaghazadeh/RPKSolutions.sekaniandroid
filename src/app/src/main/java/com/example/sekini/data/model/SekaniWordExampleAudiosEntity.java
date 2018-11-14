package com.example.sekini.data.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;


@Entity(tableName = "SekaniWordExampleAudios",primaryKeys = "id",
        foreignKeys = {@ForeignKey(entity = SekaniWordExamplesEntity.class,
                parentColumns = "id", childColumns = "sekaniWordExampleId")},
        indices = {@Index("sekaniWordExampleId")})
public class SekaniWordExampleAudiosEntity extends BaseEntity{
    public int id;
    public byte[] content;
    public String format;
    public String notes;
    public int sekaniWordExampleId;
    public String updateTime;


}

