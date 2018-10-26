package com.kad.shoppingmall.basic;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.kad.inject.InjectUtils;

/**
 * @author xww
 * @since 2018-10-22
 * Activity父类
 */
public abstract class BasicActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //注入
        InjectUtils.with().inject(this);
        initData();
    }

    public abstract void initData();


    @Override
    protected void onDestroy() {
        super.onDestroy();
        InjectUtils.with().onDetach();
    }


}
