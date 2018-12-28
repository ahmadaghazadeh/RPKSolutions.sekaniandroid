package com.example.sekini.utils.common;


import com.example.sekini.data.model.EnglishWordsEntity;
import com.example.sekini.data.model.SekaniWordsEntity;
import com.example.sekini.data.model.embedded.EnglishDicDto;
import com.example.sekini.data.model.embedded.Game2Dto;
import com.example.sekini.data.model.embedded.SekaniDicDto;
import com.example.sekini.ui.dictionary.dic.EnglishDicItem;
import com.example.sekini.ui.dictionary.dic.SekaniDicItem;
import com.example.sekini.utils.recycler.BaseRecyclerView;

import java.util.LinkedList;
import java.util.List;

public class Converter {

    public static List<BaseRecyclerView> toDicEnglishWord(List<EnglishDicDto> likes, EnglishDicItem.IDicListener dicListener) {
        List<BaseRecyclerView> list = new LinkedList<>();
        for (EnglishDicDto entity : likes) {
            EnglishDicItem englishDicItem = new EnglishDicItem(entity, dicListener);
            list.add(englishDicItem);
        }
        return list;
    }

    public static List<BaseRecyclerView> toDicSekaniWord(List<SekaniDicDto> likes, SekaniDicItem.IDicListener dicListener) {
        List<BaseRecyclerView> list = new LinkedList<>();
        for (SekaniDicDto entity : likes) {
            SekaniDicItem englishDicItem = new SekaniDicItem(entity, dicListener);
            list.add(englishDicItem);
        }
        return list;
    }

    public static Game2Dto clone(Game2Dto into) {
        Game2Dto temp = new Game2Dto();
        temp.questionColor = into.questionColor;
        temp.answeredColor = into.answeredColor;

        SekaniWordsEntity sekaniWordsEntity = new SekaniWordsEntity();
        sekaniWordsEntity.word = into.sekaniWordsEntity.word;
        sekaniWordsEntity.phonetic = into.sekaniWordsEntity.phonetic;
        sekaniWordsEntity.updateTime = into.sekaniWordsEntity.updateTime;
        sekaniWordsEntity.id = into.sekaniWordsEntity.id;
        sekaniWordsEntity.sekaniRootId = into.sekaniWordsEntity.sekaniRootId;
        temp.sekaniWordsEntity = sekaniWordsEntity;

        EnglishWordsEntity englishWordsEntity = new EnglishWordsEntity();
        englishWordsEntity.id = into.englishWordsEntity.id;
        englishWordsEntity.standard = into.englishWordsEntity.standard;
        englishWordsEntity.updateTime = into.englishWordsEntity.updateTime;
        englishWordsEntity.word = into.englishWordsEntity.word;
        temp.englishWordsEntity = englishWordsEntity;
        return temp;
    }
}
