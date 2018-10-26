package com.kad.inject.annotation;

import android.view.View;

import com.kad.inject.annotation.inner.OnEvent;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author xww
 * @since 2018-10-22
 * 事件注入
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@OnEvent(listenerSetter = "setOnClickListener",listenerType =View.OnClickListener.class ,callbackMethod = "onClick")
public @interface OnClick {
    int[] value();
}
