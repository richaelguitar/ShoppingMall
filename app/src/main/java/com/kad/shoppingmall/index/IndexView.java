package com.kad.shoppingmall.index;

import com.kad.shoppingmall.mvp.view.IView;

import java.util.List;

public interface IndexView extends IView {

    void fetch(List<IndexEntity> list);
}
