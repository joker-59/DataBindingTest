package com.wal.databinding.test.viewmodel;

import androidx.databinding.BaseObservable;
import androidx.databinding.ObservableField;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.wal.databinding.test.DataBean;
import com.wal.databinding.test.model.BaseModel;
import com.wal.databinding.test.observer.BaseLiveObserver;
import com.wal.databinding.test.observer.LiveDataBean;

import java.util.List;
import java.util.Map;

public class BaseVIewModel extends ViewModel {
    private MutableLiveData<List<DataBean>> mCurrentData;
    private MutableLiveData<LiveDataBean> mCurrentLiveData;
    public static ObservableField<String> textObservable = new ObservableField<>("default");
    //   public static ObservableBoolean observableBoolean = new ObservableBoolean();
    //引用数据类型
//    public static ObservableField<DataBean> testOneObservable = new ObservableField<>();

    private BaseModel model;

    public BaseVIewModel() {
        model = new BaseModel();
    }

    public MutableLiveData<LiveDataBean> getCurrentLiveData() {
        if (mCurrentData == null) {
            mCurrentLiveData = new MutableLiveData<LiveDataBean>();
        }
        return mCurrentLiveData;
    }

    public MutableLiveData<List<DataBean>> getCurrentData() {
        if (mCurrentData == null) {
            mCurrentData = new MutableLiveData<>();
        }
        return mCurrentData;
    }

    public List<DataBean> initData(int num) {
        return model.initData(num);
    }

    public void changeData(int position, DataBean bean) {
        model.changeData(position, bean);
        textObservable.set("change :"  + position);
        notifyChange(BaseLiveObserver.UPDATE_ITEM,Integer.parseInt(String.valueOf(position)));
    }


    public void addData() {
        model.addData();
        textObservable.set("switch");
//        mCurrentData.setValue(model.getData());
        notifyChange(BaseLiveObserver.UPDATE_ALL_UI,model.getData());
    }

    private void notifyChange(int DATA_TYPR, Object dat){
        mCurrentLiveData.setValue(new LiveDataBean(DATA_TYPR,dat));
    }

}
