package com.example.sekini.data.local.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.sekini.data.model.SekaniLevelsEntity;
import com.example.sekini.data.model.SekaniRootImagesEntity;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface ISekaniRootImagesDao {

    @Insert(onConflict = REPLACE)
    void insert(SekaniRootImagesEntity... entities);

    @Insert(onConflict = REPLACE)
    void insert(List<SekaniRootImagesEntity> entities);

    @Query("DELETE FROM SekaniRootImages WHERE id in (:ids) ")
    void delete(List<Integer> ids);

    @Query("DELETE FROM SekaniRootImages WHERE id in (:ids) ")
    void delete(Integer... ids);

    @Query("SELECT id  FROM SekaniRootImages")
    List<Integer> getIds();

    @Query("SELECT MAX(updateTime)  FROM SekaniRootImages")
    String getMaxDate();
}
