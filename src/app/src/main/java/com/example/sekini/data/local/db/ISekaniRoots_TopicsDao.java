package com.example.sekini.data.local.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.sekini.data.model.SekaniRoots_EnglishWordsEntity;
import com.example.sekini.data.model.SekaniRoots_TopicsEntity;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface ISekaniRoots_TopicsDao {

    @Insert(onConflict = REPLACE)
    void insert(SekaniRoots_TopicsEntity... entities);

    @Insert(onConflict = REPLACE)
    void insert(List<SekaniRoots_TopicsEntity> entities);

    @Query("DELETE FROM SekaniRoots_Topics WHERE id in (:ids) ")
    void delete(List<Integer> ids);

    @Query("DELETE FROM SekaniRoots_Topics WHERE id in (:ids) ")
    void delete(Integer... ids);


    @Query("SELECT id  FROM SekaniRoots_Topics")
    List<Integer> getIds();

    @Query("SELECT MAX(updateTime)  FROM SekaniRoots_Topics")
    String getMaxDate();
}
