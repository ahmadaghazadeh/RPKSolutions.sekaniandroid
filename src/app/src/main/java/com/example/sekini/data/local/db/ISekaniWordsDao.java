package com.example.sekini.data.local.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.sekini.data.model.EnglishWordsEntity;
import com.example.sekini.data.model.SekaniWordExamplesEntity;
import com.example.sekini.data.model.SekaniWordsEntity;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface ISekaniWordsDao {

    @Insert(onConflict = REPLACE)
    void insert(SekaniWordsEntity... entities);

    @Insert(onConflict = REPLACE)
    void insert(List<SekaniWordsEntity> entities);

    @Query("DELETE FROM SekaniWords WHERE id in (:ids) ")
    void delete(List<Integer> ids);

    @Query("DELETE FROM SekaniWords WHERE id in (:ids) ")
    void delete(Integer... ids);


    @Query("SELECT id  FROM SekaniWords")
    List<Integer> getIds();

    @Query("SELECT MAX(updateTime)  FROM SekaniWords")
    String getMaxDate();

    @Query("SELECT *  FROM SekaniWords WHERE word like :word")
    List<SekaniWordsEntity> like(String word);
}