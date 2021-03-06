package com.atguigu.tiankuo.liangcang0224.fragment.shopfragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.atguigu.tiankuo.liangcang0224.R;
import com.atguigu.tiankuo.liangcang0224.fragment.shopfragment.adapter.StoreAdapter;
import com.atguigu.tiankuo.liangcang0224.fragment.shopfragment.bean.StoreBean;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import okhttp3.Call;

public class StoreActivity extends AppCompatActivity {

    @InjectView(R.id.recyclerview)
    RecyclerView recyclerview;
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
    @InjectView(R.id.activity_store)
    LinearLayout activityStore;
    private String catid;
    private String url;
    private List<StoreBean.DataBean.ItemsBean> datas;
    private StoreAdapter adapter;

    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);
        ButterKnife.inject(this);

        ivDarenBack.setVisibility(View.VISIBLE);
        tvFragment.setText("商店");

        Intent intent = getIntent();
        catid = intent.getStringExtra("classify");
        Log.e("TAG", "传入数据store==" + catid);
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
        ivShop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    private void initData() {

        id = Integer.parseInt(catid);
        if (id < 100) {
            url = "http://mobile.iliangcang.com/goods/goodsShare?app_key=Android&cat_code=00"
                    + catid + "&count=10&coverId=1&page=1&sig=6E1DEF1DAFF84909ECD98F32FE6CDAD5%7C536890620070968&v=1.0";
        }else{
            url = "http://mobile.iliangcang.com/goods/goodsShare?app_key=Android&cat_code=0"
                    + catid + "&count=10&coverId=1&page=1&sig=6E1DEF1DAFF84909ECD98F32FE6CDAD5%7C536890620070968&v=1.0";
        }
        getDataFromNet(url);
    }

    private void getDataFromNet(String url) {
        OkHttpUtils.get().url(url).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                Log.e("TAG", "联网失败==store");
            }

            @Override
            public void onResponse(String response, int id) {
                Log.e("TAG", "联网成功==store");
                processData(response);
            }
        });
    }

    private void processData(String response) {
        StoreBean storeBean = new Gson().fromJson(response, StoreBean.class);
        datas = storeBean.getData().getItems();

        if (datas != null && datas.size() > 0) {
            adapter = new StoreAdapter(StoreActivity.this, datas);
            recyclerview.setAdapter(adapter);
            recyclerview.setLayoutManager(new GridLayoutManager(StoreActivity.this, 2, GridLayoutManager.VERTICAL, false));
        } else {
            Log.e("TAG", "没有获得数据==store");
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.reset(this);
    }
}
