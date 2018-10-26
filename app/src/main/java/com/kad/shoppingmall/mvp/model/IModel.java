package com.kad.shoppingmall.mvp.model;

import java.util.List;

/**
 * 数据model公共接口
 * @author xww
 * @since 2018-10-23
 */
public interface IModel<T> {

    /**
     * 以下是本地数据库操作
     * @param callback
     */
    void save(ResultCallback<T> callback);

    void update(ResultCallback<T> callback);

    void list(ResultCallback<List<T>> callback);

    void query(ResultCallback<T> callback);

    /**
     * 以下都是网络请求方法
     * @param callback
     */

    void fetch(ResultCallback<List<T>> callback);

    void get(ResultCallback<T> callback);

    void post(ResultCallback<T> callback);

}
