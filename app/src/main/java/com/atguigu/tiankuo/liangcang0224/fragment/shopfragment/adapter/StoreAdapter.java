package com.atguigu.tiankuo.liangcang0224.fragment.shopfragment.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.atguigu.tiankuo.liangcang0224.R;
import com.atguigu.tiankuo.liangcang0224.fragment.shopfragment.StoreActivity;
import com.atguigu.tiankuo.liangcang0224.fragment.shopfragment.bean.StoreBean;
import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * 作者：田阔
 * 邮箱：1226147264@qq.com
 * Created by Administrator on 2017/7/12 0012.
 */

public class StoreAdapter extends RecyclerView.Adapter<StoreAdapter.MyViewHolder> {
    private final List<StoreBean.DataBean.ItemsBean> datas;
    private final StoreActivity context;

    public StoreAdapter(StoreActivity storeActivity, List<StoreBean.DataBean.ItemsBean> datas) {
        this.context = storeActivity;
        this.datas = datas;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.fragment_shop_brand_goods_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        StoreBean.DataBean.ItemsBean bean = datas.get(position);

        holder.tvGoods.setText(bean.getGoods_name());
        Log.e("TAG","商店数据==" + bean.getGoods_name());
        holder.tvCompany.setText(bean.getBrand_info().getBrand_name());
        holder.tvPrice.setText(bean.getPrice());
        holder.tvPutup.setText(bean.getLike_count());
        Glide.with(context)
                .load(bean.getGoods_image())
                .into(holder.ivShopBrandGoods);
    }


    @Override
    public int getItemCount() {
        return datas == null ? 0 : datas.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        @InjectView(R.id.iv_shop_brand_goods)
        ImageView ivShopBrandGoods;
        @InjectView(R.id.tv_goods)
        TextView tvGoods;
        @InjectView(R.id.tv_company)
        TextView tvCompany;
        @InjectView(R.id.tv_price)
        TextView tvPrice;
        @InjectView(R.id.iv_putup)
        ImageView ivPutup;
        @InjectView(R.id.tv_putup)
        TextView tvPutup;
        @InjectView(R.id.ll_item_goods)
        LinearLayout llItemGoods;
        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);
        }
    }
}
