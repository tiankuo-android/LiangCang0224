package com.atguigu.tiankuo.liangcang0224.fragment.shopfragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.atguigu.tiankuo.liangcang0224.R;
import com.atguigu.tiankuo.liangcang0224.fragment.shopfragment.bean.GoodBean;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.loader.ImageLoader;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import okhttp3.Call;

public class GoodsActivity extends AppCompatActivity {

    @InjectView(R.id.good_back)
    ImageView goodBack;
    @InjectView(R.id.good_shop)
    ImageView goodShop;
    @InjectView(R.id.banner)
    Banner banner;
    @InjectView(R.id.activity_goods)
    LinearLayout activityGoods;
    @InjectView(R.id.tv_goods_company)
    TextView tvGoodsCompany;
    @InjectView(R.id.iv_putup)
    ImageView ivPutup;
    @InjectView(R.id.tv_putup)
    TextView tvPutup;
    @InjectView(R.id.tv_goods_product)
    TextView tvGoodsProduct;
    @InjectView(R.id.iv_goods_share)
    ImageView ivGoodsShare;
    private String url;
    private String goodid;
    private List<String> images_item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods);
        ButterKnife.inject(this);

        Intent intent = getIntent();
        goodid = intent.getStringExtra("good_id");

        initData();
        initListener();
    }

    private void initData() {
        url = "http://mobile.iliangcang.com/goods/goodsDetail?app_key=Android&goods_id="
                + goodid + "&sig=CD0E234053E25DD6111E3DBD450A4B85%7C954252010968868&v=1.0";
        getDataFromNet(url);
    }

    private void initListener() {
        goodBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void getDataFromNet(String url) {
        OkHttpUtils.get().url(url).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                Log.e("TAG", "请求失败==Goods");
            }

            @Override
            public void onResponse(String response, int id) {
                Log.e("TAG", "请求成功==Goods");
                processData(response);
            }
        });
    }

    private void processData(String response) {
        GoodBean goodbean = new Gson().fromJson(response, GoodBean.class);

        tvGoodsCompany.setText(goodbean.getData().getItems().getBrand_info().getBrand_name());
        tvGoodsProduct.setText(goodbean.getData().getItems().getGoods_name());

        initBanner(goodbean);
    }

    private void initBanner(GoodBean goodbean) {
        images_item = goodbean.getData().getItems().getImages_item();
        List<String> image = new ArrayList<>();
        for (int i = 0; i < images_item.size(); i++) {
            image.add(images_item.get(i));
        }
        //设置banner样式
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        banner.setImages(image);
        //设置banner动画效果
        banner.setBannerAnimation(Transformer.DepthPage);
        //设置自动轮播，默认为true
        banner.isAutoPlay(true);
        //设置轮播时间
        banner.setDelayTime(1500);
        //设置指示器位置（当banner模式中有指示器时）
        banner.setIndicatorGravity(BannerConfig.CENTER);
        //设置banner样式
        //banner设置方法全部调用完毕时最后调用
        banner.start();
    }

    public class GlideImageLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(context).load((String) path).into(imageView);
        }
    }
}
