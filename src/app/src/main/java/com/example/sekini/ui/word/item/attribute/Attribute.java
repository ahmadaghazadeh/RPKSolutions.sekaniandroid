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

public abstract class Attribute {

    protected abstract List<String> getAttributes();

    protected abstract String value();

    protected abstract String key();

    public List<BaseRecyclerView> render() {
        List<BaseRecyclerView> baseRecyclerViews = new LinkedList<>();

        for (SekaniWordsEntity entity : sekaniRootDto.getSekaniWords) {
            SekaniWordDto sekaniWordDto = sekaniWordDtoDao.getWord(entity.id);
            for (String attr : getAttributes()) {
                for (SekaniWordAttributesEntity sekaniWordAttributesEntity : sekaniWordDto.getAttributes) {
                    if (attr.equals(sekaniWordAttributesEntity.value) && key().equals(sekaniWordAttributesEntity.key)) {
                        Word word = new Word(sekaniWordDto, sekaniWordExampleDtoDao);
                        baseRecyclerViews.addAll(word.render());
                    }
                }
            }
        }
        if (baseRecyclerViews.size() > 0) {
            baseRecyclerViews.add(0, new AttributeItem(value()));
        }
        return baseRecyclerViews;
    }

    private SekaniRootDto sekaniRootDto;
    private ISekaniWordExampleDtoDao sekaniWordExampleDtoDao;
    private ISekaniWordDtoDao sekaniWordDtoDao;

    public Attribute(SekaniRootDto sekaniRootDto,
                     ISekaniWordExampleDtoDao sekaniWordExampleDtoDao,
                     ISekaniWordDtoDao sekaniWordDtoDao) {
        this.sekaniRootDto = sekaniRootDto;
        this.sekaniWordExampleDtoDao = sekaniWordExampleDtoDao;
        this.sekaniWordDtoDao = sekaniWordDtoDao;
    }
}
