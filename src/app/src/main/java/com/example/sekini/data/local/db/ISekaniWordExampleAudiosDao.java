package com.example.sekini.data.local.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.sekini.data.model.SekaniWordAudiosEntity;
import com.example.sekini.data.model.SekaniWordExampleAudiosEntity;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface ISekaniWordExampleAudiosDao {

    @Insert(onConflict = REPLACE)
    void insert(SekaniWordExampleAudiosEntity... entities);

    @Insert(onConflict = REPLACE)
    void insert(List<SekaniWordExampleAudiosEntity> entities);

    @Query("DELETE FROM SekaniWordExampleAudios WHERE id in (:ids) ")
    void delete(List<Integer> ids);

    @Query("DELETE FROM SekaniWordExampleAudios WHERE id in (:ids) ")
    void delete(Integer... ids);

    @Query("SELECT id  FROM SekaniWordExampleAudios")
    List<Integer> getIds();

    @Query("SELECT MAX(updateTime)  FROM SekaniWordExampleAudios")
    String getMaxDate();
}
