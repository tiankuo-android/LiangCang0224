package com.atguigu.tiankuo.liangcang0224.fragment.shopfragment.fragment;

import android.util.Log;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.atguigu.tiankuo.liangcang0224.R;
import com.atguigu.tiankuo.liangcang0224.base.BaseFragment;
import com.atguigu.tiankuo.liangcang0224.fragment.shopfragment.adapter.BrandAdapter;
import com.atguigu.tiankuo.liangcang0224.fragment.shopfragment.bean.BrandBean;
import com.atguigu.tiankuo.liangcang0224.utils.UrlUtils;
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
 * Created by Administrator on 2017/7/8 0008.
 */

public class BrandFragment extends BaseFragment {
    @InjectView(R.id.listview)
    ListView listview;
    @InjectView(R.id.refresh)
    MaterialRefreshLayout refresh;
    private List<BrandBean.DataBean.ItemsBean> datas;
    private MaterialRefreshLayout materialRefreshLayout;
    private boolean isLoadMore = false;
    private BrandAdapter adapter;

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment_shop_brand, null);
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

    private void getFromNet() {
        String url = UrlUtils.BREAD_BASE_URL + 1 + UrlUtils.BREAD_LAST_URL;
        OkHttpUtils.get().url(url).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                Log.e("TAG", "请求失败==Brand");
            }

            @Override
            public void onResponse(String json, int id) {
                Log.e("TAG", "请求成功==Brand");
                processData(json);
                materialRefreshLayout.finishRefresh();
            }
        });
    }

    private void processData(String json) {
        BrandBean brandBean = new Gson().fromJson(json, BrandBean.class);

        datas = brandBean.getData().getItems();
        if (datas != null && datas.size() > 0) {
            adapter = new BrandAdapter(mContext, datas);
            listview.setAdapter(adapter);

        } else {
            Log.e("TAG", "没有数据==Brand");
        }
    }

    @Override
    public void initData() {
        super.initData();
        getFromNet();
        ListAdapter adapter = new BrandAdapter(mContext, datas);
        listview.setAdapter(adapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
