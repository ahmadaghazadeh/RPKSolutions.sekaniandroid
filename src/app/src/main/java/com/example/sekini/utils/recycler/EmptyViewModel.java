package com.example.sekini.utils.recycler;


import com.example.sekini.BR;
import com.example.sekini.R;

public class EmptyViewModel extends BaseRecyclerView {

    public static int layoutId = R.layout.item_empty_view;
    private final EmptyItemViewModelListener mListener;

    public EmptyViewModel(EmptyItemViewModelListener mListener) {
           this.mListener = mListener;
    }

    @Override
    public int getLayoutId() {
        return layoutId;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    public void onRetryClick() {
        if (mListener != null)
            mListener.onRetryClick();
    }

    public interface EmptyItemViewModelListener {
        void onRetryClick();
    }
}
