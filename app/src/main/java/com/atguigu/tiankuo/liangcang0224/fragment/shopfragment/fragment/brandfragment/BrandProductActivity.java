package com.atguigu.tiankuo.liangcang0224.fragment.shopfragment.fragment.brandfragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.atguigu.tiankuo.liangcang0224.R;
import com.atguigu.tiankuo.liangcang0224.fragment.shopfragment.bean.BrandBean;
import com.atguigu.tiankuo.liangcang0224.utils.UrlUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import okhttp3.Call;

public class BrandProductActivity extends AppCompatActivity {
    @InjectView(R.id.iv_search)
    ImageView ivSearch;
    @InjectView(R.id.iv_daren_back)
    ImageView ivDarenBack;
    @InjectView(R.id.tv_mgz)
    TextView tvMgz;
    @InjectView(R.id.tv_fragment)
    TextView tvFragment;
    @InjectView(R.id.iv_shop)
    ImageView ivShop;
    @InjectView(R.id.iv_daren)
    ImageView ivDaren;
    @InjectView(R.id.iv_back)
    ImageView ivBack;
    @InjectView(R.id.iv_shop_brand_title)
    ImageView ivShopBrandTitle;
    @InjectView(R.id.tv_shop_brand_title)
    TextView tvShopBrandTitle;
    @InjectView(R.id.rl_shop_brand)
    RelativeLayout rlShopBrand;
    @InjectView(R.id.btn_story)
    RadioButton btnStory;
    @InjectView(R.id.btn_product)
    RadioButton btnProduct;
    @InjectView(R.id.rg_brand)
    RadioGroup rgBrand;
    @InjectView(R.id.frameLayout)
    FrameLayout frameLayout;
    @InjectView(R.id.activity_brand_product)
    LinearLayout activityBrandProduct;
    private String brand;
    private int position;
    private String url;
    private List<BrandBean.DataBean.ItemsBean> datas;
    private StoryFragment storyFragment;
    private GoodsFragment goodsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brand_product);
        ButterKnife.inject(this);

        //取得从上一个Activity当中传递过来的Intent对象
        Intent intent = getIntent();
        //从Intent当中根据key取得value
        brand = intent.getStringExtra("brand");
        position = Integer.parseInt(intent.getStringExtra("position"));

        tvFragment.setText("品牌产品");
        ivDarenBack.setVisibility(View.VISIBLE);
        initData();
        initListener();
    }

    private void initListener() {
        ivDarenBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        
        rgBrand.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switchFragment(checkedId);
            }
        });
        switchFragment(R.id.btn_story);
    }

    private void switchFragment(int checkedId) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        hidden(transaction);
        switch (checkedId) {
            case R.id.btn_story :
                if (storyFragment == null) {
                    storyFragment = new StoryFragment();

                    Bundle bundle = new Bundle();
                    bundle.putString("brand",brand);//这里的values就是我们要传的值
                    storyFragment.setArguments(bundle);

                    transaction.add(R.id.frameLayout, storyFragment);
                } else {
                    transaction.show(storyFragment);
                }
                break;
            case R.id.btn_product :
                if (goodsFragment == null) {
                    goodsFragment = new GoodsFragment();

                    Bundle bundle = new Bundle();
                    bundle.putString("position",position + "");
                    bundle.putString("band",brand);//这里的values就是我们要传的值
                    goodsFragment.setArguments(bundle);

                    transaction.add(R.id.frameLayout, goodsFragment);
                } else {
                    transaction.show(goodsFragment);
                }
                break;
        }
        transaction.commit();
    }

    private void hidden(FragmentTransaction transaction) {
        if (storyFragment != null) {
            transaction.hide(storyFragment);
        }
        if (goodsFragment != null) {
            transaction.hide(goodsFragment);
        }
    }

    private void initData() {
        url = UrlUtils.BREAD_BASE_URL + 1 + UrlUtils.BREAD_LAST_URL;
        getDataFromNet(url);
    }

    private void getDataFromNet(String url) {
        OkHttpUtils.get().url(url).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                Log.e("TAG", "请求失败==Brand");
            }

            @Override
            public void onResponse(String response, int id) {
                Log.e("TAG", "请求成功==Brand");
                processData(response);
            }
        });
    }

    private void processData(String response) {
        BrandBean itemsBean = new Gson().fromJson(response, BrandBean.class);
        datas = itemsBean.getData().getItems();

        Glide.with(BrandProductActivity.this)
                .load(datas.get(position).getBrand_logo())
                .asBitmap()
                .centerCrop()
                .into(new BitmapImageViewTarget(ivShopBrandTitle) {
                    @Override
                    protected void setResource(Bitmap resource) {
                        RoundedBitmapDrawable circularBitmapDrawable =
                                RoundedBitmapDrawableFactory.create(BrandProductActivity.this.getResources(), resource);
                        circularBitmapDrawable.setCircular(true);
                        ivShopBrandTitle.setImageDrawable(circularBitmapDrawable);
                    }
                });

        tvShopBrandTitle.setText(datas.get(position).getBrand_name());
    }

}