package com.atguigu.tiankuo.liangcang0224.fragment;

import android.view.View;

import com.atguigu.tiankuo.liangcang0224.R;
import com.atguigu.tiankuo.liangcang0224.base.BaseFragment;

/**
 * 作者：田阔
 * 邮箱：1226147264@qq.com
 * Created by Administrator on 2017/7/18 0018.
 */
public class GoodsDetailsFragment extends BaseFragment {


    private String goodsid;

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment_details_goods, null);
//            ButterKnife.inject(this,view);
//
//        Bundle bundle = this.getArguments();
//        if (bundle != null) {
//            goodsid = bundle.getString("goodsid");
//            Log.e("TAG", "userid" + goodsid);
//        }

        return view;
    }
}
