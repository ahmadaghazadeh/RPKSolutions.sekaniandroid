package com.example.sekini.ui.dictionary.dic;


import com.example.sekini.BR;
import com.example.sekini.R;
import com.example.sekini.data.model.embedded.EnglishDicDto;
import com.example.sekini.utils.recycler.BaseRecyclerView;

public class EnglishDicItem extends BaseRecyclerView {


    public EnglishDicDto englishDicDto;
    public IDicListener itemSampleListener;

    public EnglishDicItem(EnglishDicDto englishWordsEntity, IDicListener itemSampleListener) {
        this.englishDicDto = englishWordsEntity;
        this.itemSampleListener = itemSampleListener;
    }

    public void onClick() {
        if (itemSampleListener != null) {
            itemSampleListener.onClick(this);
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_dic_english;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    public interface IDicListener {
        void onClick(EnglishDicItem itemSample);
    }
}
