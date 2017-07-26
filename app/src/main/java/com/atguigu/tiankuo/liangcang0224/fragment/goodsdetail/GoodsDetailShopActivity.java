package com.atguigu.tiankuo.liangcang0224.fragment.goodsdetail;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.atguigu.tiankuo.liangcang0224.R;
import com.atguigu.tiankuo.liangcang0224.shopcart.ShoppingCartActivity;
import com.atguigu.tiankuo.liangcang0224.utils.CartStorage;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.ButterKnife;
import butterknife.InjectView;
import okhttp3.Call;

public class GoodsDetailShopActivity extends AppCompatActivity {


    @InjectView(R.id.iv_goods)
    ImageView ivGoods;
    @InjectView(R.id.iv_back)
    ImageView ivBack;
    @InjectView(R.id.tv_goods_product)
    TextView tvGoodsProduct;
    @InjectView(R.id.tv_goods_content)
    TextView tvGoodsContent;
    @InjectView(R.id.tv_goods_details_price)
    TextView tvGoodsDetailsPrice;
    @InjectView(R.id.tv_goods_monad)
    TextView tvGoodsMonad;
    @InjectView(R.id.iv_sub)
    ImageView ivSub;
    @InjectView(R.id.tv_value)
    TextView tvValue;
    @InjectView(R.id.iv_add)
    ImageView ivAdd;
    @InjectView(R.id.rb_shoping_sure)
    TextView rbShopingSure;
    @InjectView(R.id.activity_goods_detail_shop)
    LinearLayout activityGoodsDetailShop;
    private String goodsid;
    private String url;
    private GoodDetailsBean.DataBean.ItemsBean datas;
    private int values = 1;
    private int minvalues = 1;
    private int maxvalues = 10;
    private GoodDetailsBean bean;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_detail_shop);
        ButterKnife.inject(this);

        Intent intent = getIntent();
        goodsid = intent.getStringExtra("goodsid");

        initData();
        initListener();
    }

    private void initListener() {
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        ivAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (values < maxvalues) {
                    values++;
                }
                tvValue.setText(values + "");
            }
        });

        ivSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (values > minvalues) {
                    values--;
                }
                tvValue.setText(values + "");
            }
        });

        rbShopingSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GoodsDetailShopActivity.this,ShoppingCartActivity.class);
                CartStorage.getInstance(GoodsDetailShopActivity.this).addData(bean);
                startActivity(intent);
            }
        });
    }

    private void initData() {
        url = "http://mobile.iliangcang.com/goods/goodsDetail?app_key=Android&goods_id="
                + goodsid + "&sig=CD0E234053E25DD6111E3DBD450A4B85%7C954252010968868&v=1.0";
        getDataFromNet(url);
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
        bean = new Gson().fromJson(response, GoodDetailsBean.class);
        datas = bean.getData().getItems();

        Glide.with(this).load(datas.getGoods_image()).into(ivGoods);

        tvGoodsProduct.setText(datas.getBrand_info().getBrand_name());
        tvGoodsContent.setText(datas.getGoods_name());
        tvGoodsMonad.setText(datas.getSku_info().get(0).getAttrList().get(0).getAttr_name());

        String privce = getResources().getString(R.string.goods_details_price);
        String price = String.format(privce, datas.getPrice());
        tvGoodsDetailsPrice.setText(price);

        tvValue.setText(values + "");
    }
}
