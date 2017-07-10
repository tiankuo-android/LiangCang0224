package com.atguigu.tiankuo.liangcang0224.fragment.darenfragment.fragment;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.atguigu.tiankuo.liangcang0224.R;
import com.atguigu.tiankuo.liangcang0224.base.BaseFragment;
import com.atguigu.tiankuo.liangcang0224.fragment.darenfragment.bean.DetailsDarenBean;
import com.atguigu.tiankuo.liangcang0224.fragment.darenfragment.adapter.LikeDarenAdapter;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import okhttp3.Call;

public class LikeFragment extends BaseFragment {

    @InjectView(R.id.recyclerview)
    RecyclerView recyclerview;
    private String url;
    private String userid;
    private List<DetailsDarenBean.DataBean.ItemsBean.GoodsBean> datas;
    private LikeDarenAdapter adapter;

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.activity_like_fragment, null);
        ButterKnife.inject(this, view);
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            userid = bundle.getString("userid");
            Log.e("TAG", "userid" + userid);
        }
        return view;
    }

    @Override
    public void initData() {
        super.initData();
        url = "http://mobile.iliangcang.com/user/masterLike?app_key=Android&count=10&owner_id="
                + userid +
                "&page=1&sig=CD0E234053E25DD6111E3DBD450A4B85%7C954252010968868&v=1.0";
        getDataFromNet(url);
    }

    private void getDataFromNet(String url) {
        OkHttpUtils.get().url(url).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                Log.e("TAG", "请求失败==Daren_like");
            }

            @Override
            public void onResponse(String response, int id) {
                Log.e("TAG", "请求成功==Daren_like");
                processData(response);
            }
        });
    }

    private void processData(String response) {
        DetailsDarenBean bean = new Gson().fromJson(response,DetailsDarenBean.class);
        datas = bean.getData().getItems().getGoods();
        if (datas != null && datas.size() > 0) {
            //有数据
            adapter = new LikeDarenAdapter(getActivity(), datas);
            recyclerview.setAdapter(adapter);
            recyclerview.setLayoutManager(new GridLayoutManager(mContext, 2, GridLayoutManager.VERTICAL, false));
        } else {
            //没有数据
            Log.e("TAG", "没有数据");
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
