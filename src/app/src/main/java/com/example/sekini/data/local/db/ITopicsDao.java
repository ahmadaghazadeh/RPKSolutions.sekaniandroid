package com.example.sekini.data.local.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.sekini.data.model.TopicsEntity;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface ITopicsDao {

    @Insert(onConflict = REPLACE)
    void insert(TopicsEntity... entities);

    @Insert(onConflict = REPLACE)
    void insert(List<TopicsEntity> entities);

    @Query("DELETE FROM Topics WHERE id in (:ids) ")
    void delete(List<Integer> ids);

    @Query("DELETE FROM Topics WHERE id in (:ids) ")
    void delete(Integer... ids);

    @Update(onConflict = REPLACE)
    void update(TopicsEntity entity);

    @Query("SELECT id  FROM Topics")
    List<Integer> getIds();

    @Query("SELECT MAX(updateTime)  FROM Topics")
    String getMaxDate();
}
