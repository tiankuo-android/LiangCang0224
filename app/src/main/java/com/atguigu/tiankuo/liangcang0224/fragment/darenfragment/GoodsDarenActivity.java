package com.atguigu.tiankuo.liangcang0224.fragment.darenfragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.atguigu.tiankuo.liangcang0224.R;
import com.atguigu.tiankuo.liangcang0224.fragment.darenfragment.bean.GoodsDarenBean;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
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
    @InjectView(R.id.iv_goods_daren_first)
    ImageView ivGoodsDarenFirst;
    @InjectView(R.id.tv_goods_daren_first_name)
    TextView tvGoodsDarenFirstName;
    @InjectView(R.id.tv_goods_daren_first_content)
    TextView tvGoodsDarenFirstContent;
    @InjectView(R.id.tv_goods_daren_first_date)
    TextView tvGoodsDarenFirstDate;
    @InjectView(R.id.tv_goods_daren_first_huifu)
    TextView tvGoodsDarenFirstHuifu;
    @InjectView(R.id.iv_goods_daren_second)
    ImageView ivGoodsDarenSecond;
    @InjectView(R.id.tv_goods_daren_second_name)
    TextView tvGoodsDarenSecondName;
    @InjectView(R.id.tv_goods_daren_second_content)
    TextView tvGoodsDarenSecondContent;
    @InjectView(R.id.tv_goods_daren_second_date)
    TextView tvGoodsDarenSecondDate;
    @InjectView(R.id.tv_goods_daren_second_huifu)
    TextView tvGoodsDarenSecondHuifu;
    @InjectView(R.id.activity_goods_daren)
    LinearLayout activityGoodsDaren;
    private String url;
    private GoodsDarenBean.DataBean.ItemsBean datas;
    private String goodid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_daren);
        ButterKnife.inject(this);

        Intent intent = getIntent();
        goodid = intent.getStringExtra("goodid");
        initData();
        initListener();
    }

    private void initData() {
        url = "http://mobile.iliangcang.com/goods/goodsDetail?app_key=Android&goods_id="
                + goodid +
                "&sig=CD0E234053E25DD6111E3DBD450A4B85%7C954252010968868&v=1.0";
        getDataFromNet(url);
    }

    private void getDataFromNet(String url) {
        OkHttpUtils.get().url(url).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                Log.e("TAG","联网失败==goodsdaren");
            }

            @Override
            public void onResponse(String response, int id) {
                Log.e("TAG","联网成功==goodsdaren");
                processData(response);
            }
        });
    }

    private void processData(String response) {
        GoodsDarenBean bean = new Gson().fromJson(response,GoodsDarenBean.class);
        datas = bean.getData().getItems();

        Glide.with(this).load(datas.getGoods_image()).into(ivGoodsDaren);

        tvGoodsDarenName.setText(datas.getGoods_name());

        tvGoodsDarenPrice.setText(datas.getPrice());
        tvGoodsDarenDianzan.setText(datas.getLike_count());

        Glide.with(this).load(datas.getHeadimg()).into(ivGoodsDarenAuthor);
        tvGoodsDarenAuthor.setText(datas.getOwner_name());

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
