package com.atguigu.tiankuo.liangcang0224.fragment.magazinefragment;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.atguigu.tiankuo.liangcang0224.R;
import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * 作者：田阔
 * 邮箱：1226147264@qq.com
 * Created by Administrator on 2017/7/13 0013.
 */

public class SortAdapter extends RecyclerView.Adapter<SortAdapter.MyViewHolder> {
    private final List<SortBean.DataBean.ItemsBean> datas;
    private final Context context;

    public SortAdapter(Context mContext, List<SortBean.DataBean.ItemsBean> datas) {
        this.context = mContext;
        this.datas = datas;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_magazine_sort, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.tvSort.setText(datas.get(position).getCat_name());
        Glide.with(context)
                .load(datas.get(position).getThumb())
                .into(holder.ivSort);
    }

    @Override
    public int getItemCount() {
        return datas == null ? 0 : datas.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        @InjectView(R.id.iv_sort)
        ImageView ivSort;
        @InjectView(R.id.tv_sort)
        TextView tvSort;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);
        }
    }
}
