package com.atguigu.tiankuo.liangcang0224.fragment.magazinefragment;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.atguigu.tiankuo.liangcang0224.R;
import com.atguigu.tiankuo.liangcang0224.base.BaseFragment;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

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
    @InjectView(R.id.tv_date)
    TextView tvDate;
    @InjectView(R.id.ll_title)
    LinearLayout llTitle;
    private String url;
    private ArrayList<MagazineBean> listMaga;
    private MagazineAdapter adapter;

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment_magazine, null);
        ButterKnife.inject(this, view);
        tvDate.setVisibility(View.VISIBLE);
        tvFragment.setText("杂志");
        return view;
    }

    @Override
    public void initListener() {
        super.initListener();

        llTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext,EnizagamActivity.class);
                startActivity(intent);
            }
        });
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
        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONObject data = jsonObject.getJSONObject("data");
            JSONObject items = data.getJSONObject("items");
            JSONArray keys = items.getJSONArray("keys");

            listMaga = new ArrayList();
            JSONObject infos = items.getJSONObject("infos");

            for (int i = 0; i < keys.length(); i++) {
                String key = keys.getString(i);
                JSONArray key2 = infos.getJSONArray(key);
                for (int i1 = 0; i1 < key2.length(); i1++) {
                    MagazineBean bean = new MagazineBean();
                    JSONObject info = key2.getJSONObject(i1);

                    String name = info.getString("topic_name");
                    bean.setTopic_name(name);
                    Log.e("TAG", "name===========" + name);

                    String catname = info.getString("cat_name");
                    bean.setCat_name(catname);

                    String cover = info.getString("cover_img");
                    bean.setCover_img(cover);

                    bean.setTopic_url(info.getString("topic_url"));
                    bean.setAddtime(info.getString("addtime").substring(5, 10));
                    listMaga.add(bean);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        adapter = new MagazineAdapter(mContext,listMaga);
        recyclerview.setAdapter(adapter);
        recyclerview.setLayoutManager(new LinearLayoutManager(mContext,LinearLayoutManager.VERTICAL,false));
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}