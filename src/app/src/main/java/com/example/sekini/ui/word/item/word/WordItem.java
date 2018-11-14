package com.example.sekini.ui.word.item.word;


import com.example.sekini.BR;
import com.example.sekini.R;
import com.example.sekini.data.model.embedded.SekaniWordExampleDto;
import com.example.sekini.utils.recycler.BaseRecyclerView;

import java.util.HashMap;
import java.util.List;

public class WordItem extends BaseRecyclerView {


    private static HashMap<String, String> attributeMap;

    public byte[] audio;
    public String attribute;
    public String sekaniWord;
    public String phonetic;
    public List<SekaniWordExampleDto> sekaniWordExamplesEntities;

    public WordItem(byte[] audio, String attribute, String sekaniWord
            , String phonetic, List<SekaniWordExampleDto> sekaniWordExamplesEntities) {
        if (attributeMap == null) {
            initAttribute();
        }
        this.audio = audio;
        if(attributeMap.containsKey(attribute)){
            this.attribute = attributeMap.get(attribute);
        }else{
            this.attribute=attribute;
        }

        this.sekaniWord = sekaniWord;
        this.phonetic = phonetic;
        this.sekaniWordExamplesEntities = sekaniWordExamplesEntities;
    }

    private void initAttribute() {
        attributeMap = new HashMap<>();
        attributeMap.put("1s", "Singular");
        attributeMap.put("2s", "Dual");
        attributeMap.put("3s", "Singular");
        attributeMap.put("refl", "Singular");
        attributeMap.put("djr", "Singular");
        attributeMap.put("recip", "");
        attributeMap.put("1p", "Plural");
        attributeMap.put("2p", "Dual");
        attributeMap.put("3p", "Plural");
        attributeMap.put("pref", "Plural");
        attributeMap.put("pdjr", "Plural");
        attributeMap.put("indef", "");
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_word;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }


}
