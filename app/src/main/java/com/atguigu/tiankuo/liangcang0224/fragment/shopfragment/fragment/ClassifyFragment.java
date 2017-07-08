package com.atguigu.tiankuo.liangcang0224.fragment.shopfragment.fragment;

import android.view.View;
import android.widget.TextView;

import com.atguigu.tiankuo.liangcang0224.base.BaseFragment;

/**
 * 作者：田阔
 * 邮箱：1226147264@qq.com
 * Created by Administrator on 2017/7/8 0008.
 */

public class ClassifyFragment extends BaseFragment {
    @Override
    public View initView() {
        TextView textView = new TextView(mContext);
        textView.setText("分类");
        return textView;
    }
}
