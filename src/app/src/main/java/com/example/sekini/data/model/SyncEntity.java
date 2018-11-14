package com.example.sekini.data.model;

import android.arch.persistence.room.Entity;
import android.support.annotation.NonNull;


@Entity(tableName = "Sync",primaryKeys = "tableName")
public class SyncEntity extends BaseEntity{
    public SyncEntity(@NonNull String tableName, String timestamp) {
        this.tableName = tableName;
        this.timestamp = timestamp;
    }

    @NonNull
    public String tableName;
    public String timestamp;

}

