package com.example.sekini.data.local.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.sekini.data.model.SekaniWordExampleAudiosEntity;
import com.example.sekini.data.model.SekaniWordExamplesEntity;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface ISekaniWordExamplesDao {

    @Insert(onConflict = REPLACE)
    void insert(SekaniWordExamplesEntity... entities);

    @Insert(onConflict = REPLACE)
    void insert(List<SekaniWordExamplesEntity> entities);

    @Query("DELETE FROM SekaniWordExamples WHERE id in (:ids) ")
    void delete(List<Integer> ids);

    @Query("DELETE FROM SekaniWordExamples WHERE id in (:ids) ")
    void delete(Integer... ids);

    @Query("SELECT id  FROM SekaniWordExamples")
    List<Integer> getIds();

    @Query("SELECT MAX(updateTime)  FROM SekaniWordExamples")
    String getMaxDate();
}
