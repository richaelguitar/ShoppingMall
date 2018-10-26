package com.kad.inject.handler;

import android.app.Activity;

import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * @author  xww
 * @since 2018-10-22
 * 动态代理回调
 */
public class InjectProxyHandler<T> implements InvocationHandler {

    private WeakReference<?> weakReference;
    private Map<String,Method> callbackMethodMap;

    public InjectProxyHandler(T t, Map<String, Method> callbackMethodMap) {
        this.weakReference = new WeakReference<>(t);
        this.callbackMethodMap = callbackMethodMap;
    }

    @Override
    public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
        String callbackMethod = method.getName();
        if(callbackMethodMap.get(callbackMethod)!=null){
            Method targetMethod = callbackMethodMap.get(callbackMethod);
            return targetMethod.invoke(weakReference.get(),objects);
        }else {
            return method.invoke(weakReference.get(),objects);
        }
    }
}
