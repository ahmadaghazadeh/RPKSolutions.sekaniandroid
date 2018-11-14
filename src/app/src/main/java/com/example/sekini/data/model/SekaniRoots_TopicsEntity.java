package com.example.sekini.data.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;


@Entity(tableName = "SekaniRoots_Topics", primaryKeys = "id",
        foreignKeys = {@ForeignKey(entity = SekaniRootsEntity.class,
                parentColumns = "id", childColumns = "sekaniRootId"),
                @ForeignKey(entity = TopicsEntity.class,
                        parentColumns = "id", childColumns = "topicId")},
        indices = {@Index("sekaniRootId"),@Index("topicId")})
public class SekaniRoots_TopicsEntity extends BaseEntity {
    public int id;
    public int topicId;
    public int sekaniRootId;
    public String updateTime;

}

