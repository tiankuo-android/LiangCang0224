package com.atguigu.tiankuo.liangcang0224.fragment.userFragment;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.atguigu.tiankuo.liangcang0224.R;
import com.atguigu.tiankuo.liangcang0224.base.BaseFragment;
import com.atguigu.tiankuo.liangcang0224.fragment.userFragment.fragment.DiscoverFragment;
import com.atguigu.tiankuo.liangcang0224.fragment.userFragment.fragment.DynamicFragment;
import com.atguigu.tiankuo.liangcang0224.fragment.userFragment.fragment.RecommedFragment;
import com.atguigu.tiankuo.liangcang0224.fragment.userFragment.fragment.ZhiBoFragment;
import com.atguigu.tiankuo.liangcang0224.fragment.userFragment.fragment.ZhuiFanFragment;
import com.atguigu.tiankuo.liangcang0224.fragment.userFragment.fragment.ZoneFragment;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * 作者：田阔
 * 邮箱：1226147264@qq.com
 * Created by Administrator on 2017/7/5 0005.
 */

public class UserFragment extends BaseFragment {
    @InjectView(R.id.iv_play)
    ImageView ivPlay;
    @InjectView(R.id.iv_loadingdown)
    ImageView ivLoadingdown;
    @InjectView(R.id.iv_search)
    ImageView ivSearch;
    @InjectView(R.id.rb_zhibo)
    RadioButton rbZhibo;
    @InjectView(R.id.rb_recommend)
    RadioButton rbRecommend;
    @InjectView(R.id.rb_zhuifan)
    RadioButton rbZhuifan;
    @InjectView(R.id.rb_zone)
    RadioButton rbZone;
    @InjectView(R.id.rb_dynamic)
    RadioButton rbDynamic;
    @InjectView(R.id.rb_discover)
    RadioButton rbDiscover;
    @InjectView(R.id.rg_bilibili)
    RadioGroup rgBilibili;
    @InjectView(R.id.fl)
    FrameLayout fl;
    private ZhiBoFragment zhiboFragment;
    private RecommedFragment recommedfragment;
    private ZhuiFanFragment zhuifanfragment;
    private ZoneFragment zonefragment;
    private DynamicFragment dynamicfragment;
    private DiscoverFragment discoverfragment;

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment_user, null);
        ButterKnife.inject(this, view);
        return view;

    }

    @Override
    public void initListener() {
        super.initListener();
        rgBilibili.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switchFragment(checkedId);
            }
        });
        switchFragment(R.id.rb_recommend);

        ivPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }

    private void switchFragment(int checkedId) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        hidden(transaction);

        switch (checkedId) {
            case R.id.rb_zhibo:
                if (zhiboFragment == null) {
                    zhiboFragment = new ZhiBoFragment();
                    transaction.add(R.id.fl, zhiboFragment);
                } else {
                    transaction.show(zhiboFragment);
                }
                break;
            case R.id.rb_recommend:
                if (recommedfragment == null) {
                    recommedfragment = new RecommedFragment();
                    transaction.add(R.id.fl, recommedfragment);
                } else {
                    transaction.show(recommedfragment);
                }
                break;
            case R.id.rb_zhuifan:
                if (zhuifanfragment == null) {
                    zhuifanfragment = new ZhuiFanFragment();
                    transaction.add(R.id.fl, zhuifanfragment);
                } else {
                    transaction.show(zhuifanfragment);
                }
                break;
            case R.id.rb_zone:
                if (zonefragment == null) {
                    zonefragment = new ZoneFragment();
                    transaction.add(R.id.fl, zonefragment);
                } else {
                    transaction.show(zonefragment);
                }
                break;
            case R.id.rb_dynamic:
                if (dynamicfragment == null) {
                    dynamicfragment = new DynamicFragment();
                    transaction.add(R.id.fl, dynamicfragment);
                } else {
                    transaction.show(dynamicfragment);
                }
                break;
            case R.id.rb_discover:
                if (discoverfragment == null) {
                    discoverfragment = new DiscoverFragment();
                    transaction.add(R.id.fl, discoverfragment);
                } else {
                    transaction.show(discoverfragment);
                }
                break;
        }
        transaction.commit();
    }

    private void hidden(FragmentTransaction transaction) {
        if (zhiboFragment != null) {
            transaction.hide(zhiboFragment);
        }
        if (recommedfragment != null) {
            transaction.hide(recommedfragment);
        }
        if (zhuifanfragment != null) {
            transaction.hide(zhuifanfragment);
        }
        if (zonefragment != null) {
            transaction.hide(zonefragment);
        }
        if (dynamicfragment != null) {
            transaction.hide(dynamicfragment);
        }
        if (discoverfragment != null) {
            transaction.hide(discoverfragment);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.inject(this, rootView);
        return rootView;
    }
}
