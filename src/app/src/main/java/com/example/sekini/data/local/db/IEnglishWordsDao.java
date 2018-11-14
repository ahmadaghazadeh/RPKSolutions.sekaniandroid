package com.example.sekini.data.local.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.sekini.data.model.EnglishWordsEntity;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface IEnglishWordsDao {

    @Insert(onConflict = REPLACE)
    void insert(EnglishWordsEntity... entities);

    @Insert(onConflict = REPLACE)
    void insert(List<EnglishWordsEntity> entities);

    @Query("DELETE FROM EnglishWords WHERE id in (:ids) ")
    void delete(List<Integer> ids);

    @Query("DELETE FROM EnglishWords WHERE id in (:ids) ")
    void delete(Integer... ids);

    @Query("SELECT id  FROM EnglishWords")
    List<Integer> getIds();

    @Query("SELECT MAX(updateTime)  FROM EnglishWords")
    String getMaxDate();

    @Query("SELECT *  FROM EnglishWords WHERE word like :word")
    List<EnglishWordsEntity> like(String word);
}
