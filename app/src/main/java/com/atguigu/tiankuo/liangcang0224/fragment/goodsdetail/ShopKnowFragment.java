package com.atguigu.tiankuo.liangcang0224.fragment.goodsdetail;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.atguigu.tiankuo.liangcang0224.R;
import com.atguigu.tiankuo.liangcang0224.base.BaseFragment;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.ButterKnife;
import butterknife.InjectView;
import okhttp3.Call;

/**
 * 作者：田阔
 * 邮箱：1226147264@qq.com
 * Created by Administrator on 2017/7/18 0018.
 */

public class ShopKnowFragment extends BaseFragment {
    @InjectView(R.id.tv_goods_content)
    TextView tvGoodsContent;
    @InjectView(R.id.btn_know)
    Button btnKnow;
    private String url;
    private String goodsid;
    private GoodDetailsBean.DataBean.ItemsBean datas;

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment_know_shop, null);
        ButterKnife.inject(this, view);

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            goodsid = bundle.getString("goodsid");
            Log.e("TAG", "userid" + goodsid);
        }

        return view;
    }

    @Override
    public void initData() {
        super.initData();
        url = "http://mobile.iliangcang.com/goods/goodsDetail?app_key=Android&goods_id="
                + goodsid + "&sig=CD0E234053E25DD6111E3DBD450A4B85%7C954252010968868&v=1.0";
        getDataFromNet(url);
    }

    private void getDataFromNet(String url) {
        OkHttpUtils.get().url(url).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                Log.e("TAG", "请求失败==GoodsDetails");
            }

            @Override
            public void onResponse(String response, int id) {
                Log.e("TAG", "请求成功==GoodsDetails");
                processData(response);
            }
        });
    }

    private void processData(String response) {
        GoodDetailsBean bean = new Gson().fromJson(response, GoodDetailsBean.class);
        datas = bean.getData().getItems();

        tvGoodsContent.setText(datas.getGood_guide().getContent());
    }

    @Override
    public void initListener() {
        super.initListener();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
