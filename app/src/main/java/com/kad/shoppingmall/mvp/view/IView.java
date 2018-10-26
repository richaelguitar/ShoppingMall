package com.kad.shoppingmall.mvp.view;


/**
 * view操作接口
 *
 * @author xww
 * @since 2018-10-23
 */
public interface IView {

    void showLoading();

    void hideLoading();

    void showProgress(int progress);
}
