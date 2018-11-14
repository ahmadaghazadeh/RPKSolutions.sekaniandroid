package com.example.sekini.data.local.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.sekini.data.model.EnglishWordsEntity;
import com.example.sekini.data.model.SekaniCategoriesEntity;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface ISekaniCategoriesDao {

    @Insert(onConflict = REPLACE)
    void insert(SekaniCategoriesEntity... entities);

    @Insert(onConflict = REPLACE)
    void insert(List<SekaniCategoriesEntity> entities);

    @Query("DELETE FROM SekaniCategories WHERE id in (:ids) ")
    void delete(List<Integer> ids);

    @Query("DELETE FROM SekaniCategories WHERE id in (:ids) ")
    void delete(Integer... ids);

    @Query("SELECT id  FROM SekaniCategories")
    List<Integer> getIds();

    @Query("SELECT MAX(updateTime)  FROM SekaniCategories")
    String getMaxDate();
}
