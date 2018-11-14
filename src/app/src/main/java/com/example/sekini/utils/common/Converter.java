package com.example.sekini.utils.common;


import com.example.sekini.data.model.embedded.SekaniEnglishDicDto;
import com.example.sekini.ui.dictionary.dic.EnglishDicItem;
import com.example.sekini.utils.recycler.BaseRecyclerView;

import java.util.LinkedList;
import java.util.List;

public class Converter {

    public static List<BaseRecyclerView> toDicEnglishWord(List<SekaniEnglishDicDto> likes, EnglishDicItem.IDicListener dicListener) {
        List<BaseRecyclerView> list = new LinkedList<>();
        for (SekaniEnglishDicDto entity : likes) {
            EnglishDicItem englishDicItem = new EnglishDicItem(entity, dicListener);
            list.add(englishDicItem);
        }
        return list;
    }

}
