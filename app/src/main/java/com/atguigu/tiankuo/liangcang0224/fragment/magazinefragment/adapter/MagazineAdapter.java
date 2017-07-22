package com.atguigu.tiankuo.liangcang0224.fragment.magazinefragment.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.atguigu.tiankuo.liangcang0224.R;
import com.atguigu.tiankuo.liangcang0224.fragment.magazinefragment.bean.MagazineBean;
import com.atguigu.tiankuo.liangcang0224.fragment.shopfragment.fragment.homefragment.HomeActivity;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * 作者：田阔
 * 邮箱：1226147264@qq.com
 * Created by Administrator on 2017/7/11 0011.
 */

public class MagazineAdapter extends RecyclerView.Adapter<MagazineAdapter.MyViewHolder> {

    private final ArrayList<MagazineBean> datas;
    private final Context context;

    public MagazineAdapter(Context mContext, ArrayList<MagazineBean> listMaga) {
        this.context = mContext;
        this.datas = listMaga;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.fragment_magazine_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        Glide.with(context)
                .load(datas.get(position).getCover_img())
                .into(holder.ivMagazine);
        holder.tvWord.setText(datas.get(position).getTopic_name());
        holder.tvType.setText(datas.get(position).getCat_name());
        holder.tvMonth.setText(datas.get(position).getAddtime());

        holder.llItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, HomeActivity.class);
                intent.putExtra("url",datas.get(position).getTopic_url());
                intent.putExtra("named",datas.get(position).getTopic_name());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return datas == null ? 0 : datas.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        @InjectView(R.id.iv_magazine)
        ImageView ivMagazine;
        @InjectView(R.id.tv_word)
        TextView tvWord;
        @InjectView(R.id.tv_type)
        TextView tvType;
        @InjectView(R.id.tv_month)
        TextView tvMonth;
        @InjectView(R.id.ll_item)
        LinearLayout llItem;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);
        }
    }
}
