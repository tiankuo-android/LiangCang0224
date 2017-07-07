package com.atguigu.tiankuo.liangcang0224.fragment.creamfragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.atguigu.tiankuo.liangcang0224.fragment.creamfragment.fragment.EpisodeFragment;
import com.atguigu.tiankuo.liangcang0224.fragment.creamfragment.fragment.RecommendFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：田阔
 * 邮箱：1226147264@qq.com
 * Created by Administrator on 2017/7/7 0007.
 */

public class CreamAdapter extends FragmentPagerAdapter {

    private List<Fragment> fragmentList;
    private String[] titles = new String[]{"推荐", "段子"};


    public CreamAdapter(FragmentManager fm) {
        super(fm);
        initFragments();
    }

    private void initFragments() {
        fragmentList = new ArrayList<>();

        RecommendFragment recommendFragment = new RecommendFragment();
        fragmentList.add(recommendFragment);

        EpisodeFragment episodeFragment = new EpisodeFragment();
        fragmentList.add(episodeFragment);
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
