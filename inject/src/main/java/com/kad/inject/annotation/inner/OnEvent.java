package com.kad.inject.annotation.inner;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 元注解，作用在注解上面，
 * 用于包装对象，需要组装这样一个对象
 *
 * listenerSetter--》setOnClickListener
 * listenerType--》View.OnClickListener
 * callbackMethod--》onClick
 * eg:
 * showTextView.setOnClickListener(new View.OnClickListener() {
 *             @Override
 *             public void onClick(View view) {
 *
 *             }
 *         });
 * @author xww
 * @since 2018-10-22
 */
@Target(ElementType.ANNOTATION_TYPE)//注解在注解上的注解，称为元注解
@Retention(RetentionPolicy.RUNTIME)
public @interface OnEvent {
    String listenerSetter();
    Class<?> listenerType();
    String callbackMethod();
}
