package com.kad.inject.annotation.inner;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author xww
 * @since 2018-10-22
 * 控件注入
 */
@Target(ElementType.FIELD)//注解对象为类，接口，枚举的字段
@Retention(RetentionPolicy.RUNTIME)//生命周期source<class<runtime
public @interface BindView {
    int value();
}
