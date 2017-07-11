package com.atguigu.tiankuo.liangcang0224.fragment.shopfragment.fragment.brandfragment;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.atguigu.tiankuo.liangcang0224.R;
import com.atguigu.tiankuo.liangcang0224.fragment.shopfragment.bean.BrandProductBean;
import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * 作者：田阔
 * 邮箱：1226147264@qq.com
 * Created by Administrator on 2017/7/11 0011.
 */

public class BrandGoodsAdapter extends RecyclerView.Adapter<BrandGoodsAdapter.MyViewHolder> {
    private final List<BrandProductBean.DataBean.ItemsBean> datas;
    private final Context context;


    public BrandGoodsAdapter(Context mContext, List<BrandProductBean.DataBean.ItemsBean> datas) {
        this.context = mContext;
        this.datas = datas;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.fragment_shop_brand_goods_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        BrandProductBean.DataBean.ItemsBean bean = datas.get(position);

        holder.tvGoods.setText(bean.getGoods_name());
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
        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this,itemView);
        }
    }
}
