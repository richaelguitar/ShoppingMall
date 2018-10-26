package com.kad.shoppingmall.basic;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.kad.shoppingmall.mvp.presenter.BasePresenter;
import com.kad.shoppingmall.mvp.view.IView;

public abstract class BasicPresenterActivity<V extends IView,T extends BasePresenter<V>> extends BasicActivity {

    private T presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = createPresenter();
        presenter.onAttachView((V)this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDetachView();
    }

    public abstract T createPresenter();
}
