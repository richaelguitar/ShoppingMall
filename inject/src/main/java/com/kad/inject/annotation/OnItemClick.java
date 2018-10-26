package com.kad.inject.annotation;

import android.widget.AdapterView;

import com.kad.inject.annotation.inner.OnEvent;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author xww
 * @since 2018-10-23
 * ListView的item点击事件
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@OnEvent(listenerSetter = "setOnItemClickListener",listenerType =AdapterView.OnItemClickListener.class ,callbackMethod = "onItemClick")
public @interface OnItemClick {
    int value();
}
