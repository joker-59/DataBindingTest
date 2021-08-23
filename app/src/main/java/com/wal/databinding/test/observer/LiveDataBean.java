package com.wal.databinding.test.observer;

public class LiveDataBean<T> {
    int DATA_TYPR;
    T data;

    public LiveDataBean(int DATA_TYPR, T data) {
        this.DATA_TYPR = DATA_TYPR;
        this.data = data;
    }

    public int getDATA_TYPR() {
        return DATA_TYPR;
    }

    public void setDATA_TYPR(int DATA_TYPR) {
        this.DATA_TYPR = DATA_TYPR;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
