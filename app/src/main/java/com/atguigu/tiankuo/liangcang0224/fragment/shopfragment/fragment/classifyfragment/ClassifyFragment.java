package com.atguigu.tiankuo.liangcang0224.fragment.shopfragment.fragment.classifyfragment;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.atguigu.tiankuo.liangcang0224.R;
import com.atguigu.tiankuo.liangcang0224.base.BaseFragment;
import com.atguigu.tiankuo.liangcang0224.fragment.shopfragment.fragment.classifyfragment.adapter.ClassifyAdapter;
import com.atguigu.tiankuo.liangcang0224.fragment.shopfragment.bean.ClassifyBean;
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

public class ClassifyFragment extends BaseFragment {
    @InjectView(R.id.recyclerview)
    RecyclerView recyclerview;
    private String url;
    private List<ClassifyBean.DataBean.ItemsBean> datas;
    private ClassifyAdapter adapter;

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment_shop_classify, null);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void initData() {
        super.initData();
        url = "http://mobile.iliangcang.com/goods/goodsCategory?app_key=Android&sig=430BD99E6C913B8B8C3ED109737ECF15%7C830952120106768&v=1.0";
        getDataFromNet(url);
    }

    private void getDataFromNet(String url) {
        OkHttpUtils.get().url(url).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                Log.e("TAG", "请求失败==Classify");
            }

            @Override
            public void onResponse(String response, int id) {
                Log.e("TAG", "请求成功==Classify");
                processData(response);
            }
        });
    }

    private void processData(String response) {
        ClassifyBean classifyBean = new Gson().fromJson(response,ClassifyBean.class);
        datas = classifyBean.getData().getItems();
        if (datas != null && datas.size() > 0) {
            //有数据
            adapter = new ClassifyAdapter(getActivity(), datas);
            recyclerview.setAdapter(adapter);
            recyclerview.setLayoutManager(new GridLayoutManager(mContext, 2, GridLayoutManager.VERTICAL, false));
        } else {
            //没有数据
            Log.e("TAG", "没有数据==Classify");
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
