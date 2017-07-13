package com.atguigu.tiankuo.liangcang0224.fragment.shopfragment.fragment.brandfragment;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import com.atguigu.tiankuo.liangcang0224.R;
import com.atguigu.tiankuo.liangcang0224.base.BaseFragment;
import com.atguigu.tiankuo.liangcang0224.fragment.shopfragment.fragment.brandfragment.adapter.BrandGoodsAdapter;
import com.atguigu.tiankuo.liangcang0224.fragment.shopfragment.fragment.brandfragment.bean.BrandProductBean;
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
 * Created by Administrator on 2017/7/11 0011.
 */
public class GoodsFragment extends BaseFragment {
    @InjectView(R.id.recyclerview)
    RecyclerView recyclerview;
    private String brand;
    private String url;
    private List<BrandProductBean.DataBean.ItemsBean> datas;

    @Override
    public View initView() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.fragment_shop_brand_goods, null);
        ButterKnife.inject(this, view);
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            brand = bundle.getString("brand");
            Log.e("TAG", "brand==" + brand);
        }
        return view;
    }

    @Override
    public void initData() {
        super.initData();
        url = "http://mobile.iliangcang.com/brand/brandShopList?app_key=Android&brand_id="
                + brand +
                "&count=20&page=1&sig=430BD99E6C913B8B8C3ED109737ECF15%7C830952120106768&v=1.0";
        getDataFromNet(url);
    }

    private void getDataFromNet(String url) {
        OkHttpUtils.get().url(url).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                Log.e("TAG", "请求失败==Story");
            }

            @Override
            public void onResponse(String response, int id) {
                Log.e("TAG", "请求成功==Story");
                processData(response);
            }
        });
    }

    private void processData(String response) {
        BrandProductBean brandProductBean = new Gson().fromJson(response,BrandProductBean.class);
        datas = brandProductBean.getData().getItems();
        
        if(datas != null && datas.size() > 0) {
            BrandGoodsAdapter adapter = new BrandGoodsAdapter(mContext,datas);
            recyclerview.setAdapter(adapter);
            recyclerview.setLayoutManager(new GridLayoutManager(mContext,2,GridLayoutManager.VERTICAL,false));
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
