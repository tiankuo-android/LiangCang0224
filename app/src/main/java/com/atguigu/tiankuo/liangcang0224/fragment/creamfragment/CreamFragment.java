package com.atguigu.tiankuo.liangcang0224.fragment.creamfragment;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.atguigu.tiankuo.liangcang0224.R;
import com.atguigu.tiankuo.liangcang0224.base.BaseFragment;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * 作者：田阔
 * 邮箱：1226147264@qq.com
 * Created by Administrator on 2017/7/5 0005.
 */

public class CreamFragment extends BaseFragment {

    @InjectView(R.id.iv_search)
    ImageView ivSearch;
    @InjectView(R.id.tv_mgz)
    TextView tvMgz;
    @InjectView(R.id.tv_fragment)
    TextView tvFragment;
    @InjectView(R.id.iv_shop)
    ImageView ivShop;
    @InjectView(R.id.iv_daren)
    ImageView ivDaren;
    @InjectView(R.id.iv_back)
    ImageView ivBack;
    @InjectView(R.id.tab_layout)
    TabLayout tabLayout;
    @InjectView(R.id.view_pager)
    ViewPager viewPager;
    private CreamAdapter pagerAdapter;

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment_cream, null);
        ButterKnife.inject(this,view);
        tvFragment.setText("精华");
        return view;

    }

    @Override
    public void initData() {
        super.initData();
        pagerAdapter = new CreamAdapter(getFragmentManager());
        viewPager.setAdapter(pagerAdapter);

        tabLayout.setupWithViewPager(viewPager);
        //如果有多个ViewPager页面
//        tabLayout.setTabMode(TableLayout.Mo);
//        tabLayout.setTabMode(TableLayout.MODE_SCROLLABLE);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

}
