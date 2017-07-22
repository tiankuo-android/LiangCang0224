package com.atguigu.tiankuo.liangcang0224.fragment.magazinefragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.atguigu.tiankuo.liangcang0224.R;
import com.atguigu.tiankuo.liangcang0224.fragment.magazinefragment.adapter.MagazineAdapter;
import com.atguigu.tiankuo.liangcang0224.fragment.magazinefragment.bean.MagazineBean;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;
import okhttp3.Call;

public class MagazineActivity extends AppCompatActivity {

    @InjectView(R.id.iv_back)
    ImageView ivBack;
    @InjectView(R.id.tv_magazine)
    TextView tvMagazine;
    @InjectView(R.id.recyclerview)
    RecyclerView recyclerview;
    @InjectView(R.id.activity_magazine)
    LinearLayout activityMagazine;
    private String url;
    private String autherid;
    private ArrayList listMaga;
    private MagazineAdapter adapter;
    private String auther;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_magazine);
        ButterKnife.inject(this);

        Intent intent = getIntent();
        autherid = intent.getStringExtra("autherid");
        auther = intent.getStringExtra("auther");

        initData();
        initListener();
    }

    private void initListener() {
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initData() {
        url = "http://mobile.iliangcang.com/topic/magazineList?app_key=Android&author_id="
        + autherid + "&sig=CD0E234053E25DD6111E3DBD450A4B85%7C954252010968868&uid=305309276&user_token=e8fff51ce18d54cbf817cbcfd162cee5&v=1.0";
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

        String like = getResources().getString(R.string.magazine_title);
        String like_count = String.format(like,auther);
        tvMagazine.setText(like_count);

        adapter = new MagazineAdapter(this,listMaga);
        recyclerview.setAdapter(adapter);
        recyclerview.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
    }
}
