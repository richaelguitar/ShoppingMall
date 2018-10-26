package com.kad.inject;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.view.View;

import com.kad.inject.annotation.inner.BindView;
import com.kad.inject.annotation.inner.ContentView;
import com.kad.inject.annotation.inner.OnEvent;
import com.kad.inject.handler.InjectProxyHandler;

import java.lang.annotation.Annotation;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

/**
 * @author xww
 * @since 2018-10-22
 * @version 0.0.1
 * 注入工具类
 */
public class InjectUtils {

    /**
     * 静态单例对象
     */
    private static InjectUtils injectUtils;

    /**
     * Activity对象的弱引用
     */
    private WeakReference<Activity> weakReference ;

    /**
     * 获取单例
     * @return InjectUtils
     */
    public static InjectUtils with(){
        if(injectUtils == null){
            synchronized (InjectUtils.class){
                if(injectUtils == null){
                    injectUtils = new InjectUtils();
                }
            }
        }
        return injectUtils;
    }

    /**
     * Activity的注入
     * @param activity
     */
    public void  inject(Activity activity){
        weakReference = new WeakReference<>(activity);
        if(weakReference.get()!=null){
            injectLayout(weakReference.get());
            injectViews(weakReference.get());
            injectEvents(weakReference.get());
        }
    }

    /**
     * fragment的注入
     * @param fragment
     * @param rootView
     */
    public void  inject(Fragment fragment,View rootView){
        injectViews(fragment,rootView);
        injectEvents(fragment,rootView);
    }


    /**
     * 控件注入
     * @param fragment
     * @param rootView
     */
    private void injectViews(Fragment fragment,View rootView) {
         //获取fragment上的所有声明字段
        Field[] fields = fragment.getClass().getDeclaredFields();
        //遍历字段
        for (Field field:fields) {
            //获取字段上的注解
            BindView bindView = field.getAnnotation(BindView.class);
            if(bindView!=null){
                //获取注解上的值
                int viewId = bindView.value();
                //根据id找到view控件
                View view = rootView.findViewById(viewId);
                //把控件赋值给字段
                if(view!=null){
                    field.setAccessible(true);
                    try {
                        field.set(fragment,view);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    /**
     * 注入事件
     * @param fragment
     * @param rootView
     */
    private void injectEvents(Fragment fragment,View rootView) {
        //获取fragment上所有声明的方法,不会包括继承的方法
        Method[] methods = fragment.getClass().getDeclaredMethods();
        //遍历方法
        for (Method method:methods) {
            //获取方法上的注解,会存在多个注解的情况
            Annotation[] annotations = method.getAnnotations();
            //遍历注解
            for (Annotation annotation:annotations) {
                //获取注解类型，用于获取元注解
                Class<?> annotationType = annotation.annotationType();
                //根据注解类型获取元注解
                OnEvent onEvent  = annotationType.getAnnotation(OnEvent.class);
                if(onEvent!=null){
                    //获取元注解的值
                    String listenerSetter = onEvent.listenerSetter();
                    Class<?> listenerType = onEvent.listenerType();
                    String callbackMethod = onEvent.callbackMethod();
                    //获取注解的值
                    try {
                        Method valueMethod = annotationType.getMethod("value");
                        //获取viewIds
                        int[] viewIds = (int[])valueMethod.invoke(annotation);
                        //遍历viewIds，获取控件
                        for (int viewId:viewIds) {
                            //获取控件
                            View view = rootView.findViewById(viewId);
                            //获取view的listenerSetter方法
                            Method listenerSetterMethod = view.getClass().getMethod(listenerSetter,listenerType);
                            //回调我们自定义的方法,这里要使用动态代理的模式去拦截listenerSetter方法
                            Map<String, Method> callbackMethodMap = new HashMap<>();
                            callbackMethodMap.put(callbackMethod,method);
                            InjectProxyHandler<Fragment> handler = new InjectProxyHandler(fragment,callbackMethodMap);
                            Object proxy = Proxy.newProxyInstance(listenerType.getClassLoader(),new Class<?>[]{listenerType},handler);
                            //调用view的listenerSetter方法
                            listenerSetterMethod.invoke(view,proxy);

                        }
                    } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
            }

        }
    }


    /**
     * 注入布局
     * @param activity
     */
    private void injectLayout(Activity activity) {
        //获取类上的注解
        ContentView contentView = activity.getClass().getAnnotation(ContentView.class);
        if(contentView!=null){
            //获取注解的值
            int layoutId = contentView.value();
            //获取Activity的setContentView方法
            try {

               Method method = activity.getClass().getMethod("setContentView", int.class);
               method.invoke(activity,layoutId);

            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * 注入控件
     * @param activity
     */
    private void injectViews(Activity activity) {
        //获取类的声明字段
        Field[] fields = activity.getClass().getDeclaredFields();
        //遍历字段
        for (Field field:fields) {
            //获取字段上的注解类
           BindView bindView =  field.getAnnotation(BindView.class);
           if(bindView!=null){
               //获取注解的值
               int viewId = bindView.value();
               //获取控件
               View view = activity.findViewById(viewId);
               //对字段设置控件的值
               field.setAccessible(true);
               try {
                   field.set(activity,view);
               } catch (IllegalAccessException e) {
                   e.printStackTrace();
               }
           }
        }
    }

    /**
     * 注入事件
     * @param activity
     */
    private void injectEvents(Activity activity) {
        //获取类里面声明的所有方法
        Method[] methods = activity.getClass().getDeclaredMethods();
        //遍历方法
        for (Method method:methods) {
            //获取方法上的注解
            Annotation[] annotations =  method.getAnnotations();
            if(annotations!=null){
                //遍历注解
                for (Annotation annotation:annotations) {
                    //获取注解类型比如@OnClick
                    Class<?> annotationType = annotation.annotationType();
                    //根据注解类型获取注解上的注解@OnEvent
                    OnEvent onEvent = annotationType.getAnnotation(OnEvent.class);
                    if(onEvent!=null){
                        //获取元注解上的值
                        String listenerSetter = onEvent.listenerSetter();
                        Class<?> listenerType = onEvent.listenerType();
                        String callbackMethod = onEvent.callbackMethod();
                        //获取注解的值
                        try {
                            Method  value = annotationType.getDeclaredMethod("value");
                            int[] viewIds = (int[])value.invoke(annotation);
                            //遍历控件
                            for (int id:viewIds) {
                                View view = activity.findViewById(id);
                                if(view!=null){
                                  //获取指定的方法
                                    Method listenerSetterMethod =   view.getClass().getMethod(listenerSetter,listenerType);
                                  //动态代理
                                    Map<String,Method> callbackMethodMap = new HashMap<>();
                                    callbackMethodMap.put(callbackMethod,method);
                                    InjectProxyHandler<Activity> handler = new InjectProxyHandler<>(activity,callbackMethodMap);
                                    Object proxy = Proxy.newProxyInstance(listenerType.getClassLoader(),new Class<?>[]{listenerType},handler);
                                    listenerSetterMethod.invoke(view,proxy);
                                }
                            }
                        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException  e) {
                            e.printStackTrace();
                        }
                    }

                }
            }
        }
    }


    /**
     * 释放引用
     */
    public void onDetach(){
        if(isInjected()){
            weakReference.clear();
            weakReference = null;
        }
    }

    /**
     * 是否已经注入
     * @return true 表示为已经注入
     * false 表示未注入
     */
    public boolean  isInjected(){
        return weakReference!=null&&weakReference.get()!=null;
    }
}
