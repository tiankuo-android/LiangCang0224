package com.atguigu.tiankuo.liangcang0224;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;
import android.widget.RadioGroup;

import com.atguigu.tiankuo.liangcang0224.fragment.darenfragment.DarenFragment;
import com.atguigu.tiankuo.liangcang0224.fragment.creamfragment.CreamFragment;
import com.atguigu.tiankuo.liangcang0224.fragment.ShopFragment;
import com.atguigu.tiankuo.liangcang0224.fragment.UserFragment;
import com.atguigu.tiankuo.liangcang0224.fragment.magazinefragment.MagazineFragment;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainActivity extends AppCompatActivity {

    @InjectView(R.id.frameLayout)
    FrameLayout frameLayout;
    @InjectView(R.id.rg_main)
    RadioGroup rgMain;

    private CreamFragment creamFragment;
    private DarenFragment darenFragment;
    private MagazineFragment magazineFragment;
    private ShopFragment shopFragment;
    private UserFragment userFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        initListener();
        initView();
    }

    private void initListener() {
        rgMain.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switchFragment(checkedId);
            }
        });
    }

    public void initView() {
        switchFragment(R.id.rb_shop);
    }

    //切換Fragment
    private void switchFragment(int checkedId) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        hidden(transaction);
        switch (checkedId) {
            case R.id.rb_cream:
                if (creamFragment == null) {
                    creamFragment = new CreamFragment();
                    transaction.add(R.id.frameLayout, creamFragment);
                } else {
                    transaction.show(creamFragment);
                }
                break;
            case R.id.rb_daren:
                if (darenFragment == null) {
                    darenFragment = new DarenFragment();
                    transaction.add(R.id.frameLayout, darenFragment);
                } else {
                    transaction.show(darenFragment);
                }
                break;
            case R.id.rb_magazine:
                if (magazineFragment == null) {
                    magazineFragment = new MagazineFragment();
                    transaction.add(R.id.frameLayout, magazineFragment);
                } else {
                    transaction.show(magazineFragment);
                }
                break;
            case R.id.rb_shop:
                if (shopFragment == null) {
                    shopFragment = new ShopFragment();
                    transaction.add(R.id.frameLayout, shopFragment);
                } else {
                    transaction.show(shopFragment);
                }
                break;
            case R.id.rb_user:
                if (userFragment == null) {
                    userFragment = new UserFragment();
                    transaction.add(R.id.frameLayout, userFragment);
                } else {
                    transaction.show(userFragment);
                }
                break;
        }
        transaction.commit();
    }

    private void hidden(FragmentTransaction transaction) {
        if (creamFragment != null) {
            transaction.hide(creamFragment);
        }
        if (darenFragment != null) {
            transaction.hide(darenFragment);
        }
        if (magazineFragment != null) {
            transaction.hide(magazineFragment);
        }
        if (shopFragment != null) {
            transaction.hide(shopFragment);
        }
        if (userFragment != null) {
            transaction.hide(userFragment);
        }
    }


//
//    //双击退出
//    private boolean isExit = false;
//
//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if(keyCode == KeyEvent.KEYCODE_BACK) {
//            if(isExit) {
//                finish();
//            }
//            Toast.makeText(MainActivity.this, "再点击一次退出", Toast.LENGTH_SHORT).show();
//            isExit = true;
//            new Timer().schedule(new TimerTask() {
//                @Override
//                public void run() {
//                    isExit = false;
//                }
//            },2000);
//            return true;
//        }
//        return super.onKeyDown(keyCode, event);
//    }
}
