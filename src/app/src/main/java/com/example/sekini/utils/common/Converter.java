package com.example.sekini.utils.common;


import com.example.sekini.data.model.embedded.EnglishDicDto;
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


}
