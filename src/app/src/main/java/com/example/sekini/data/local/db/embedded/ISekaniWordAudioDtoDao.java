package com.example.sekini.data.local.db.embedded;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import com.example.sekini.data.model.embedded.SekaniWordAudioDto;

@Dao
public interface ISekaniWordAudioDtoDao {

    @Query("  SELECT upper(substr(sw.word, 1, 1))||substr(sw.word, 2) sekaniWord,swa.Content audio,upper(substr( ew.word, 1, 1))||substr( ew.word, 2) englishWord  FROM  SekaniWords sw  \n" +
            "INNER JOIN SekaniRoots_EnglishWords srew on srew.sekaniRootId=sw.sekaniRootId\n" +
            "INNER JOIN EnglishWords ew on ew.id=srew.englishWordId\n" +
            "            LEFT JOIN SekaniWordAudios swa on swa.sekaniWordId=sw.id\n" +
            "            WHERE sw.sekaniRootId=:sekaniRootId\n" +
            "            ORDER BY content desc LIMIT 1 ")
    SekaniWordAudioDto getWordRootId(int sekaniRootId);
}


