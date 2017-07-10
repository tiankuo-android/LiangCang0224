package com.atguigu.tiankuo.liangcang0224.fragment.shopfragment.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.atguigu.tiankuo.liangcang0224.R;
import com.atguigu.tiankuo.liangcang0224.base.BaseFragment;
import com.atguigu.tiankuo.liangcang0224.fragment.shopfragment.adapter.SpecialAdapter;
import com.atguigu.tiankuo.liangcang0224.fragment.shopfragment.bean.SpecialBean;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import okhttp3.Call;

/**
 * 作者：田阔
 * 邮箱：1226147264@qq.com
 * Created by Administrator on 2017/7/8 0008.
 */

public class SpecialFragment extends BaseFragment {
    @InjectView(R.id.recyclerview)
    RecyclerView recyclerview;
    private String url;
    private List<SpecialBean.DataBean.ItemsBean> datas;
    private SpecialAdapter adapter;

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment_shop_special, null);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void initData() {
        super.initData();
        url = "http://mobile.iliangcang.com/goods/shopSpecial?app_key=Android&count=10&page=1&sig=CD0E234053E25DD6111E3DBD450A4B85%7C954252010968868&v=1.0";
        getDataFromNet(url);
    }

    private void getDataFromNet(String url) {
        OkHttpUtils.get().url(url).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                Log.e("TAG","请求失败==Special");
            }

            @Override
            public void onResponse(String response, int id) {
                Log.e("TAG","请求成功==Special");
                processData(response);
            }
        });
    }

    private void processData(String response) {
        SpecialBean itemsBean = new Gson().fromJson(response,SpecialBean.class);
        Log.e("TAG","数据==Special==" + itemsBean);
        datas = itemsBean.getData().getItems();
        if (datas != null && datas.size() > 0) {
            //有数据
            adapter = new SpecialAdapter(mContext, datas);
            recyclerview.setAdapter(adapter);
            recyclerview.setLayoutManager(new LinearLayoutManager(mContext,LinearLayoutManager.VERTICAL,false));
        } else {
            //没有数据
            Log.e("TAG", "没有数据==Special");
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
