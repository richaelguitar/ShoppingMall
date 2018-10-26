package com.kad.shoppingmall.basic;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kad.shoppingmall.mvp.presenter.BasePresenter;
import com.kad.shoppingmall.mvp.view.IView;

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

    public abstract T createPresenter();



    @Override
    public void onDetach() {
        super.onDetach();
        if(presenter!=null){
            presenter.onDetachView();
        }
    }

}
