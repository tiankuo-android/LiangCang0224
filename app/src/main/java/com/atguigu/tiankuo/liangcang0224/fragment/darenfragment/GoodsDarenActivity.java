package com.atguigu.tiankuo.liangcang0224.fragment.darenfragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.atguigu.tiankuo.liangcang0224.R;
import com.bumptech.glide.Glide;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.ButterKnife;
import butterknife.InjectView;
import okhttp3.Call;

public class GoodsDarenActivity extends AppCompatActivity {

    @InjectView(R.id.iv_goods_daren)
    ImageView ivGoodsDaren;
    @InjectView(R.id.tv_goods_daren_name)
    TextView tvGoodsDarenName;
    @InjectView(R.id.iv_goods_daren_dianzan)
    ImageView ivGoodsDarenDianzan;
    @InjectView(R.id.tv_goods_daren_price)
    TextView tvGoodsDarenPrice;
    @InjectView(R.id.tv_goods_daren_dianzan)
    TextView tvGoodsDarenDianzan;
    @InjectView(R.id.iv_goods_daren_author)
    ImageView ivGoodsDarenAuthor;
    @InjectView(R.id.tv_goods_daren_author)
    TextView tvGoodsDarenAuthor;
    @InjectView(R.id.activity_goods_daren)
    ScrollView activityGoodsDaren;
    private String url;
    private String goodid;
    private String goodsimage;
    private String goodsname;
    private String prices;
    private String likecount;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_daren);
        ButterKnife.inject(this);

        Intent intent = getIntent();
        goodid = intent.getStringExtra("goodid");
        goodsimage = intent.getStringExtra("goodsimage");
        goodsname = intent.getStringExtra("goodsname");
        prices = intent.getStringExtra("price");
        likecount = intent.getStringExtra("likecount");
        initData();
        initListener();
    }

    private void initData() {
        url = "http://mobile.iliangcang.com/comments/goods?app_key=Android&count=3&goods_id="
                + goodid +
                "&page=1&sig=CD0E234053E25DD6111E3DBD450A4B85%7C954252010968868&uid=305309276&user_token=e8fff51ce18d54cbf817cbcfd162cee5&v=1.0";
        getDataFromNet(url);
    }

    private void getDataFromNet(String url) {
        OkHttpUtils.get().url(url).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                Log.e("TAG", "联网失败==goodsdaren");
            }

            @Override
            public void onResponse(String response, int id) {
                Log.e("TAG", "联网成功==goodsdaren");
                processData(response);
            }
        });
    }

    private void processData(String response) {

        Glide.with(this).load(goodsimage).into(ivGoodsDaren);

        tvGoodsDarenName.setText(goodsname);

        String price = getResources().getString(R.string.goods_daren);
        String darenprice = String.format(price, prices);
        tvGoodsDarenPrice.setText(darenprice);

        tvGoodsDarenDianzan.setText(likecount);

//        Glide.with(this).load(datadaren.getHeadimg()).into(ivGoodsDarenAuthor);
//        tvGoodsDarenAuthor.setText(datadaren.getOwner_name());

//        datasdaren = bean.getData().getItems().getLiked();
//        adapter = new GoodsDarenAdapter(GoodsDarenActivity.this,datadaren);
//        recyclerview.setAdapter(adapter);

    }

    private void initListener() {

        ivGoodsDarenAuthor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GoodsDarenActivity.this, DetailsDarenActivity.class);
                startActivity(intent);
            }
        });
    }
}
