package com.example.sekini.ui.word.item.verb;

import com.example.sekini.data.local.db.embedded.ISekaniWordDtoDao;
import com.example.sekini.data.local.db.embedded.ISekaniWordExampleDtoDao;
import com.example.sekini.ui.word.item.attribute.AttributeItem;
import com.example.sekini.ui.word.item.word.VerbWord;
import com.example.sekini.utils.common.CommonUtils;
import com.example.sekini.utils.recycler.BaseRecyclerView;

import java.util.LinkedList;
import java.util.List;

public class Verb {

    public enum TENSE {
        Imperfective,
        Perfective,
        Future,
        Optative
    }

    public enum PERSON {
        First,
        Second,
        Third
    }

    private ISekaniWordExampleDtoDao sekaniWordExampleDtoDao;
    private ISekaniWordDtoDao sekaniWordDtoDao;
    private TENSE tense;
    private PERSON person;

    public Verb(
            ISekaniWordExampleDtoDao sekaniWordExampleDtoDao,
            ISekaniWordDtoDao sekaniWordDtoDao, TENSE tense, PERSON person) {
        this.sekaniWordExampleDtoDao = sekaniWordExampleDtoDao;
        this.sekaniWordDtoDao = sekaniWordDtoDao;
        this.tense = tense;
        this.person = person;
    }


    private List<String> plurality() {
        List<String> lst = new LinkedList<>();
        lst.add("Singular");
        lst.add("Dual");
        lst.add("Plural");
        return lst;
    }

    public List<BaseRecyclerView> render() {
        List<BaseRecyclerView> baseRecyclerViews = new LinkedList<>();
        for (String str : plurality()) {
            VerbWord word = new VerbWord(str,
                    sekaniWordDtoDao.getWord(
                            CommonUtils.getLikeString(tense.toString())
                            , CommonUtils.getLikeString(str),
                            CommonUtils.getLikeString(person.toString()))
                    , sekaniWordExampleDtoDao);
            baseRecyclerViews.addAll(word.render());
        }
        if (baseRecyclerViews.size() > 0) {
            baseRecyclerViews.add(0, new AttributeItem(person.toString() + " Person"));
        }
        return baseRecyclerViews;
    }


}
