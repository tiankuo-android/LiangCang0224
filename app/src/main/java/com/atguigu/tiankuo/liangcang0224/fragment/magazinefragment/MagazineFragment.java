package com.atguigu.tiankuo.liangcang0224.fragment.magazinefragment;

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
 * Created by Administrator on 2017/7/7 0007.
 */

public class MagazineFragment extends BaseFragment {
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

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment_magazine, null);
        ButterKnife.inject(this, view);
        ivSearch.setVisibility(View.VISIBLE);
        tvFragment.setText("杂志");
        return view;

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
