package com.example.sekini.data.local.db.embedded;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import com.example.sekini.data.model.embedded.SekaniEnglishDicDto;

import java.util.List;

@Dao
public interface ISekaniEnglishDicDao {

    @Query("SELECT ew.word e_word,ew.id e_id,ew.updateTime e_updateTime," +
            " ew.standard e_standard,sf.*,GROUP_CONCAT(sw.word,', ') sekaniWords , srs.content image," +
            " sw.sekaniRootId sekaniRootId" +
            " FROM EnglishWords ew \n" +
            "INNER JOIN SekaniRoots_EnglishWords srew ON ew.Id=srew.EnglishWordId\n" +
            "INNER JOIN SekaniRoots sr ON sr.Id=srew.SekaniRootId\n" +
            "INNER JOIN SekaniForms sf ON sf.Id=sr.SekaniFormId\n" +
            "INNER JOIN SekaniWords sw ON sw.SekaniRootId=sr.Id\n" +
            "LEFT JOIN SekaniRootImages srs ON srs.sekaniRootId=sr.Id \t\n" +
            "WHERE ew.word like :word\n" +
            "GROUP BY ew.id Order by ew.word")
    List<SekaniEnglishDicDto> likeEnglish(String word);

    @Query("SELECT *\n" +
            "FROM\n" +
            "(\n" +
            "    SELECT ew.word e_word,\n" +
            "           ew.id e_id,\n" +
            "           ew.updateTime e_updateTime,\n" +
            "           ew.standard e_standard,\n" +
            "           sf.*,\n" +
            "           GROUP_CONCAT(sw.word, ', ') sekaniWords,\n" +
            "           srs.content image, sw.sekaniRootId sekaniRootId\n" +
            "    FROM EnglishWords ew\n" +
            "        INNER JOIN SekaniRoots_EnglishWords srew\n" +
            "            ON ew.Id = srew.EnglishWordId\n" +
            "        INNER JOIN SekaniRoots sr\n" +
            "            ON sr.Id = srew.SekaniRootId\n" +
            "        INNER JOIN SekaniForms sf\n" +
            "            ON sf.Id = sr.SekaniFormId\n" +
            "        INNER JOIN SekaniWords sw\n" +
            "            ON sw.SekaniRootId = sr.Id\n" +
            "        LEFT JOIN SekaniRootImages srs\n" +
            "            ON srs.SekaniRootId = sr.Id\n" +
            "    GROUP BY ew.Id\n" +
            ") a\n" +
            "WHERE a.sekaniWords LIKE :word\n" +
            "ORDER BY a.e_word;")
    List<SekaniEnglishDicDto> likeSekani(String word);
}
