package com.atguigu.tiankuo.liangcang0224.shopcart;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.atguigu.tiankuo.liangcang0224.R;
import com.atguigu.tiankuo.liangcang0224.fragment.GoodDetailsBean;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import okhttp3.Call;

public class ShoppingCartActivity extends AppCompatActivity {

    @InjectView(R.id.tv_date)
    TextView tvDate;
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
    @InjectView(R.id.ll_title)
    LinearLayout llTitle;
    @InjectView(R.id.recyclerview)
    RecyclerView recyclerview;
    @InjectView(R.id.checkbox_all)
    CheckBox checkboxAll;
    @InjectView(R.id.tv_shopcart_total)
    TextView tvShopcartTotal;
    @InjectView(R.id.btn_check_out)
    TextView btnCheckOut;
    @InjectView(R.id.ll_check_all)
    LinearLayout llCheckAll;
    @InjectView(R.id.activity_shopping_cart)
    LinearLayout activityShoppingCart;
    @InjectView(R.id.tv_shopcart)
    TextView tvShopcart;

    //编辑状态
    private static final int ACTION_EDIT = 1;
    //完成状态
    private static final int ACTION_COMPLETE = 2;
    private String goodsid;
    private String url;
    private List<GoodDetailsBean.DataBean.ItemsBean.GoodsInfoBean> datas;
    private ShoppingCartAdapter adapter;
    boolean checked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);
        ButterKnife.inject(this);

        Intent intent = getIntent();
        goodsid = intent.getStringExtra("goodsid");

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

    }

    private void initData() {

        ivDarenBack.setVisibility(View.VISIBLE);
        tvFragment.setText("购物车");
        tvShopcart.setVisibility(View.VISIBLE);

        //设置编辑状态
        tvShopcart.setTag(ACTION_EDIT);
        tvShopcart.setText("编辑");
        //显示去结算布局
        llCheckAll.setVisibility(View.VISIBLE);

        tvShopcart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //1.得到状态
                int action = (int) v.getTag();
                //2.根据不同状态做不同的处理
                if (action == ACTION_EDIT) {
                    //切换完成状态
                    showDelete();
                } else {
                    //切换成编辑状态
                    hideDelete();
                }
            }
        });





        url = "http://mobile.iliangcang.com/goods/goodsDetail?app_key=Android&goods_id="
                + goodsid + "&sig=CD0E234053E25DD6111E3DBD450A4B85%7C954252010968868&v=1.0";
        getDataFromNet(url);
    }

    private void hideDelete() {
        tvShopcart.setTag(ACTION_EDIT);
        tvShopcart.setText("编辑");


    }

    private void showDelete() {

        tvShopcart.setTag(ACTION_COMPLETE);
        tvShopcart.setText("完成");

        //把所有的数据设置非选择状态

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
        datas = bean.getData().getItems().getGoods_info();

        if(datas != null && datas.size() > 0) {
            adapter = new ShoppingCartAdapter(this,datas);
            recyclerview.setAdapter(adapter);

        }
    }
}
