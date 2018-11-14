package com.example.sekini.data.local.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.sekini.data.model.SekaniWordAttributesEntity;
import com.example.sekini.data.model.SekaniWordAudiosEntity;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface ISekaniWordAudiosDao {

    @Insert(onConflict = REPLACE)
    void insert(SekaniWordAudiosEntity... entities);

    @Insert(onConflict = REPLACE)
    void insert(List<SekaniWordAudiosEntity> entities);

    @Query("DELETE FROM SekaniWordAudios WHERE id in (:ids) ")
    void delete(List<Integer> ids);

    @Query("DELETE FROM SekaniWordAudios WHERE id in (:ids) ")
    void delete(Integer... ids);

    @Query("SELECT id  FROM SekaniWordAudios")
    List<Integer> getIds();

    @Query("SELECT MAX(updateTime)  FROM SekaniWordAudios")
    String getMaxDate();
}
