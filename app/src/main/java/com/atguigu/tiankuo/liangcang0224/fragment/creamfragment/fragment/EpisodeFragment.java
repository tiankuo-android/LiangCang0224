package com.atguigu.tiankuo.liangcang0224.fragment.creamfragment.fragment;

import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.atguigu.tiankuo.liangcang0224.R;
import com.atguigu.tiankuo.liangcang0224.base.BaseFragment;
import com.atguigu.tiankuo.liangcang0224.fragment.creamfragment.adapter.EpisodeAdapter;
import com.atguigu.tiankuo.liangcang0224.fragment.creamfragment.domain.CreamBean;
import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;
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
 * Created by Administrator on 2017/7/7 0007.
 */

public class EpisodeFragment extends BaseFragment {
    @InjectView(R.id.listview)
    ListView listview;
    @InjectView(R.id.refresh)
    MaterialRefreshLayout refresh;
    private EpisodeAdapter adapter;
    private List<CreamBean.ListBean> datas;
    private MaterialRefreshLayout materialRefreshLayout;
    private String url = "http://s.budejie.com/topic/tag-topic/64/hot/budejie-android-6.6.3/0-20.json";
    private boolean isLoadMore = true;

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment_episode, null);
        ButterKnife.inject(this, view);

        materialRefreshLayout = (MaterialRefreshLayout) view.findViewById(R.id.refresh);
        materialRefreshLayout.setWaveColor(0xffffffff);
        materialRefreshLayout.setIsOverLay(false);
        materialRefreshLayout.setWaveShow(true);
        materialRefreshLayout.setMaterialRefreshListener(new MaterialRefreshListener() {
            @Override
            public void onRefresh(MaterialRefreshLayout materialRefreshLayout) {
                isLoadMore = false;
                getFromNet();
            }
        });

        return view;
    }

    @Override
    public void initData() {
        super.initData();
        getFromNet();
    }

    private void getFromNet() {
        OkHttpUtils.get().url(url).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                Log.e("TAG", "请求失败");
            }

            @Override
            public void onResponse(String json, int id) {
                Log.e("TAG", "段子请求成功");
                processData(json);
                materialRefreshLayout.finishRefresh();
            }
        });
    }

    private void processData(String json) {
        CreamBean creamBean = new Gson().fromJson(json, CreamBean.class);

        datas = creamBean.getList();
        if (datas != null && datas.size() > 0) {
            adapter = new EpisodeAdapter(mContext, datas);
            listview.setAdapter(adapter);

        } else {
            Log.e("TAG", "没有数据");
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

}
