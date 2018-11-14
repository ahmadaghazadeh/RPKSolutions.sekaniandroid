package com.example.sekini.data.local.db.embedded;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import com.example.sekini.data.model.embedded.SekaniRootDto;
import com.example.sekini.data.model.embedded.SekaniWordDto;

@Dao
public interface ISekaniWordDtoDao {

    @Query(" SELECT\n" +
            "\tsw.*,\n" +
            "\tswa.Content audio \n" +
            "FROM\n" +
            "\tSekaniWords sw\n" +
            "\tLEFT JOIN SekaniWordAudios swa ON swa.SekaniWordId = sw.Id\n" +
            "\tWHERE sw.id =:id ")
    SekaniWordDto getWord(int id);

    @Query("  SELECT a.audio audio,a.id id,a.phonetic phonetic,a.sekaniRootId sekaniRootId,a.word word,a.updateTime updateTime \n" +
            " FROM\n" +
            "\t(\n" +
            "\tSELECT\n" +
            "\t\tGROUP_CONCAT( swa.'key', ', ' ) keys,\n" +
            "\t\tGROUP_CONCAT( swa.'value', ', ' ) 'val',swau.Content audio ,sw.*\n" +
            "\tFROM SekaniRoots sr\n" +
            "\t\tINNER JOIN SekaniForms sf ON sf.Id = sr.SekaniFormId\n" +
            "\t\tINNER JOIN SekaniWords sw ON sw.SekaniRootId = sr.Id\n" +
            "\t\tINNER JOIN SekaniWordAttributes swa ON swa.SekaniWordId = sw.Id\n" +
            "\t\tLEFT JOIN SekaniWordAudios swau ON swau.SekaniWordId = sw.Id \n" +
            "\tGROUP BY\n" +
            "\t\tswa.SekaniWordId \n" +
            "\t) a \n" +
            "WHERE\n" +
            "\tkeys LIKE '%Tense%' \n" +
            "\tAND keys LIKE '%Plurality%' \n" +
            "\tAND keys LIKE '%Person%' \n" +
            "\tAND val LIKE :Tense \n" +
            "\tAND val LIKE :Plurality \n" +
            "\tAND val LIKE :Person ")
    SekaniWordDto getWord(String Tense,String Plurality,String Person);
}


