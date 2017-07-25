package com.atguigu.tiankuo.liangcang0224.shopcart;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.atguigu.tiankuo.liangcang0224.R;
import com.atguigu.tiankuo.liangcang0224.fragment.GoodDetailsBean;
import com.atguigu.tiankuo.liangcang0224.utils.CartStorage;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

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
    @InjectView(R.id.tv_shopcart)
    TextView tvShopcart;
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
    private List<GoodDetailsBean> datas;
    private ShoppingCartAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);
        ButterKnife.inject(this);

        initView();
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

        checkboxAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean checked = checkboxAll.isChecked();
                //设置是否选择
                adapter.checkAll_none(checked);

                //重新计算价格
                adapter.showTotalPrice();
            }
        });


        tvShopcart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = tvShopcart.getText().toString().trim();
                if (str.equals("编辑")) {
                    tvShopcart.setText("完成");
                    adapter.showDelete(true);
                }

                if (str.equals("完成")) {
                    tvShopcart.setText("编辑");
                    adapter.showDelete(false);
                }
            }
        });
    }

    private void initView() {
        tvFragment.setText("购物车");
        ivDarenBack.setVisibility(View.VISIBLE);
        tvShopcart.setVisibility(View.VISIBLE);

        datas = CartStorage.getInstance(this).getAllData();
        adapter = new ShoppingCartAdapter(this, datas, checkboxAll, tvShopcartTotal);
    }

    private void initData() {

        recyclerview.setAdapter(adapter);
        recyclerview.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
    }
}
