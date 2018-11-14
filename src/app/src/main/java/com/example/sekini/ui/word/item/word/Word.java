package com.example.sekini.ui.word.item.word;

import com.example.sekini.data.local.db.embedded.ISekaniWordExampleDtoDao;
import com.example.sekini.data.model.SekaniRootImagesEntity;
import com.example.sekini.data.model.SekaniWordAttributesEntity;
import com.example.sekini.data.model.embedded.SekaniRootDto;
import com.example.sekini.data.model.embedded.SekaniWordDto;
import com.example.sekini.data.model.embedded.SekaniWordExampleDto;
import com.example.sekini.ui.word.item.IWordItem;
import com.example.sekini.utils.recycler.BaseRecyclerView;

import java.util.LinkedList;
import java.util.List;

public class Word implements IWordItem {

    private SekaniWordDto sekaniWordDto;
    private ISekaniWordExampleDtoDao sekaniWordExampleDtoDao;

    public Word(SekaniWordDto sekaniWordDto, ISekaniWordExampleDtoDao sekaniWordExampleDtoDao) {
        this.sekaniWordDto = sekaniWordDto;
        this.sekaniWordExampleDtoDao = sekaniWordExampleDtoDao;
    }

    @Override
    public List<BaseRecyclerView> render() {
        List<BaseRecyclerView> lst = new LinkedList<>();
        String attrib = "";
        if (sekaniWordDto.getAttributes.size() == 1) {
            attrib = sekaniWordDto.getAttributes.get(0).value;
        } else if (sekaniWordDto.getAttributes.size() == 3) {
            for (SekaniWordAttributesEntity attributesEntity : sekaniWordDto.getAttributes) {
                if (attributesEntity.key.equals("Plurality")) {
                    attrib = attributesEntity.value;
                }
            }
        }
        List<SekaniWordExampleDto> example = sekaniWordExampleDtoDao.getExample(sekaniWordDto.sekaniWordsEntity.id);
        if (sekaniWordDto.audio != null || !attrib.equals("") ||
                !sekaniWordDto.sekaniWordsEntity.phonetic.equals("") || example.size() > 0) {
            lst.add(new WordItem(sekaniWordDto.audio,
                    attrib,
                    sekaniWordDto.sekaniWordsEntity.word,
                    sekaniWordDto.sekaniWordsEntity.phonetic,
                    example));
        }
        return lst;
    }
}
