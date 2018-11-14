package com.example.sekini.utils.recycler;

import android.support.annotation.LayoutRes;
import android.view.View;

public abstract class BaseRecyclerView    {

    private IRecyclerOnClick recyclerOnClick;
    public View.OnTouchListener onTouchListener;

    public void setAdapterOnClick(IRecyclerOnClick recyclerOnClick) {
        this.recyclerOnClick = recyclerOnClick;
    }

    public void setOnTouchListener( View.OnTouchListener onTouchListener) {
        this.onTouchListener = onTouchListener;
    }


    @LayoutRes
    public abstract int getLayoutId();

    public abstract int getBindingVariable();

    private boolean selected;

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
    public  void onClick(){
        setSelected(!isSelected());
        if(recyclerOnClick!=null)
            recyclerOnClick.onClick();
    }




}
