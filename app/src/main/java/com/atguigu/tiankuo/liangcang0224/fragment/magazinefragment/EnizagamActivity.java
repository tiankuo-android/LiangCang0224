package com.atguigu.tiankuo.liangcang0224.fragment.magazinefragment;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.atguigu.tiankuo.liangcang0224.R;
import com.atguigu.tiankuo.liangcang0224.fragment.magazinefragment.fragment.AuthorFragment;
import com.atguigu.tiankuo.liangcang0224.fragment.magazinefragment.fragment.SortFragment;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class EnizagamActivity extends AppCompatActivity {

    @InjectView(R.id.btn_sort)
    RadioButton btnSort;
    @InjectView(R.id.btn_author)
    RadioButton btnAuthor;
    @InjectView(R.id.fl_content)
    FrameLayout flContent;
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
    @InjectView(R.id.activity_enizagam)
    LinearLayout activityEnizagam;
    @InjectView(R.id.rg_enizagam)
    RadioGroup rgEnizagam;
    private String url;
    private SortFragment sortFragment;
    private AuthorFragment authorFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enizagam);
        ButterKnife.inject(this);
        tvFragment.setText("杂志");

        initListener();
    }

    private void initListener() {

        llTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               finish();
            }
        });

        rgEnizagam.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switchFragment(checkedId);
            }
        });
        switchFragment(R.id.btn_sort);
    }

    private void switchFragment(int checkedId) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        hidden(transaction);
        switch (checkedId) {
            case R.id.btn_sort:
                if (sortFragment == null) {
                    sortFragment = new SortFragment();

//                    Bundle bundle = new Bundle();
//                    bundle.putString("userid",user_id);//这里的values就是我们要传的值
//                    sortFragment.setArguments(bundle);

                    transaction.add(R.id.fl_content, sortFragment);
                } else {
                    transaction.show(sortFragment);
                }
                break;
            case R.id.btn_author:
                if (authorFragment == null) {
                    authorFragment = new AuthorFragment();
                    transaction.add(R.id.fl_content, authorFragment);
                } else {
                    transaction.show(authorFragment);
                }
                break;
        }
        transaction.commit();
    }

    private void hidden(FragmentTransaction transaction) {
        if (sortFragment != null) {
            transaction.hide(sortFragment);
        }
        if (authorFragment != null) {
            transaction.hide(authorFragment);
        }
    }
}
