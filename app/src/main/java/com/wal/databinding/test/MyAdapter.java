package com.wal.databinding.test;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

class MyAdapter extends RecyclerView.Adapter {
    List<DataBean> list = new ArrayList<DataBean>();

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewDataBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext())
                , R.layout.item, parent, false);
        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((MyViewHolder) holder).bindData(list.get(position));
        ((MyViewHolder) holder).getBinding().executePendingBindings();
    }

    public void setList(List<DataBean> list) {
        this.list = list;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        ViewDataBinding viewDataBinding;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        public MyViewHolder(ViewDataBinding viewDataBinding) {
            super(viewDataBinding.getRoot());
            this.viewDataBinding = viewDataBinding;
        }

        public void bindData(DataBean dataBean) {
            viewDataBinding.setVariable(BR.dataBean,dataBean);
        }

        public ViewDataBinding getBinding() {
            return viewDataBinding;
        }
    }
}
