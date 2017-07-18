package com.atguigu.tiankuo.liangcang0224.fragment.magazinefragment.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.atguigu.tiankuo.liangcang0224.R;
import com.atguigu.tiankuo.liangcang0224.base.BaseFragment;
import com.atguigu.tiankuo.liangcang0224.fragment.magazinefragment.adapter.AuthorAdapter;
import com.atguigu.tiankuo.liangcang0224.fragment.magazinefragment.bean.AuthorBean;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import okhttp3.Call;

/**
 * 作者：田阔
 * 邮箱：1226147264@qq.com
 * Created by Administrator on 2017/7/13 0013.
 */

public class AuthorFragment extends BaseFragment {
    @InjectView(R.id.recyclerview)
    RecyclerView recyclerview;
    private String url;
    private List<AuthorBean.DataBean.ItemsBean> datas;

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment_magazine_author, null);
        ButterKnife.inject(this,view);
        return view;

    }

    @Override
    public void initData() {
        super.initData();
        url = "http://mobile.iliangcang.com/topic/magazineAuthorList?app_key=Android&sig=CD0E234053E25DD6111E3DBD450A4B85%7C954252010968868&v=1.0";
        getDataFromNet(url);
    }

    private void getDataFromNet(String url) {
        OkHttpUtils.get().url(url).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                Log.e("TAG", "联网失败==Author");
            }

            @Override
            public void onResponse(String response, int id) {
                Log.e("TAG", "联网成功==Author");
                processData(response);
            }
        });
    }

    private void processData(String response) {
        AuthorBean authorBean = new Gson().fromJson(response,AuthorBean.class);

        datas = authorBean.getData().getItems();

        if(datas != null && datas.size() > 0) {
            AuthorAdapter intent = new AuthorAdapter(mContext,datas);
            recyclerview.setAdapter(intent);
            recyclerview.setLayoutManager(new LinearLayoutManager(mContext,LinearLayoutManager.VERTICAL,false));
        }
    }

    @Override
    public void initListener() {
        super.initListener();

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
