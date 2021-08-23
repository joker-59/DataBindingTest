package com.wal.databinding.test;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.wal.databinding.test.databinding.ActivityMainBinding;
import com.wal.databinding.test.observer.BaseLiveObserver;
import com.wal.databinding.test.observer.LiveDataBean;
import com.wal.databinding.test.viewmodel.BaseVIewModel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    MyAdapter myAdapter;
    ActivityMainBinding mainBinding;

    private BaseVIewModel baseVIewModel;
    Map map = new HashMap();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        mainBinding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        map.put("key1","modify");
        map.put("key2","add");
        mainBinding.setButtonText(map);

//        RecyclerView recyclerView = findViewById(R.id.my_lv);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
//        recyclerView.setLayoutManager(layoutManager);
//        MyAdapter myAdapter = new MyAdapter();
//        recyclerView.setAdapter(myAdapter);
        initLiveData();
        setViewModel(baseVIewModel);


        myAdapter  = new MyAdapter();
        myAdapter.setList(baseVIewModel.initData(10));
        mainBinding.myLv.setLayoutManager(layoutManager);
        mainBinding.myLv.setAdapter(myAdapter);

        mainBinding.setHandler(new OnClickHandler());

    }


    private void initLiveData() {
        // 创建一个持有某种数据类型的LiveData (通常是在ViewModel中)
        // 创建一个定义了onChange()方法的观察者
        // 开始订阅
        baseVIewModel = ViewModelProviders.of(this).get(BaseVIewModel.class);

        final BaseLiveObserver<LiveDataBean> baseObserver = new BaseLiveObserver<LiveDataBean>() {
            @Override
            public void onDataChange(LiveDataBean liveDataBean) {
                switch (liveDataBean.getDATA_TYPR()) {
                    case BaseLiveObserver.UPDATE_ALL_UI:
                        Log.e("wallog","UPDATE_ALL_UI :" + liveDataBean.getData());
                        List dataBeans = (List)liveDataBean.getData();
                        map.put("key2", "add total:" + dataBeans.size());

                        myAdapter.notifyDataSetChanged();
                        mainBinding.myLv.smoothScrollToPosition(dataBeans.size() - 1);
                        mainBinding.setButtonText(map);
                        break;
                    case BaseLiveObserver.UPDATE_ITEM:
                        Log.e("wallog","UPDATE_ITEM :" + liveDataBean.getData());
                        Integer position = (Integer)liveDataBean.getData();
                        mainBinding.myLv.smoothScrollToPosition(position);
                        break;
                }
            }
        };
        baseVIewModel.getCurrentLiveData().observe(this,baseObserver);

//        final Observer<List<DataBean>> nameObserver = new Observer<List<DataBean>>() {
//            @Override
//            public void onChanged(@Nullable final List<DataBean> dataBeans) {
//                // 更新数据
//
//                map.put("key2","add :"+ dataBeans.size());
//
//                myAdapter.notifyDataSetChanged();
//                mainBinding.myLv.smoothScrollToPosition(dataBeans.size()-1);
//                mainBinding.setButtonText(map);
//            }
//        };
//        // 通过 observe()方法连接观察者和LiveData，注意：observe()方法需要携带一个LifecycleOwner类
//        baseVIewModel.getCurrentData().observe(this, nameObserver);
    }

    public void setViewModel(BaseVIewModel viewModel) {
        this.baseVIewModel = viewModel;
    }

    public class OnClickHandler {
        public void onClickFriend(View view) {
            if (view.getId() == R.id.tv) {
                Toast.makeText(MainActivity.this,"change", Toast.LENGTH_SHORT).show();

                baseVIewModel.changeData(4,new DataBean(1000,"james"));
//                myAdapter.notifyDataSetChanged();
//                mainBinding.myLv.smoothScrollToPosition(4);
            } else {

                Toast.makeText(MainActivity.this,"add", Toast.LENGTH_SHORT).show();
                baseVIewModel.addData();
            }
        }
    }
}