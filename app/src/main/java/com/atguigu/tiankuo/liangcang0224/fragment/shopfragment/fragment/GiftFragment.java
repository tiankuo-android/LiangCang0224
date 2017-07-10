package com.atguigu.tiankuo.liangcang0224.fragment.shopfragment.fragment;

import android.view.View;
import android.widget.ImageView;

import com.atguigu.tiankuo.liangcang0224.R;
import com.atguigu.tiankuo.liangcang0224.base.BaseFragment;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * 作者：田阔
 * 邮箱：1226147264@qq.com
 * Created by Administrator on 2017/7/8 0008.
 */

public class GiftFragment extends BaseFragment {

    @InjectView(R.id.iv_present_7)
    ImageView ivPresent7;
    @InjectView(R.id.iv_present_1)
    ImageView ivPresent1;
    @InjectView(R.id.iv_present_2)
    ImageView ivPresent2;
    @InjectView(R.id.iv_present_3)
    ImageView ivPresent3;
    @InjectView(R.id.iv_present_4)
    ImageView ivPresent4;
    @InjectView(R.id.iv_present_5)
    ImageView ivPresent5;
    @InjectView(R.id.iv_present_6)
    ImageView ivPresent6;

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment_shop_gift, null);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void initData() {
        super.initData();
    }

    @Override
    public void initListener() {
        super.initListener();
        ivPresent1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
