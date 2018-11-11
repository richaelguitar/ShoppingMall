package com.kad.shoppingmall.utils;

import android.widget.Toast;

import com.kad.shoppingmall.App;

public class ToastUtil {

    public static void showToastLong(String text){
        Toast.makeText(App.getInstance(),text,Toast.LENGTH_LONG).show();
    }

    public static void showToastShort(String text){
        Toast.makeText(App.getInstance(),text,Toast.LENGTH_SHORT).show();
    }
}
