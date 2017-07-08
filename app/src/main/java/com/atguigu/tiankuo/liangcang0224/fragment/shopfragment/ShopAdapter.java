package com.atguigu.tiankuo.liangcang0224.fragment.shopfragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.atguigu.tiankuo.liangcang0224.fragment.shopfragment.fragment.BrandFragment;
import com.atguigu.tiankuo.liangcang0224.fragment.shopfragment.fragment.ClassifyFragment;
import com.atguigu.tiankuo.liangcang0224.fragment.shopfragment.fragment.GiftFragment;
import com.atguigu.tiankuo.liangcang0224.fragment.shopfragment.fragment.HomeFragment;
import com.atguigu.tiankuo.liangcang0224.fragment.shopfragment.fragment.SpecialFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：田阔
 * 邮箱：1226147264@qq.com
 * Created by Administrator on 2017/7/8 0008.
 */

public class ShopAdapter extends FragmentPagerAdapter {

    private List<Fragment> fragmentList;
    private String[] titles = new String[]{"分类", "品牌", "首页", "专题", "礼物"};

    public ShopAdapter(FragmentManager fm) {
        super(fm);
        initFragments();
    }

    private void initFragments() {
        fragmentList = new ArrayList<>();

        ClassifyFragment classifyFragment = new ClassifyFragment();
        fragmentList.add(classifyFragment);

        BrandFragment brandFragment = new BrandFragment();
        fragmentList.add(brandFragment);

        HomeFragment homeFragment = new HomeFragment();
        fragmentList.add(homeFragment);

        SpecialFragment specialFragment = new SpecialFragment();
        fragmentList.add(specialFragment);

        GiftFragment giftFragment = new GiftFragment();
        fragmentList.add(giftFragment);
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
