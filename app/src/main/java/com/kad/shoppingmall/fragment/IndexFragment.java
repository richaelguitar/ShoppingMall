package com.kad.shoppingmall.fragment;


import android.graphics.Color;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.kad.inject.annotation.inner.BindView;
import com.kad.inject.annotation.OnClick;
import com.kad.inject.annotation.OnLongClick;
import com.kad.shoppingmall.R;
import com.kad.shoppingmall.basic.BasicPresenterFragment;
import com.kad.shoppingmall.index.IndexEntity;
import com.kad.shoppingmall.index.IndexPresenter;
import com.kad.shoppingmall.index.IndexView;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link IndexFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class IndexFragment extends BasicPresenterFragment<IndexView,IndexPresenter> implements IndexView {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    @BindView(R.id.tv_content)
    private TextView tvContent;

    private IndexPresenter indexPresenter;

    private ListView listView;


    public IndexFragment() {
        // Required empty public constructor
    }


    public static IndexFragment newInstance(String param1, String param2) {
        IndexFragment fragment = new IndexFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }





    @Override
    public int getLayoutId() {
        return R.layout.fragment_index;
    }

    @Override
    public void initData() {
        tvContent.setText("控件注入成功");
        indexPresenter.fetch();
    }



    @OnClick(R.id.btn_fragment_click)
    public void show(View view){
        tvContent.setTextColor(Color.RED);
        tvContent.setText("点击事件注入成功");
        Toast.makeText(getActivity(),"点击事件注入成功",Toast.LENGTH_SHORT).show();
    }

    @OnLongClick(R.id.btn_fragment_long_click)
    public boolean showLong(View view){
        tvContent.setTextColor(Color.GREEN);
        tvContent.setText("长按事件注入成功");
        Toast.makeText(getActivity(),"长按事件注入成功",Toast.LENGTH_SHORT).show();
        return true;
    }

    @Override
    public IndexPresenter createPresenter() {
        indexPresenter = new IndexPresenter();

        return indexPresenter;
    }

    @Override
    public void showLoading() {
        Toast.makeText(getActivity(),"开始加载",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void hideLoading() {
        Toast.makeText(getActivity(),"加载完成",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showProgress(int progress) {
        Toast.makeText(getActivity(),"加载中",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void fetch(List<IndexEntity> list) {
        Toast.makeText(getActivity(),list.get(0).getPicUrl(),Toast.LENGTH_SHORT).show();
    }
}
