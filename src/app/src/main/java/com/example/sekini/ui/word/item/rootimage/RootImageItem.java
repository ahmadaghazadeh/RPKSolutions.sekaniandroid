package com.example.sekini.ui.word.item.rootimage;


import com.example.sekini.BR;
import com.example.sekini.R;
import com.example.sekini.utils.common.RunnableIn;
import com.example.sekini.utils.recycler.BaseRecyclerView;

public class RootImageItem extends BaseRecyclerView {


    public byte[] image;
    public String englishWord;
    public String sekaniWord;
    public String sekaniForm;
    public  RunnableIn<byte[]> runnable;

    public RootImageItem(byte[] image, String englishWord, String sekaniWord, String sekaniForm, RunnableIn<byte[]> runnable) {
        this.image = image;
        this.englishWord = englishWord;
        this.sekaniWord = sekaniWord;
        this.sekaniForm = sekaniForm;
        this.runnable = runnable;
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_root_image;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    public void onClickImage(){
        runnable.run(image);
    }


}
