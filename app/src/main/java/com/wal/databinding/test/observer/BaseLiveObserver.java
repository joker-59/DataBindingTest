package com.wal.databinding.test.observer;

import androidx.lifecycle.Observer;

public abstract class BaseLiveObserver<T> implements Observer<T> {
    public static final int UPDATE_ALL_UI = 0;
    public static final int UPDATE_ITEM = 1;

    public abstract void onDataChange(T t);

    @Override
    public void onChanged(T t) {
        onDataChange(t);
    }
}
