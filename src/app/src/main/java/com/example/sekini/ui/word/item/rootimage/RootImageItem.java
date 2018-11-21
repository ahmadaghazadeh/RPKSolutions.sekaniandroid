package com.example.sekini.ui.word.item.rootimage;


import com.example.sekini.BR;
import com.example.sekini.R;
import com.example.sekini.utils.recycler.BaseRecyclerView;

public class RootImageItem extends BaseRecyclerView {


    public byte[] image;
    public String englishWord;
    public String sekaniWord;
    public String sekaniForm;

    public RootImageItem(byte[] image, String englishWord, String sekaniWord, String sekaniForm) {
        this.image = image;
        this.englishWord = englishWord;
        this.sekaniWord = sekaniWord;
        this.sekaniForm = sekaniForm;
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_root_image;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }


}
