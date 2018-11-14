package com.example.sekini.data.local.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.sekini.data.model.SekaniDictionaryEntity;
import com.example.sekini.data.model.SekaniFormsEntity;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface ISekaniFormsDao {

    @Insert(onConflict = REPLACE)
    void insert(SekaniFormsEntity... entities);

    @Insert(onConflict = REPLACE)
    void insert(List<SekaniFormsEntity> entities);

    @Query("DELETE FROM SekaniForms WHERE id in (:ids) ")
    void delete(List<Integer> ids);

    @Query("DELETE FROM SekaniForms WHERE id in (:ids) ")
    void delete(Integer... ids);

    @Query("SELECT id  FROM SekaniForms")
    List<Integer> getIds();

    @Query("SELECT MAX(updateTime)  FROM SekaniForms")
    String getMaxDate();
}
