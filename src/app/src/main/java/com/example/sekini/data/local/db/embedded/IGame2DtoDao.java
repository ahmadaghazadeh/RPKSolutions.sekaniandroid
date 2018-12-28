package com.example.sekini.data.local.db.embedded;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import com.example.sekini.data.model.embedded.EnglishDicDto;
import com.example.sekini.data.model.embedded.Game2Dto;
import com.example.sekini.data.model.embedded.SekaniDicDto;

import java.util.List;

@Dao
public interface IGame2DtoDao {

    @Query(" SELECT\n" +
            "\tsw.id , sw.phonetic,sw.sekaniRootId,sw.updateTime,upper(substr(sw.word, 1, 1))||substr(sw.word, 2) word, ew.id e_id,ew.standard e_standard,ew.updateTime e_updateTime,upper(substr(ew.word, 1, 1))||substr(ew.word, 2) e_word,0 questionColor,0 answeredColor\n" +
            "FROM\n" +
            "\tSekaniWords sw\n" +
            "\tINNER JOIN SekaniRoots_EnglishWords srew ON srew.sekaniRootId = sw.sekaniRootId\n" +
            "\tINNER JOIN EnglishWords ew ON ew.id = srew.englishWordId \n" +
            "\tLEFT JOIN UserLearnedWord ul ON sw.id=ul.sekaniWordId\n" +
            "WHERE sw.word NOT IN (:sekaniWords) AND ew.word NOT IN (:englishWords) AND ul.sekaniWordId IS NULL\n" +
            "ORDER BY RANDOM() LIMIT 1")
    Game2Dto getRandom(List<String> sekaniWords, List<String> englishWords);

    @Query(" SELECT  CASE WHEN (count(1) / 3)>:count THEN\n" +
            "        :count \n" +
            "    ELSE\n" +
            "        count(1) / 3\n" +
            "    END \n" +
            "FROM\n" +
            "\tSekaniWords sw\n" +
            "\tINNER JOIN SekaniRoots_EnglishWords srew ON srew.sekaniRootId = sw.sekaniRootId\n" +
            "\tINNER JOIN EnglishWords ew ON ew.id = srew.englishWordId\n" +
            "\tLEFT JOIN UserLearnedWord ul ON sw.id = ul.sekaniWordId \n" +
            "WHERE\n" +
            "\t ul.sekaniWordId IS NULL \n")
    int getCount(int count);

}
