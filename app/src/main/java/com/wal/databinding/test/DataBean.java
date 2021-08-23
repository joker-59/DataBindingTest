package com.wal.databinding.test;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.BindingAdapter;

public class DataBean extends BaseObservable {
    private int NO_i;

    private  String dec;

    @Override
    public String toString() {
        return "DataBean{" +
                "NO_i=" + NO_i +
                ", dec='" + dec + '\'' +
                '}';
    }

    public DataBean(int NO_i) {
        this.NO_i = NO_i;
    }

    public DataBean(int NO_i, String dec) {
        this.NO_i = NO_i;
        this.dec = dec;
    }
    @Bindable
    public String getDec() {
        return dec;
    }

    public void setDec(String dec) {
        this.dec = dec;
        notifyPropertyChanged(BR.dec);
    }
    @Bindable
    public int getNO_i() {
        return NO_i;
    }

    public void setNO_i(int NO_i) {
        this.NO_i = NO_i;
        notifyPropertyChanged(BR.nO_i);
    }
}

