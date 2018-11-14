package com.example.sekini.utils.recycler;

import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.example.sekini.BR;
import com.example.sekini.utils.recycler.helper.ItemTouchHelperAdapter;
import com.example.sekini.utils.recycler.helper.ItemTouchHelperViewHolder;
import com.example.sekini.utils.recycler.helper.OnStartDragListener;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;


public class EasyAdapter<T extends BaseRecyclerView> extends RecyclerView.Adapter<EasyAdapter.ViewHolder> implements ItemTouchHelperAdapter, OnStartDragListener {

    private OnStartDragListener dragStartListener;

    private AdapterMode adapterMode;
    private List<T> list;
    private EmptyViewModel.EmptyItemViewModelListener emptyItemViewModelListener ;
    private BaseRecyclerView emptyViewModel ;

    public EasyAdapter() {
        emptyViewModel=new EmptyViewModel(null);
        this.list = new LinkedList<>();
        adapterMode = AdapterMode.None;
    }

    public EasyAdapter(AdapterMode adapterMode) {
        this.list = new LinkedList<>();
        this.adapterMode = adapterMode;
        emptyViewModel=new EmptyViewModel(null);
    }

    public EasyAdapter(List<T> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @BindingAdapter(value = {"data", "mode"}, requireAll = false)
    public static void setData(RecyclerView recycler, List<BaseRecyclerView> models, AdapterMode adapterMode) {

        if (recycler.getAdapter() instanceof EasyAdapter) {
            ((EasyAdapter) recycler.getAdapter()).insertList(models);
        } else {
            EasyAdapter<BaseRecyclerView> adapter1;
            if (adapterMode != null) {
                adapter1 = new EasyAdapter<>(adapterMode);
            } else {
                adapter1 = new EasyAdapter<>();
            }
            adapter1.insertList(models);
            recycler.setAdapter(adapter1);
        }


    }

    public void setDragStartListener(OnStartDragListener mDragStartListener) {
        this.dragStartListener = mDragStartListener;
    }

    public List<T> getSelectedItem() {
        List<T> lst = new LinkedList<>();
        for (T t : list) {
            if (t.isSelected())
                lst.add(t);
        }
        return lst;
    }



    public void init(List<T> list1) {
        this.list = list1;
        notifyDataSetChanged();
    }

    public void insertList(List<T> list1) {
        this.list.addAll(0, list1);
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        if (list.isEmpty()) {
            return emptyViewModel.getLayoutId();
        } else {
            return list.get(position).getLayoutId();
        }
    }

    @Override
    public int getItemCount() {
        if (list.size() == 0)
            return 1;
        else return list.size();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        try {
            ViewDataBinding bind = DataBindingUtil.bind(LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false));
            return new ViewHolder<>(bind);
        } catch (Exception ex) {
            ex.getMessage();
            return null;
        }
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (list.size() != 0) {
            final T model = list.get(position);
            holder.getBinding().setVariable(model.getBindingVariable(), model);
            switch (adapterMode) {
                case MultiChoose:
                case OneChoose:
                    model.setAdapterOnClick(() -> {
                        if (adapterMode == AdapterMode.OneChoose) {
                            for (int i = 0; i < list.size(); i++) {
                                if (list.get(i).isSelected() && position != i) {
                                    list.get(i).setSelected(false);
                                    notifyItemChanged(i);
                                }
                            }
                        }
                        notifyItemChanged(position);
                    });
                    break;

                case DragDrop:
                    model.setOnTouchListener((View v, MotionEvent event) -> {
                        switch (event.getAction()) {
                            case MotionEvent.ACTION_DOWN:
                                dragStartListener.onStartDrag(holder);
                                break;
                            case MotionEvent.ACTION_UP:
                                v.performClick();
                                break;
                            default:
                                break;
                        }
                        return true;
                    });
                    break;
            }


        } else {
            if(emptyViewModel==null){
                emptyViewModel=new EmptyViewModel(emptyItemViewModelListener);
            }
            holder.getBinding().setVariable(BR.viewModel, emptyViewModel);
        }

        holder.getBinding().executePendingBindings();
    }

    @Override
    public void onItemDismiss(int position) {
        list.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        Collections.swap(list, fromPosition, toPosition);
        notifyItemMoved(fromPosition, toPosition);
        return true;
    }

    @Override
    public void onStartDrag(RecyclerView.ViewHolder viewHolder) {

    }

    public void setmListener(EmptyViewModel.EmptyItemViewModelListener mListener) {
        this.emptyItemViewModelListener = mListener;
    }

    public static class ViewHolder<V extends ViewDataBinding> extends RecyclerView.ViewHolder implements
            ItemTouchHelperViewHolder {
        Drawable background;
        private V v;

        ViewHolder(V v) {
            super(v.getRoot());
            this.v = v;
        }

        public V getBinding() {
            return v;
        }

        @Override
        public void onItemSelected() {
            background = itemView.getBackground();
            itemView.setBackgroundColor(Color.LTGRAY);
        }

        @Override
        public void onItemClear() {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                itemView.setBackground(background);
            } else {
                itemView.setBackgroundDrawable(background);
            }
        }
    }

    public void setEmptyLayout(BaseRecyclerView emptyLayout) {
        this.emptyViewModel=emptyLayout;
    }

}