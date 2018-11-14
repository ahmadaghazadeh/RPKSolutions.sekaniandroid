package com.example.sekini.data.local.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.sekini.data.model.SekaniFormsEntity;
import com.example.sekini.data.model.SekaniLevelsEntity;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface ISekaniLevelsDao {

    @Insert(onConflict = REPLACE)
    void insert(SekaniLevelsEntity... entities);

    @Insert(onConflict = REPLACE)
    void insert(List<SekaniLevelsEntity> entities);

    @Query("DELETE FROM SekaniLevels WHERE id in (:ids) ")
    void delete(List<Integer> ids);

    @Query("DELETE FROM SekaniLevels WHERE id in (:ids) ")
    void delete(Integer... ids);

    @Query("SELECT id  FROM SekaniLevels")
    List<Integer> getIds();

    @Query("SELECT MAX(updateTime)  FROM SekaniLevels")
    String getMaxDate();
}
