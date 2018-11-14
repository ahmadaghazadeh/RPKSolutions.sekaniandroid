package com.example.sekini.ui.dictionary.dic;


import com.example.sekini.BR;
import com.example.sekini.R;
import com.example.sekini.utils.recycler.BaseRecyclerView;


public class EmptyDicViewModel extends BaseRecyclerView {

    public static int layoutId = R.layout.item_dic_empty_view;

    @Override
    public int getLayoutId() {
        return layoutId;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }


}
