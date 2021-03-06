package com.example.sekini.ui.word.item.attribute;

import com.example.sekini.data.local.db.embedded.ISekaniWordDtoDao;
import com.example.sekini.data.local.db.embedded.ISekaniWordExampleDtoDao;
import com.example.sekini.data.model.SekaniWordAttributesEntity;
import com.example.sekini.data.model.SekaniWordsEntity;
import com.example.sekini.data.model.embedded.SekaniRootDto;
import com.example.sekini.data.model.embedded.SekaniWordDto;
import com.example.sekini.ui.word.item.word.Word;
import com.example.sekini.utils.recycler.BaseRecyclerView;

import java.util.LinkedList;
import java.util.List;

public class ThirdPerson extends Attribute {


    public ThirdPerson(SekaniRootDto sekaniRootDto, ISekaniWordExampleDtoDao sekaniWordExampleDtoDao, ISekaniWordDtoDao sekaniWordDtoDao) {
        super(sekaniRootDto, sekaniWordExampleDtoDao, sekaniWordDtoDao);
    }

    @Override
    protected List<String> getAttributes() {
        List<String> lst = new LinkedList<>();
        lst.add("3s");
        lst.add("3p");
        return lst;
    }

    @Override
    protected String value() {
        return "Third Person";
    }

    @Override
    protected String key() {
        return "Attrib";
    }


}

