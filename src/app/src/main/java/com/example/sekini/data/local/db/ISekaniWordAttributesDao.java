package com.example.sekini.data.local.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.sekini.data.model.SekaniRootsEntity;
import com.example.sekini.data.model.SekaniWordAttributesEntity;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface ISekaniWordAttributesDao {

    @Insert(onConflict = REPLACE)
    void insert(SekaniWordAttributesEntity... entities);

    @Insert(onConflict = REPLACE)
    void insert(List<SekaniWordAttributesEntity> entities);

    @Query("DELETE FROM SekaniWordAttributes WHERE id in (:ids) ")
    void delete(List<Integer> ids);

    @Query("DELETE FROM SekaniWordAttributes WHERE id in (:ids) ")
    void delete(Integer... ids);


    @Query("SELECT id  FROM SekaniWordAttributes")
    List<Integer> getIds();

    @Query("SELECT MAX(updateTime)  FROM SekaniWordAttributes")
    String getMaxDate();
}
