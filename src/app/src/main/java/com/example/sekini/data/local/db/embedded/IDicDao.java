package com.example.sekini.data.local.db.embedded;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import com.example.sekini.data.model.embedded.EnglishDicDto;
import com.example.sekini.data.model.embedded.SekaniDicDto;

import java.util.List;

@Dao
public interface IDicDao {

    @Query(" SELECT ew.word e_word,ew.id e_id,ew.updateTime e_updateTime,\n" +
            "            ew.standard e_standard,sf.* ,sr.id sekaniRootId\n" +
            "            FROM EnglishWords ew \n" +
            "            INNER JOIN SekaniRoots_EnglishWords srew ON ew.Id=srew.EnglishWordId\n" +
            "            INNER JOIN SekaniRoots sr ON sr.Id=srew.SekaniRootId\n" +
            "            INNER JOIN SekaniForms sf ON sf.Id=sr.SekaniFormId\n" +
            "             WHERE ew.word like :word ")
    List<EnglishDicDto> likeEnglish(String word);

    @Query("  SELECT\n" +
            "\tsw.id s_id,\n" +
            "\tsw.phonetic s_phonetic,\n" +
            "\tsw.sekaniRootId s_sekaniRootId,\n" +
            "\tsw.word s_word,\n" +
            "\tsw.updateTime s_updateTime,\n" +
            "\tsf.*,\n" +
            "\tew.word englishWord \n" +
            "FROM\n" +
            "\tSekaniWords sw\n" +
            "\tINNER JOIN SekaniRoots sr ON sw.sekaniRootId = sr.id\n" +
            "\tINNER JOIN SekaniRoots_EnglishWords srew ON sr.Id = srew.sekaniRootId\n" +
            "\tINNER JOIN EnglishWords ew ON ew.id = srew.englishWordId \n" +
            "\tINNER JOIN SekaniForms sf ON sf.Id = sr.sekaniFormId\t\n" +
            "WHERE\n" +
            "\tsw.word LIKE :word")
    List<SekaniDicDto> likeSekani(String word);
}
