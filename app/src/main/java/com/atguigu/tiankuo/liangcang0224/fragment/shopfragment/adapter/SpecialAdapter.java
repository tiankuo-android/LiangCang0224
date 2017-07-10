package com.atguigu.tiankuo.liangcang0224.fragment.shopfragment.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.atguigu.tiankuo.liangcang0224.R;
import com.atguigu.tiankuo.liangcang0224.fragment.shopfragment.bean.SpecialBean;
import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * 作者：田阔
 * 邮箱：1226147264@qq.com
 * Created by Administrator on 2017/7/9 0009.
 */

public class SpecialAdapter extends RecyclerView.Adapter<SpecialAdapter.MyViewHolder> {
    private final List<SpecialBean.DataBean.ItemsBean> datas;
    private final Context context;


    public SpecialAdapter(Context mContext, List<SpecialBean.DataBean.ItemsBean> datas) {
        this.context = mContext;
        this.datas = datas;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_special, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        SpecialBean.DataBean.ItemsBean itemsBean = datas.get(position);
        holder.tvSpecial.setText(itemsBean.getTopic_name());
        String url = datas.get(position).getCover_img();
        Glide.with(context)
                .load(url)
                .into(holder.ivSpecial);
    }


    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public int getItemCount() {
        return datas == null ? 0 : datas.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        @InjectView(R.id.iv_special)
        ImageView ivSpecial;
        @InjectView(R.id.tv_special)
        TextView tvSpecial;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this,itemView);
        }
    }
}
