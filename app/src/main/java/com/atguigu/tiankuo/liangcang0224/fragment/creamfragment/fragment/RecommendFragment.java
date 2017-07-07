package com.atguigu.tiankuo.liangcang0224.fragment.creamfragment.fragment;

import android.view.View;
import android.widget.TextView;

import com.atguigu.tiankuo.liangcang0224.base.BaseFragment;

/**
 * 作者：田阔
 * 邮箱：1226147264@qq.com
 * Created by Administrator on 2017/7/7 0007.
 */

public class RecommendFragment extends BaseFragment {
    @Override
    public View initView() {
        TextView textView = new TextView(mContext);
        textView.setText("精华");
        return textView;
    }
}
