package com.example.sekini.data.local.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.sekini.data.model.SekaniWordsEntity;
import com.example.sekini.data.model.UserFailedWord;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface IUserFailedWordDao {

    @Insert(onConflict = REPLACE)
    void insert(UserFailedWord... entities);

    @Insert(onConflict = REPLACE)
    void insert(List<UserFailedWord> entities);

    @Query("DELETE FROM UserFailedWord WHERE id in (:ids) ")
    void delete(List<Integer> ids);

    @Query("DELETE FROM UserFailedWord WHERE id in (:ids) ")
    void delete(Integer... ids);

}
