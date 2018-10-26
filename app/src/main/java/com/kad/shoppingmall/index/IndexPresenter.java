package com.kad.shoppingmall.index;

import com.kad.shoppingmall.mvp.model.ResultCallback;
import com.kad.shoppingmall.mvp.presenter.BasePresenter;

import java.util.List;


public class IndexPresenter extends BasePresenter<IndexView> {

    private IndexModel indexModel = new IndexModel();


    public void  fetch(){
        indexModel.fetch(new ResultCallback<List<IndexEntity>>() {
            @Override
            public void onCompete() {
                getTargetView().hideLoading();
            }

            @Override
            public void onStart() {
                getTargetView().showLoading();
            }

            @Override
            public void onSuccess(List<IndexEntity> indexEntities) {
                getTargetView().fetch(indexEntities);
            }

            @Override
            public void onFailed(List<IndexEntity> indexEntities) {
                getTargetView().hideLoading();
            }

            @Override
            public void onProgress(int progress) {

            }
        });
    }



}
