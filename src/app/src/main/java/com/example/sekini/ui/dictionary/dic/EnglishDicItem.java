package com.example.sekini.ui.dictionary.dic;


import com.example.sekini.BR;
import com.example.sekini.R;
import com.example.sekini.data.model.embedded.SekaniEnglishDicDto;
import com.example.sekini.utils.recycler.BaseRecyclerView;

public class EnglishDicItem extends BaseRecyclerView {


    public SekaniEnglishDicDto sekaniEnglishDicDto;
    public IDicListener itemSampleListener;

    public EnglishDicItem(SekaniEnglishDicDto englishWordsEntity, IDicListener itemSampleListener) {
        this.sekaniEnglishDicDto = englishWordsEntity;
        this.itemSampleListener = itemSampleListener;
    }

    public void onClick() {
        if (itemSampleListener != null) {
            itemSampleListener.onClick(this);
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_dic;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    public interface IDicListener {
        void onClick(EnglishDicItem itemSample);
    }
}
