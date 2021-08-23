package com.wal.databinding.test;

import android.widget.TextView;

import androidx.databinding.BindingAdapter;

public class DesHelp {

    @BindingAdapter({"textValue"})
    public static void loadDes(TextView deTv,String value){
        deTv.setText("custom :"+value);
    }
}
