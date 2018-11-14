package com.example.sekini.data.local.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.sekini.data.model.SekaniRootImagesEntity;
import com.example.sekini.data.model.SekaniRoots_EnglishWordsEntity;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface ISekaniRoots_EnglishWordsDao {

    @Insert(onConflict = REPLACE)
    void insert(SekaniRoots_EnglishWordsEntity... entities);

    @Insert(onConflict = REPLACE)
    void insert(List<SekaniRoots_EnglishWordsEntity> entities);

    @Query("DELETE FROM SekaniRoots_EnglishWords WHERE id in (:ids) ")
    void delete(List<Integer> ids);

    @Query("DELETE FROM SekaniRoots_EnglishWords WHERE id in (:ids) ")
    void delete(Integer... ids);

    @Query("SELECT id  FROM SekaniRoots_EnglishWords")
    List<Integer> getIds();

    @Query("SELECT MAX(updateTime)  FROM SekaniRoots_EnglishWords")
    String getMaxDate();
}
