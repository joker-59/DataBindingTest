package com.wal.databinding.test.model;

import android.util.Log;

import com.wal.databinding.test.DataBean;

import java.util.ArrayList;
import java.util.List;

public class BaseModel {

    private static String TAG = "BaseModel";
    private List<DataBean> data ;

    public BaseModel(){
        init();
    }

    private void init(){
        Log.e(TAG,"init");
    }

    public List<DataBean> initData(int num){
        data = new ArrayList<DataBean>();
        for (int i =0; i < num; i++){
            data.add(new DataBean(i,i+""));
        }
        return data;
    }

    public void changeData(int position,DataBean bean) {
        DataBean dataBean = data.get(position);
        dataBean.setNO_i(bean.getNO_i());
        dataBean.setDec(bean.getDec());
    }

    public void addData() {
        data.add(new DataBean(data.size()*2,data.size()+""));
    }

    public List<DataBean> getData() {
        return data;
    }
}
