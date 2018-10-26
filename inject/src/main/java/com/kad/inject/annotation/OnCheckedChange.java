package com.kad.inject.annotation;

import android.widget.RadioGroup;

import com.kad.inject.annotation.inner.OnEvent;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author xww
 * @since  2018-10-22
 * RadioGroup的选择变化监听
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@OnEvent(listenerSetter = "setOnCheckedChangeListener",listenerType = RadioGroup.OnCheckedChangeListener.class,callbackMethod = "onCheckedChanged")
public @interface OnCheckedChange {
    int[] value();
}
