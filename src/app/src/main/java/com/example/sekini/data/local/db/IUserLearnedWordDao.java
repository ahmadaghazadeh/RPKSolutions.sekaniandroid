package com.example.sekini.data.local.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.sekini.data.model.UserLearnedWord;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface IUserLearnedWordDao {

    @Insert(onConflict = REPLACE)
    void insert(UserLearnedWord... entities);

    @Insert(onConflict = REPLACE)
    void insert(List<UserLearnedWord> entities);

    @Query("DELETE FROM UserLearnedWord WHERE id in (:ids) ")
    void delete(List<Integer> ids);

    @Query("DELETE FROM UserLearnedWord WHERE id in (:ids) ")
    void delete(Integer... ids);

    @Query("SELECT MAX(id)  FROM UserLearnedWord")
    int getMaxId();
}
