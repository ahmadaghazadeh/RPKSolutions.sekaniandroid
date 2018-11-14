package com.example.sekini.data.local.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.sekini.data.model.SekaniCategoriesEntity;
import com.example.sekini.data.model.SekaniDictionaryEntity;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface ISekaniDictionaryDao {

    @Insert(onConflict = REPLACE)
    void insert(SekaniDictionaryEntity... entities);

    @Insert(onConflict = REPLACE)
    void insert(List<SekaniDictionaryEntity> entities);

    @Query("DELETE FROM SekaniDictionary WHERE sekaniId in (:ids) ")
    void delete(List<Integer> ids);

    @Query("DELETE FROM SekaniDictionary WHERE sekaniId in (:ids) ")
    void delete(Integer... ids);


}
