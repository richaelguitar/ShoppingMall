package com.kad.shoppingmall;



import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.RadioButton;

import com.kad.inject.annotation.inner.BindView;
import com.kad.inject.annotation.inner.ContentView;
import com.kad.inject.annotation.OnCheckedChange;
import com.kad.shoppingmall.basic.BasicActivity;
import com.kad.shoppingmall.basic.BasicPresenterActivity;
import com.kad.shoppingmall.fragment.CartFragment;
import com.kad.shoppingmall.fragment.IndexFragment;
import com.kad.shoppingmall.fragment.SortFragment;
import com.kad.shoppingmall.fragment.UserFragment;
import com.kad.shoppingmall.index.IndexView;

import java.util.ArrayList;
import java.util.List;

@ContentView(R.layout.activity_main)
public class MainActivity extends BasicActivity {


    private static final String TAG = MainActivity.class.getSimpleName();

    @BindView(R.id.bottom_home)
    private RadioButton rbHome;

    private List<Fragment> tabList;

    @Override
    public void initData() {
        initTabList();
    }

    private void initTabList() {
        tabList = new ArrayList<>();
        Fragment homeFragment = IndexFragment.newInstance("","");
        tabList.add(homeFragment);
        Fragment sortFragment = SortFragment.newInstance("","");
        tabList.add(sortFragment);
        Fragment cartFragment = CartFragment.newInstance("","");
        tabList.add(cartFragment);
        Fragment userFragment = UserFragment.newInstance("","");
        tabList.add(userFragment);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.flt_content,homeFragment);
        fragmentTransaction.add(R.id.flt_content,sortFragment);
        fragmentTransaction.add(R.id.flt_content,cartFragment);
        fragmentTransaction.add(R.id.flt_content,userFragment);
        fragmentTransaction.commit();
        showHome();

    }


    @OnCheckedChange(R.id.radioGroup)
    public void switchTabChangeListener(View view,int id){
            switch (id){
                case R.id.bottom_home :
                    changeFragmentShow(0);
                    break;
                case R.id.bottom_sort :
                    changeFragmentShow(1);
                    break;
                case R.id.bottom_cart :
                    changeFragmentShow(2);
                    break;
                case R.id.bottom_user:
                    changeFragmentShow(3);
                    break;
            }
    }


    public void  showHome(){
        rbHome.setChecked(true);
        changeFragmentShow(0);
    }

    public void changeFragmentShow(int position){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        for (int i = 0;i<tabList.size();i++){
            if(i == position){
                fragmentTransaction.show(tabList.get(i));
            }else{
                fragmentTransaction.hide(tabList.get(i));
            }
        }
        fragmentTransaction.commitAllowingStateLoss();
    }
}
