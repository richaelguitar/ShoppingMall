package com.kad.shoppingmall.basic;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kad.inject.InjectUtils;

/**
 * @author xww
 * @since 2018-10-24
 * Fragment基类
 */
public abstract class BasicFragment extends Fragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutId(), container, false);
        InjectUtils.with().inject(this,view);

        return  view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    public abstract void initData();

    public abstract int getLayoutId();




    @Override
    public void onDetach() {
        super.onDetach();
        InjectUtils.with().onDetach();
    }
}
