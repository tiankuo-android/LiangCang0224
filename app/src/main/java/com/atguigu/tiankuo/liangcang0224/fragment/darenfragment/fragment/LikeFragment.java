package com.atguigu.tiankuo.liangcang0224.fragment.darenfragment.fragment;

import android.view.View;
import android.widget.TextView;

import com.atguigu.tiankuo.liangcang0224.base.BaseFragment;

public class LikeFragment extends BaseFragment {


    @Override
    public View initView() {
//        View view = View.inflate(mContext, R.layout.activity_like_fragment,null);
        TextView view = new TextView(mContext);
        view.setText("喜欢");
        view.setTextSize(100);
        return view;
    }
}