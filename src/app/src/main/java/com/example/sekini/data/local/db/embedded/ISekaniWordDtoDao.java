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

    @Query(" SELECT swau.Content audio,\n" +
            "       sw.Id,\n" +
            "       sw.Phonetic,\n" +
            "       sw.SekaniRootId,\n" +
            "       sw.Word,\n" +
            "       sw.UpdateTime\n" +
            "FROM SekaniWords sw\n" +
            "    INNER JOIN\n" +
            "    (\n" +
            "        SELECT COUNT(*) counter,\n" +
            "               sw.Id SekaniWordId\n" +
            "        FROM SekaniRoots sr\n" +
            "            INNER JOIN SekaniForms sf\n" +
            "                ON sf.Id = sr.SekaniFormId\n" +
            "            INNER JOIN SekaniWords sw\n" +
            "                ON sw.SekaniRootId = sr.Id\n" +
            "            INNER JOIN SekaniWordAttributes swa\n" +
            "                ON swa.SekaniWordId = sw.Id\n" +
            "        WHERE (\n" +
            "                  (\n" +
            "                      \"Key\" = 'Tense'\n" +
            "                      AND Value = :Tense\n" +
            "                  )\n" +
            "                  OR\n" +
            "                  (\n" +
            "                      \"Key\" = 'Plurality'\n" +
            "                      AND Value = :Plurality\n" +
            "                  )\n" +
            "                  OR\n" +
            "                  (\n" +
            "                      \"Key\" = 'Person'\n" +
            "                      AND Value = :Person\n" +
            "                  )\n" +
            "              )\n" +
            "              AND SekaniRootId = :SekaniRootId\n" +
            "        GROUP BY sw.Id\n" +
            "    ) a\n" +
            "        ON a.SekaniWordId = sw.Id\n" +
            "    LEFT JOIN SekaniWordAudios swau\n" +
            "        ON swau.SekaniWordId = sw.Id\n" +
            "WHERE counter = 3;")
    SekaniWordDto getWord(int SekaniRootId,String Tense,String Plurality,String Person);




}


