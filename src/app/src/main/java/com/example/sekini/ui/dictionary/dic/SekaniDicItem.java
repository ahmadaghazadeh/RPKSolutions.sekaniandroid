package com.example.sekini.ui.dictionary.dic;


import com.example.sekini.BR;
import com.example.sekini.R;
import com.example.sekini.data.model.embedded.SekaniDicDto;
import com.example.sekini.utils.recycler.BaseRecyclerView;

public class SekaniDicItem extends BaseRecyclerView {


    public SekaniDicDto sekaniDicDto;
    public IDicListener itemSampleListener;

    public SekaniDicItem(SekaniDicDto sekaniDicDto, IDicListener itemSampleListener) {
        this.sekaniDicDto = sekaniDicDto;
        this.itemSampleListener = itemSampleListener;
    }

    public void onClick() {
        if (itemSampleListener != null) {
            itemSampleListener.onClick(this);
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_dic_sekani;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    public interface IDicListener {
        void onClick(SekaniDicItem itemSample);
    }
}
