package com.atguigu.tiankuo.liangcang0224.fragment.darenfragment;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.atguigu.tiankuo.liangcang0224.R;
import com.atguigu.tiankuo.liangcang0224.base.BaseFragment;
import com.atguigu.tiankuo.liangcang0224.bean.DarenBean;
import com.atguigu.tiankuo.liangcang0224.utils.DarenUtils;
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
 * Created by Administrator on 2017/7/5 0005.
 */

public class DarenFragment extends BaseFragment {
    @InjectView(R.id.iv_search)
    ImageView ivSearch;
    @InjectView(R.id.tv_fragment)
    TextView tvFragment;
    @InjectView(R.id.iv_daren)
    ImageView ivDaren;
    @InjectView(R.id.recyclerview)
    RecyclerView recyclerview;
    @InjectView(R.id.iv_back)
    ImageView ivBack;
    private String url;
    private List<DarenBean.DataBean.ItemsBean> datas;
    private RecyclerView.Adapter adapter;

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment_daren, null);
        ButterKnife.inject(this, view);
        ivSearch.setVisibility(View.VISIBLE);
        tvFragment.setText("达人");
        ivDaren.setVisibility(View.VISIBLE);
        return view;
    }

    @Override
    public void initData() {
        super.initData();
        url = DarenUtils.MAIN_URL;
        getDataFromNet(url);
    }

    private void getDataFromNet(String url) {
        OkHttpUtils.get().url(url).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                Log.e("TAG", "请求失败");
            }

            @Override
            public void onResponse(String response, int id) {
                Log.e("TAG", "请求成功");
                processData(response);
            }
        });
    }

    private void processData(String response) {

        DarenBean bean = new Gson().fromJson(response, DarenBean.class);
//        Log.e("TAG",bean.getData().getItems().get(0).getNickname()+ "");
        datas = bean.getData().getItems();
        if (datas != null && datas.size() > 0) {
            //有数据
            adapter = new MyDarenAdapter(getActivity(), datas);
            recyclerview.setAdapter(adapter);
            recyclerview.setLayoutManager(new GridLayoutManager(mContext, 3, GridLayoutManager.VERTICAL, false));
        } else {
            //没有数据
            Log.e("TAG", "没有数据");
        }
    }

    @Override
    public void initListener() {
//        super.initListener();

        ivDaren.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ivDaren.setVisibility(View.GONE);
                ivBack.setVisibility(View.VISIBLE);
                Intent intent = new Intent(mContext,SortActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
