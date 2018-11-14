package com.example.sekini.data.local.db.embedded;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import com.example.sekini.data.model.embedded.SekaniRootDto;

@Dao
public interface ISekaniRootDtoDao {

    @Query("SELECT\n" +
            "\tsr.*,\n" +
            "\tew.Word englishWords,\n" +
            "\tsf.id sf_id,\n" +
            "\tsf.notes sf_notes,\n" +
            "\tsf.title sf_title,\n" +
            "\tsf.updateTime sf_updateTime \n" +
            "FROM\n" +
            "\tSekaniRoots sr\n" +
            "\tINNER JOIN SekaniRoots_EnglishWords srew ON srew.SekaniRootId = sr.Id\n" +
            "\tINNER JOIN EnglishWords ew ON ew.Id = srew.EnglishWordId\n" +
            "\tINNER JOIN SekaniForms sf ON sf.Id = sr.sekaniFormId \n" +
            "WHERE sr.id=:rootId ")
    SekaniRootDto getWord(int rootId);
}


