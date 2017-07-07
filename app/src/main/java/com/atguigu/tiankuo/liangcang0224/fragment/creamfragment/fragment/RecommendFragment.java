package com.atguigu.tiankuo.liangcang0224.fragment.creamfragment.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.atguigu.tiankuo.liangcang0224.R;
import com.atguigu.tiankuo.liangcang0224.base.BaseFragment;
import com.atguigu.tiankuo.liangcang0224.fragment.creamfragment.ShowImageAndGifActivity;
import com.atguigu.tiankuo.liangcang0224.fragment.creamfragment.adapter.RecommendAdapter;
import com.atguigu.tiankuo.liangcang0224.fragment.creamfragment.domain.CreamBean;
import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;
import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.common.util.LogUtil;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * 作者：田阔
 * 邮箱：1226147264@qq.com
 * Created by Administrator on 2017/7/7 0007.
 */

public class RecommendFragment extends BaseFragment {

    @InjectView(R.id.listview)
    ListView listview;
    @InjectView(R.id.refresh)
    MaterialRefreshLayout refresh;
    private MaterialRefreshLayout materialRefreshLayout;
    private boolean isLoadMore = false;
    private List<CreamBean.ListBean> datas;
    private String NET_AUDIO_URL = "http://s.budejie.com/topic/list/jingxuan/1/budejie-android-6.2.8/0-20.json?market=baidu&udid=863425026599592&appname=baisibudejie&os=4.2.2&client=android&visiting=&mac=98%3A6c%3Af5%3A4b%3A72%3A6d&ver=6.2.8";
    private RecommendAdapter myAdapter;

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment_recommend, null);
        ButterKnife.inject(this, view);

        materialRefreshLayout = (MaterialRefreshLayout) view.findViewById(R.id.refresh);
        materialRefreshLayout.setWaveColor(0xffffffff);
        materialRefreshLayout.setIsOverLay(false);
        materialRefreshLayout.setWaveShow(true);
        materialRefreshLayout.setMaterialRefreshListener(new MaterialRefreshListener() {
            @Override
            public void onRefresh(MaterialRefreshLayout materialRefreshLayout) {
                isLoadMore = false;
                getDataFromNet();
            }
        });

        //设置点击事件
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                CreamBean.ListBean listEntity = datas.get(position);
                if (listEntity != null) {
                    //3.传递视频列表
                    Intent intent = new Intent(mContext, ShowImageAndGifActivity.class);
                    if (listEntity.getType().equals("gif")) {
                        String url = listEntity.getGif().getImages().get(0);
                        intent.putExtra("url", url);
                        mContext.startActivity(intent);
                    } else if (listEntity.getType().equals("image")) {
                        String url = listEntity.getImage().getBig().get(0);
                        intent.putExtra("url", url);
                        mContext.startActivity(intent);
                    }
                }
            }
        });
        return view;
    }

    @Override
    public void initData() {
        super.initData();
        getDataFromNet();
    }

    private void getDataFromNet() {
        RequestParams reques = new RequestParams(NET_AUDIO_URL);
        x.http().get(reques, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                processData(result);
                materialRefreshLayout.finishRefresh();
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    private void processData(String result) {
        CreamBean netAudioBean = new Gson().fromJson(result, CreamBean.class);
//        NetAudioBean netAudioBean = paraseJson(result);
        LogUtil.e(netAudioBean.getList().get(0).getText() + "-----------");

        datas = netAudioBean.getList();

        if (datas != null && datas.size() > 0) {
            //有视频
            //设置适配器
            myAdapter = new RecommendAdapter(mContext, datas);
            listview.setAdapter(myAdapter);
        } else {
            //没有视频
        }

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
