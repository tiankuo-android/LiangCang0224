package com.atguigu.tiankuo.liangcang0224.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.atguigu.tiankuo.liangcang0224.R;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.ButterKnife;
import butterknife.InjectView;
import okhttp3.Call;

public class GoodsDetailActivity extends AppCompatActivity {

    @InjectView(R.id.iv_goods_daren)
    ImageView ivGoodsDaren;
    @InjectView(R.id.tv_goods_name)
    TextView tvGoodsName;
    @InjectView(R.id.iv_share)
    ImageView ivShare;
    @InjectView(R.id.tv_goods_daren_name)
    TextView tvGoodsDarenName;
    @InjectView(R.id.iv_goods_daren_dianzan)
    ImageView ivGoodsDarenDianzan;
    @InjectView(R.id.tv_goods_daren_dianzan)
    TextView tvGoodsDarenDianzan;
    @InjectView(R.id.tv_goods_details_price)
    TextView tvGoodsDetailsPrice;
    @InjectView(R.id.rb_goodsdetails)
    RadioButton rbGoodsdetails;
    @InjectView(R.id.rb_shopknow)
    RadioButton rbShopknow;
    @InjectView(R.id.rg_goods)
    RadioGroup rgGoods;
    @InjectView(R.id.frameLayout)
    FrameLayout frameLayout;
    @InjectView(R.id.iv_back)
    ImageView ivBack;
    @InjectView(R.id.iv_shop)
    ImageView ivShop;
    @InjectView(R.id.rb_shoping_goodsdetails)
    RadioButton rbShopingGoodsdetails;
    @InjectView(R.id.rb_shoping_shopknow)
    RadioButton rbShopingShopknow;
    @InjectView(R.id.rg_shoping_goods)
    RadioGroup rgShopingGoods;
    @InjectView(R.id.activity_goods_detail)
    RelativeLayout activityGoodsDetail;
    private String url;
    private String goodsid;
    private ShopKnowFragment shopKnowFragment;
    private GoodsDetailsFragment goodsdetailsFragment;
    private GoodDetailsBean.DataBean.ItemsBean datas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_detail);
        ButterKnife.inject(this);

        Intent intent = getIntent();
        goodsid = intent.getStringExtra("goodsid");

        initData();
        initListener();
    }

    private void initListener() {
        rgGoods.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switchFragment(checkedId);
            }
        });
        switchFragment(R.id.rb_goodsdetails);
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
        GoodDetailsBean bean = new Gson().fromJson(response, GoodDetailsBean.class);
        datas = bean.getData().getItems();

        Glide.with(this).load(datas.getGoods_image()).into(ivGoodsDaren);

        tvGoodsName.setText(datas.getBrand_info().getBrand_name());
        tvGoodsDarenName.setText(datas.getGoods_name());

        String privce = getResources().getString(R.string.goods_details_price);
        String price = String.format(privce, datas.getPrice());
        tvGoodsDetailsPrice.setText(price);


        tvGoodsDarenDianzan.setText(datas.getLike_count());

    }


    private void switchFragment(int checkedId) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        hidden(transaction);

        switch (checkedId) {
            case R.id.rb_goodsdetails:
                if (goodsdetailsFragment == null) {
                    goodsdetailsFragment = new GoodsDetailsFragment();

                    Bundle bundle = new Bundle();
                    bundle.putString("goodsid",goodsid);//这里的values就是我们要传的值
                    goodsdetailsFragment.setArguments(bundle);

                    transaction.add(R.id.frameLayout, goodsdetailsFragment);
                } else {
                    transaction.show(goodsdetailsFragment);
                }
                break;
            case R.id.rb_shopknow:
                if (shopKnowFragment == null) {
                    shopKnowFragment = new ShopKnowFragment();

                    Bundle bundle = new Bundle();
                    bundle.putString("goodsid",goodsid);//这里的values就是我们要传的值
                    shopKnowFragment.setArguments(bundle);

                    transaction.add(R.id.frameLayout, shopKnowFragment);
                } else {
                    transaction.show(shopKnowFragment);
                }
                break;
        }
        transaction.commit();
    }

    private void hidden(FragmentTransaction transaction) {
        if (shopKnowFragment != null) {
            transaction.hide(shopKnowFragment);
        }
        if (goodsdetailsFragment != null) {
            transaction.hide(goodsdetailsFragment);
        }
    }
}
