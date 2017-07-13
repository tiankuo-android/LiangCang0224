package com.atguigu.tiankuo.liangcang0224.fragment.darenfragment.adapter;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.atguigu.tiankuo.liangcang0224.R;
import com.atguigu.tiankuo.liangcang0224.fragment.darenfragment.DetailsDarenActivity;
import com.atguigu.tiankuo.liangcang0224.fragment.darenfragment.bean.FansDarenBean;
import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * 作者：田阔
 * 邮箱：1226147264@qq.com
 * Created by Administrator on 2017/7/10 0010.
 */

public class FansDarenAdapter extends RecyclerView.Adapter<FansDarenAdapter.MyViewHolder> {
    private final List<FansDarenBean.DataBean.ItemsBean.UsersBean> datas;
    private final FragmentActivity context;


    public FansDarenAdapter(FragmentActivity activity, List<FansDarenBean.DataBean.ItemsBean.UsersBean> datas) {
        this.context = activity;
        this.datas = datas;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_daren_fans, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        holder.tvItemName.setText(datas.get(position).getUser_name());
        holder.tvItemJob.setText(datas.get(position).getUser_desc());
        String imageUrl = datas.get(position).getUser_image().getOrig();
        Glide.with(context)
                .load(imageUrl)
                .into(holder.ivItemDarenFans);

        holder.llItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailsDarenActivity.class);
                intent.putExtra("user_id",datas.get(position).getUser_id());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return datas == null ? 0 : datas.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        @InjectView(R.id.iv_item_daren_fans)
        ImageView ivItemDarenFans;
        @InjectView(R.id.tv_item_name)
        TextView tvItemName;
        @InjectView(R.id.tv_item_job)
        TextView tvItemJob;
        @InjectView(R.id.ll_item)
        LinearLayout llItem;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this,itemView);
        }
    }
}
