package com.atguigu.tiankuo.liangcang0224.fragment.magazinefragment;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.atguigu.tiankuo.liangcang0224.R;
import com.atguigu.tiankuo.liangcang0224.base.BaseFragment;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.ButterKnife;
import butterknife.InjectView;
import okhttp3.Call;

/**
 * 作者：田阔
 * 邮箱：1226147264@qq.com
 * Created by Administrator on 2017/7/7 0007.
 */

public class MagazineFragment extends BaseFragment {

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
    @InjectView(R.id.recyclerview)
    RecyclerView recyclerview;
    private String url;
    private MagazineAdapter adapter;
    private MagazineBean datas;

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment_magazine, null);
        ButterKnife.inject(this, view);
        ivSearch.setVisibility(View.VISIBLE);
        tvFragment.setText("杂志");
        return view;
    }

    @Override
    public void initData() {
        super.initData();
        url = "http://mobile.iliangcang.com/topic/magazineList?app_key=Android&sig=CD0E234053E25DD6111E3DBD450A4B85%7C954252010968868&v=1.0";
        getDataFromNet(url);
    }

    private void getDataFromNet(String url) {
        OkHttpUtils.get().url(url).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                Log.e("TAG", "联网失败==Magazine");
            }

            @Override
            public void onResponse(String response, int id) {
                Log.e("TAG", "联网成功==Magazine");
                processData(response);
            }
        });
    }

    private void processData(String response) {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
