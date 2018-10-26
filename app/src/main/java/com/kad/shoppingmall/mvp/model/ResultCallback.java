package com.kad.shoppingmall.mvp.model;

public interface ResultCallback<T> {
    void onCompete();
    void onStart();
    void onSuccess(T t);
    void onFailed(T t);
    void onProgress(int progress);
}
