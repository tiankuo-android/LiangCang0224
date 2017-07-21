package com.atguigu.tiankuo.liangcang0224.fragment;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.atguigu.tiankuo.liangcang0224.R;
import com.atguigu.tiankuo.liangcang0224.base.BaseFragment;
import com.atguigu.tiankuo.liangcang0224.fragment.shopfragment.adapter.StoreAdapter;
import com.atguigu.tiankuo.liangcang0224.fragment.shopfragment.bean.StoreBean;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import butterknife.ButterKnife;
import okhttp3.Call;

/**
 * 作者：田阔
 * 邮箱：1226147264@qq.com
 * Created by Administrator on 2017/7/18 0018.
 */
public class GoodsDetailsFragment extends BaseFragment {

    private LinearLayout llDetailsGoods;
    private String url;
    private String goodsid;
    private List<StoreBean.DataBean.ItemsBean> datasstore;
    private GoodDetailsBean bean;
    private List<GoodDetailsBean.DataBean.ItemsBean.GoodsInfoBean> datas;
    private GoodDetailsBean.DataBean.ItemsBean datadesc;
    private RecyclerView recyclerView;

    @Override
    public View initView() {
        // 创建LinearLayout对象
        llDetailsGoods = new LinearLayout(mContext);
        llDetailsGoods.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
        llDetailsGoods.setOrientation(LinearLayout.VERTICAL);

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            goodsid = bundle.getString("goodsid");
            bean = (GoodDetailsBean) bundle.getSerializable("bean");
            Log.e("TAG", "userid" + goodsid);
        }
        return llDetailsGoods;
    }

    @Override
    public void initData() {
        super.initData();
        url = "http://mobile.iliangcang.com/goods/guessLike?app_key=Android&goods_id="
                + goodsid + "&sig=CD0E234053E25DD6111E3DBD450A4B85%7C954252010968868&uid=305309276&user_token=e8fff51ce18d54cbf817cbcfd162cee&v=1.0";
        getDataFromNet(url);
    }

    private void getDataFromNet(String url) {
        OkHttpUtils.get().url(url).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                Log.e("TAG", "请求失败==GoodsDetails");
            }

            @Override
            public void onResponse(String response, int id) {
                Log.e("TAG", "请求成功==GoodsDetails");
                processData(response);
            }
        });
    }

    private void processData(String response) {
//        GoodDetailsBean bean = new Gson().fromJson(response, GoodDetailsBean.class);
        datas = bean.getData().getItems().getGoods_info();
        datadesc = bean.getData().getItems();

        if (datas != null && datas.size() > 0) {
            llDetailsGoods.removeAllViews();
            for (int i = 0; i < datas.size(); i++) {
                if (datas.get(i).getType() == 1) {
                    ImageView ivGoodsShop = new ImageView(mContext);
                    ivGoodsShop.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));  //设置图片宽高
                    Glide.with(this).load(datas.get(i).getContent().getImg()).into(ivGoodsShop);//图片资源
                    llDetailsGoods.addView(ivGoodsShop); //动态添加图片
                }
                if (datas.get(i).getType() == 0) {
                    TextView tvcontent = new TextView(mContext);
                    tvcontent.setText(datas.get(i).getContent().getText());
                    tvcontent.setPadding(15, 15, 15, 15);
                    llDetailsGoods.addView(tvcontent);
                }
                if (datas.get(i).getType() == 2) {
                    TextView tvcontenttitle = new TextView(mContext);
                    tvcontenttitle.setText(datas.get(i).getContent().getText());
                    tvcontenttitle.setTextSize(20);
                    tvcontenttitle.setPadding(15, 15, 15, 15);
                    llDetailsGoods.addView(tvcontenttitle);
                }
            }
        }

        TextView tvcontentdesc = new TextView(mContext);
        tvcontentdesc.setText(datadesc.getGoods_desc());
        tvcontentdesc.setPadding(15, 15, 15, 15);
        llDetailsGoods.addView(tvcontentdesc);

        View view = View.inflate(mContext, R.layout.fragment_details, null);

        TextView tv_brand_name = (TextView) view.findViewById(R.id.tv_brand_name);
        TextView tv_brand_desc = (TextView) view.findViewById(R.id.tv_brand_desc);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);

        TextView tv_gone = (TextView) view.findViewById(R.id.tv_gone);
        ImageView iv_gone = (ImageView) view.findViewById(R.id.iv_gone);
        LinearLayout llGone = (LinearLayout) view.findViewById(R.id.ll_gone);
        
//        if(datadesc.getHeadimg() != null) {
//            tv_gone.setVisibility(View.VISIBLE);
//            iv_gone.setVisibility(View.VISIBLE);
//            llGone.setVisibility(View.VISIBLE);
//
//            Glide.with(mContext).load(datadesc.getHeadimg()).into(iv_gone);
//            tv_gone.setText(datadesc.getRec_reason());
//        }
        
        tv_brand_name.setText(datadesc.getBrand_info().getBrand_name());
        tv_brand_desc.setText(datadesc.getBrand_info().getBrand_desc());


        StoreBean storeBean = new Gson().fromJson(response, StoreBean.class);
        datasstore = storeBean.getData().getItems();
        StoreAdapter adapter = new StoreAdapter(mContext,datasstore);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(mContext,2,GridLayoutManager.VERTICAL,false));

        llDetailsGoods.addView(view);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
