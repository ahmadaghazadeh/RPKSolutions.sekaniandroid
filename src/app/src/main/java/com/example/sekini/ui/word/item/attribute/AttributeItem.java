package com.example.sekini.ui.word.item.attribute;


import com.example.sekini.BR;
import com.example.sekini.R;
import com.example.sekini.utils.recycler.BaseRecyclerView;

public class AttributeItem extends BaseRecyclerView {


    public String name;

    public AttributeItem( String name) {
        this.name = name;
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_attribute;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }


}
