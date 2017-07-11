package com.atguigu.tiankuo.liangcang0224.fragment.darenfragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.atguigu.tiankuo.liangcang0224.R;
import com.atguigu.tiankuo.liangcang0224.fragment.darenfragment.bean.DetailsDarenBean;
import com.atguigu.tiankuo.liangcang0224.fragment.darenfragment.fragment.AttentionFragment;
import com.atguigu.tiankuo.liangcang0224.fragment.darenfragment.fragment.FansFragment;
import com.atguigu.tiankuo.liangcang0224.fragment.darenfragment.fragment.LikeFragment;
import com.atguigu.tiankuo.liangcang0224.fragment.darenfragment.fragment.RecomFragment;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.xutils.x;

import butterknife.ButterKnife;
import butterknife.InjectView;
import okhttp3.Call;

public class DetailsDarenActivity extends AppCompatActivity {

    @InjectView(R.id.iv_search)
    ImageView ivSearch;
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
    @InjectView(R.id.frameLayout)
    FrameLayout frameLayout;
    @InjectView(R.id.activity_details_daren)
    LinearLayout activityDetailsDaren;
    @InjectView(R.id.iv_daren_back)
    ImageView ivDarenBack;
    @InjectView(R.id.btn_like)
    RadioButton btnLike;
    @InjectView(R.id.btn_recommend)
    RadioButton btnRecommend;
    @InjectView(R.id.btn_attention)
    RadioButton btnAttention;
    @InjectView(R.id.btn_fans)
    RadioButton btnFans;
    @InjectView(R.id.iv_daren_head)
    ImageView ivDarenHead;
    @InjectView(R.id.tv_daren_name)
    TextView tvDarenName;
    @InjectView(R.id.tv_daren_duty)
    TextView tvDarenDuty;
    @InjectView(R.id.rg_daren)
    RadioGroup rgDaren;
    private String url;
    private String user_id;
    private DetailsDarenBean.DataBean.ItemsBean datas;
    private LikeFragment likeFragment;
    private RecomFragment recomFragment;
    private AttentionFragment attentionFragment;
    private FansFragment fansFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_daren);
        ButterKnife.inject(this);

        //取得从上一个Activity当中传递过来的Intent对象
        Intent intent = getIntent();
        //从Intent当中根据key取得value
        user_id = intent.getStringExtra("user_id");

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

        rgDaren.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switchFragment(checkedId);
            }
        });
        switchFragment(R.id.btn_like);
    }

    private void switchFragment(int checkedId) {
        android.support.v4.app.FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        hidden(transaction);

        switch (checkedId) {
            case  R.id.btn_like:
                if (likeFragment == null) {
                    likeFragment = new LikeFragment();

                    Bundle bundle = new Bundle();
                    bundle.putString("userid",user_id);//这里的values就是我们要传的值
                    likeFragment.setArguments(bundle);

                    transaction.add(R.id.frameLayout, likeFragment);
                } else {
                    transaction.show(likeFragment);
                }
                break;
            case  R.id.btn_recommend:
                if (recomFragment == null) {
                    recomFragment = new RecomFragment();

                    Bundle bundle = new Bundle();
                    bundle.putString("userid",user_id);//这里的values就是我们要传的值
                    recomFragment.setArguments(bundle);

                    transaction.add(R.id.frameLayout, recomFragment);
                } else {
                    transaction.show(recomFragment);
                }
                break;
            case  R.id.btn_attention:
                if (attentionFragment == null) {
                    attentionFragment = new AttentionFragment();

                    Bundle bundle = new Bundle();
                    bundle.putString("userid",user_id);//这里的values就是我们要传的值
                    attentionFragment.setArguments(bundle);

                    transaction.add(R.id.frameLayout, attentionFragment);
                } else {
                    transaction.show(attentionFragment);
                }
                break;
            case  R.id.btn_fans:
                if (fansFragment == null) {
                    fansFragment = new FansFragment();

                    Bundle bundle = new Bundle();
                    bundle.putString("userid",user_id);//这里的values就是我们要传的值
                    fansFragment.setArguments(bundle);

                    transaction.add(R.id.frameLayout, fansFragment);
                } else {
                    transaction.show(fansFragment);
                }
                break;
        }
        transaction.commit();
    }

    private void hidden(FragmentTransaction transaction) {
        if (likeFragment != null) {
            transaction.hide(likeFragment);
        }
        if (recomFragment != null) {
            transaction.hide(recomFragment);
        }
        if (attentionFragment != null) {
            transaction.hide(attentionFragment);
        }
        if (fansFragment != null) {
            transaction.hide(fansFragment);
        }
    }


    private void initData() {
        url = "http://mobile.iliangcang.com/user/masterListInfo?app_key=Android&count=10&owner_id="
                + user_id +
                "&page=1&sig=CD0E234053E25DD6111E3DBD450A4B85%7C954252010968868&v=1.0";
        getDataFromNet(url);
    }

    private void getDataFromNet(String url) {
        OkHttpUtils.get().url(url).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                Log.e("TAG", "请求失败==Details");
            }

            @Override
            public void onResponse(String response, int id) {
                Log.e("TAG", "请求成功==Details");
                processData(response);
            }
        });
    }

    private void processData(String response) {
        DetailsDarenBean bean = new Gson().fromJson(response, DetailsDarenBean.class);

        datas = bean.getData().getItems();

        tvFragment.setText(datas.getUser_name());
        x.image().bind(ivDarenHead, datas.getUser_image().getOrig());
        tvDarenName.setText(datas.getUser_name());
        tvDarenDuty.setText(datas.getUser_desc());

        String like = getResources().getString(R.string.count_like);
        String like_count = String.format(like, datas.getLike_count().toString());
        btnLike.setText(like_count);

        String recommend = getResources().getString(R.string.count_recommend);
        String recommend_count = String.format(recommend, datas.getRecommendation_count().toString());
        btnRecommend.setText(recommend_count);

        String attention = getResources().getString(R.string.count_attention);
        String attention_count = String.format(attention, datas.getFollowing_count().toString());
        btnAttention.setText(attention_count);

        String fans = getResources().getString(R.string.count_fans);
        String fans_count = String.format(fans, datas.getFollowed_count().toString());
        btnFans.setText(fans_count);

    }


}
