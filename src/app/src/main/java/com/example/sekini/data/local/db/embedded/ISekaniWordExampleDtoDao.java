package com.example.sekini.data.local.db.embedded;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import com.example.sekini.data.model.embedded.SekaniWordDto;
import com.example.sekini.data.model.embedded.SekaniWordExampleDto;

import java.util.List;

@Dao
public interface ISekaniWordExampleDtoDao {

    @Query("  SELECT swe.Id sw_id,swe.English sw_english,swe.Sekani sw_sekani,swe.SekaniWordId sw_sekaniWordId,\n" +
            "swe.UpdateTime sw_updateTime,swea.*\n" +
            "FROM SekaniWordExamples swe\n" +
            "    LEFT JOIN SekaniWordExampleAudios swea\n" +
            "        ON swea.SekaniWordExampleId = swe.Id\n" +
            "WHERE swe.sekaniWordId =:id\n ")
    List<SekaniWordExampleDto> getExample(int id);
}


