package com.kad.shoppingmall.basic;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kad.shoppingmall.App;
import com.kad.shoppingmall.mvp.presenter.BasePresenter;
import com.kad.shoppingmall.mvp.view.IView;

import java.lang.ref.WeakReference;

public abstract class BasicPresenterFragment<V extends IView,T extends BasePresenter<V>> extends BasicFragment {

    private T presenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = super.onCreateView(inflater,container,savedInstanceState);
        presenter = createPresenter();
        presenter.onAttachView((V)this);
        return  view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }


    public abstract T createPresenter();



    @Override
    public void onDetach() {
        super.onDetach();
        if(presenter!=null){
            presenter.onDetachView();
        }
    }

}
