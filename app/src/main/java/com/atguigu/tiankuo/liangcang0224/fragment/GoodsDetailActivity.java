package com.atguigu.tiankuo.liangcang0224.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.atguigu.tiankuo.liangcang0224.R;
import com.atguigu.tiankuo.liangcang0224.RegisterActivity;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.ButterKnife;
import butterknife.InjectView;
import cn.sharesdk.onekeyshare.OnekeyShare;
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
    TextView rbShopingGoodsdetails;
    @InjectView(R.id.rb_shoping_shopknow)
    TextView rbShopingShopknow;
    @InjectView(R.id.activity_goods_detail)
    RelativeLayout activityGoodsDetail;
    private String url;
    private String goodsid;
    private ShopKnowFragment shopKnowFragment;
    private GoodsDetailsFragment goodsdetailsFragment;
    private GoodDetailsBean.DataBean.ItemsBean datas;
    private PopupWindow popupWindow;

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

        rbShopingGoodsdetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GoodsDetailActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        ivShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showShare();
            }
        });
    }

    private void showShare() {
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();
        // title标题，印象笔记、邮箱、信息、微信、人人网、QQ和QQ空间使用
        oks.setTitle("标题");
        // titleUrl是标题的网络链接，仅在Linked-in,QQ和QQ空间使用
        oks.setTitleUrl("http://sharesdk.cn");
        // text是分享文本，所有平台都需要这个字段
        oks.setText("我是分享文本");
        //分享网络图片，新浪微博分享网络图片需要通过审核后申请高级写入接口，否则请注释掉测试新浪微博
        oks.setImageUrl("http://f1.sharesdk.cn/imgs/2014/02/26/owWpLZo_638x960.jpg");
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        //oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl("http://sharesdk.cn");
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("我是测试评论文本");
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite("ShareSDK");
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl("http://sharesdk.cn");

// 启动分享GUI
        oks.show(this);
    }

//    private void showPopupWindow(View v) {
//        View contentView = LayoutInflater.from(GoodsDetailActivity.this).inflate(
//                R.layout.goods_popup_view, null);
//        //设置按钮的点击事件
//        TextView tvsharefriend = (TextView) contentView.findViewById(R.id.tv_share_friend);
//        TextView tvshareweixin = (TextView) contentView.findViewById(R.id.tv_share_weixin);
//        TextView tvsharexinlang = (TextView) contentView.findViewById(R.id.tv_share_xinlang);
//        TextView tvsharetencent = (TextView) contentView.findViewById(R.id.tv_share_tencent);
//        TextView tvshareabolish = (TextView) contentView.findViewById(R.id.tv_share_abolish);
//        Button btn = (Button) contentView.findViewById(R.id.btn_background);
//
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                popupWindow.dismiss();
//            }
//        });
//
//        tvshareabolish.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                popupWindow.dismiss();
//            }
//        });
//
//        tvsharefriend.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
//
//        tvsharetencent.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
//
//        tvshareweixin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
//
//        tvsharexinlang.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
//    }

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
                    bundle.putString("goodsid", goodsid);//这里的values就是我们要传的值
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
                    bundle.putString("goodsid", goodsid);//这里的values就是我们要传的值
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
