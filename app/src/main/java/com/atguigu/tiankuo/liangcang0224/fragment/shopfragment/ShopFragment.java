package com.atguigu.tiankuo.liangcang0224.fragment.shopfragment;

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

public class ShopFragment extends BaseFragment {
    @InjectView(R.id.iv_search)
    ImageView ivSearch;
    @InjectView(R.id.tv_fragment)
    TextView tvFragment;
    @InjectView(R.id.iv_shop)
    ImageView ivShop;
    @InjectView(R.id.tab_layout)
    TabLayout tabLayout;
    @InjectView(R.id.view_pager)
    ViewPager viewPager;
    private ShopAdapter pagerAdapter;

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment_shop, null);
        ButterKnife.inject(this, view);
        ivSearch.setVisibility(View.VISIBLE);
        tvFragment.setText("商店");
        ivShop.setVisibility(View.VISIBLE);
        return view;
    }

    @Override
    public void initData() {
        super.initData();
        pagerAdapter = new ShopAdapter(getFragmentManager());
        viewPager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
