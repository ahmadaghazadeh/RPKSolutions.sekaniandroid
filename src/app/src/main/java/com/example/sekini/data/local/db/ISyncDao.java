package com.example.sekini.data.local.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.sekini.data.model.SekaniWordsEntity;
import com.example.sekini.data.model.SyncEntity;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface ISyncDao {

    @Insert(onConflict = REPLACE)
    void insert(SyncEntity... entities);

    @Insert(onConflict = REPLACE)
    void insert(List<SyncEntity> entities);

    @Update(onConflict = REPLACE)
    void update(SyncEntity syncEntity);

    @Query("SELECT IFNULL(max(timestamp),'2000-01-01T01:01:01.0000000') FROM Sync WHERE tableName=:tableName")
    String getTimestamp(String tableName);



}
