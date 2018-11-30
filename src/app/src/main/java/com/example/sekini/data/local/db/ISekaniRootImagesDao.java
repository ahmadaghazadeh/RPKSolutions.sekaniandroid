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

    @Query("SELECT *  FROM SekaniRootImages WHERE sekaniRootId in(:sekaniRootIds)")
    List<SekaniRootImagesEntity> get(List<Integer>  sekaniRootIds);

    @Query("SELECT *  FROM SekaniRootImages WHERE sekaniRootId in(:sekaniRootIds)")
    List<SekaniRootImagesEntity> get(Integer...  sekaniRootIds);

    @Query("SELECT\n" +
            "\t* \n" +
            "FROM\n" +
            "\t(\n" +
            "\tSELECT\n" +
            "\t\t* \n" +
            "\tFROM\n" +
            "\t\tSekaniRootImages \n" +
            "\tWHERE\n" +
            "\t\tsekaniRootId = :sekaniRootIds UNION ALL\n" +
            "\tSELECT\n" +
            "\t\t* \n" +
            "\tFROM\n" +
            "\t\t( SELECT * FROM SekaniRootImages WHERE sekaniRootId != :sekaniRootIds ORDER BY RANDOM() LIMIT 3 ) a \n" +
            "\t) B \n" +
            "ORDER BY\n" +
            "\tRANDOM()")
    List<SekaniRootImagesEntity> getRandom(Integer  sekaniRootIds);

}
