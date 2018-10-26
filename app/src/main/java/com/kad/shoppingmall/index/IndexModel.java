package com.kad.shoppingmall.index;

import android.os.Handler;
import android.os.Looper;

import com.kad.shoppingmall.mvp.model.IModel;
import com.kad.shoppingmall.mvp.model.ResultCallback;

import java.util.ArrayList;
import java.util.List;

public class IndexModel implements IModel<IndexEntity> {

    private List<IndexEntity> entities ;

    public IndexModel() {
        this.entities = new ArrayList<>();
        for (int i=0;i<10;i++){
            IndexEntity entity = new IndexEntity("https://360kad.com");
            entities.add(entity);
        }
    }

    @Override
    public void save(ResultCallback<IndexEntity> callback) {

    }

    @Override
    public void update(ResultCallback<IndexEntity> callback) {

    }

    @Override
    public void list(ResultCallback<List<IndexEntity>> callback) {
        callback.onSuccess(entities);
    }

    @Override
    public void query(ResultCallback<IndexEntity> callback) {

    }

    @Override
    public void fetch(final ResultCallback<List<IndexEntity>> callback) {
        callback.onStart();
       new Thread(new Runnable() {
           @Override
           public void run() {
               new Handler(Looper.getMainLooper()).post(new Runnable() {
                   @Override
                   public void run() {
                       callback.onSuccess(entities);
                       callback.onCompete();
                   }
               });
           }
       }).start();
    }

    @Override
    public void get(ResultCallback<IndexEntity> callback) {

    }

    @Override
    public void post(ResultCallback<IndexEntity> callback) {

    }
}
