package com.atguigu.tiankuo.liangcang0224.fragment.darenfragment;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.atguigu.tiankuo.liangcang0224.R;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class SortActivity extends AppCompatActivity {

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
    @InjectView(R.id.tv_default)
    TextView tvDefault;
    @InjectView(R.id.tv_more)
    TextView tvMore;
    @InjectView(R.id.tv_hot)
    TextView tvHot;
    @InjectView(R.id.tv_new)
    TextView tvNew;
    @InjectView(R.id.tv_putin)
    TextView tvPutin;
    @InjectView(R.id.activity_sort)
    LinearLayout activitySort;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sort);
        ButterKnife.inject(this);

        ivSearch.setVisibility(View.VISIBLE);
        ivDaren.setVisibility(View.GONE);
        tvFragment.setText("达人");
        ivBack.setVisibility(View.VISIBLE);
        initListener();
    }

    private void initListener() {
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


}
