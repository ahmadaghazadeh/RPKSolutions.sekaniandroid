package com.example.sekini.ui.word.item.word;

import com.example.sekini.data.local.db.embedded.ISekaniWordExampleDtoDao;
import com.example.sekini.data.model.SekaniWordAttributesEntity;
import com.example.sekini.data.model.embedded.SekaniWordDto;
import com.example.sekini.data.model.embedded.SekaniWordExampleDto;
import com.example.sekini.ui.word.item.IWordItem;
import com.example.sekini.utils.recycler.BaseRecyclerView;

import java.util.LinkedList;
import java.util.List;

public class VerbWord implements IWordItem {

    private SekaniWordDto sekaniWordDto;
    private ISekaniWordExampleDtoDao sekaniWordExampleDtoDao;
    private String plurality;

    public VerbWord(String plurality, SekaniWordDto sekaniWordDto, ISekaniWordExampleDtoDao sekaniWordExampleDtoDao) {
        this.sekaniWordDto = sekaniWordDto;
        this.plurality = plurality;
        this.sekaniWordExampleDtoDao = sekaniWordExampleDtoDao;
    }

    @Override
    public List<BaseRecyclerView> render() {
        List<BaseRecyclerView> lst = new LinkedList<>();
        String attrib = plurality;
        if (sekaniWordDto != null) {
            List<SekaniWordExampleDto> example = sekaniWordExampleDtoDao.getExample(sekaniWordDto.sekaniWordsEntity.id);
            lst.add(new WordItem(sekaniWordDto.audio,
                    attrib,
                    sekaniWordDto.sekaniWordsEntity.word,
                    sekaniWordDto.sekaniWordsEntity.phonetic,
                    example));
        }
        return lst;
    }
}
