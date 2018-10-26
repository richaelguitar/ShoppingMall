package com.kad.shoppingmall.mvp.presenter;

import com.kad.shoppingmall.mvp.view.IView;

import java.lang.ref.WeakReference;

public abstract class BasePresenter<V extends IView> {


    private WeakReference<V> viewWeakReference;


    public void  onAttachView(V view){
        if(viewWeakReference == null){
            viewWeakReference = new WeakReference<>(view);
        }
    }

    public void onDetachView(){
        if(viewWeakReference!=null){
            viewWeakReference.clear();
        }
    }


    public V getTargetView() {
        return isAttachedView()?viewWeakReference.get():null;
    }

    private boolean isAttachedView(){

        return viewWeakReference!=null&&viewWeakReference.get()!=null;
    }
}
