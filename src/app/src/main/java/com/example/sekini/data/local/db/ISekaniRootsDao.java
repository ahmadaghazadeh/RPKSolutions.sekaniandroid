package com.example.sekini.data.local.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.sekini.data.model.SekaniRootsEntity;
import com.example.sekini.data.model.SekaniRoots_TopicsEntity;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface ISekaniRootsDao {

    @Insert(onConflict = REPLACE)
    void insert(SekaniRootsEntity... entities);

    @Insert(onConflict = REPLACE)
    void insert(List<SekaniRootsEntity> entities);

    @Query("DELETE FROM SekaniRoots WHERE id in (:ids) ")
    void delete(List<Integer> ids);

    @Query("DELETE FROM SekaniRoots WHERE id in (:ids) ")
    void delete(Integer... ids);

    @Query("SELECT id  FROM SekaniRoots")
    List<Integer> getIds();

    @Query("SELECT MAX(updateTime)  FROM SekaniRoots")
    String getMaxDate();


}
