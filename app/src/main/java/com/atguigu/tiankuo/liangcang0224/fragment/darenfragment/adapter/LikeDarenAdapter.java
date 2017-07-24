package com.atguigu.tiankuo.liangcang0224.fragment.darenfragment.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.atguigu.tiankuo.liangcang0224.R;
import com.atguigu.tiankuo.liangcang0224.fragment.darenfragment.GoodsDarenActivity;
import com.atguigu.tiankuo.liangcang0224.fragment.darenfragment.bean.DetailsDarenBean;
import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * 作者：田阔
 * 邮箱：1226147264@qq.com
 * Created by Administrator on 2017/7/10 0010.
 */

public class LikeDarenAdapter extends RecyclerView.Adapter<LikeDarenAdapter.MyViewHolder> {
    private final List<DetailsDarenBean.DataBean.ItemsBean.GoodsBean> datas;
    private final Context context;



    public LikeDarenAdapter(Context context, List<DetailsDarenBean.DataBean.ItemsBean.GoodsBean> datas) {
        this.context = context;
        this.datas = datas;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_daren_like, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        String imageUrl = datas.get(position).getGoods_image();
        Glide.with(context)
                .load(imageUrl)
                .into(holder.ivDarenLike);

        holder.llItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,GoodsDarenActivity.class);
                intent.putExtra("goodid",datas.get(position).getGoods_id());
                intent.putExtra("goodsimage",datas.get(position).getGoods_image());
                intent.putExtra("goodsname",datas.get(position).getGoods_name());
                intent.putExtra("price",datas.get(position).getPrice());
                intent.putExtra("likecount",datas.get(position).getLike_count());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return datas == null ? 0 : datas.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        @InjectView(R.id.iv_daren_like)
        ImageView ivDarenLike;
        @InjectView(R.id.ll_item)
        LinearLayout llItem;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);
        }
    }
}
