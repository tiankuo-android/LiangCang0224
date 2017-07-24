package com.atguigu.tiankuo.liangcang0224.fragment.darenfragment;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.atguigu.tiankuo.liangcang0224.R;
import com.atguigu.tiankuo.liangcang0224.fragment.darenfragment.bean.DarenBean;
import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * 作者：田阔
 * 邮箱：1226147264@qq.com
 * Created by Administrator on 2017/7/6 0006.
 */
public class MyDarenAdapter extends RecyclerView.Adapter<MyDarenAdapter.MyViewHolder> {

    private Context context;
    private List<DarenBean.DataBean.ItemsBean> datas;

    public MyDarenAdapter(Context context, List<DarenBean.DataBean.ItemsBean> datas) {
        this.context = context;
        this.datas = datas;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_daren, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final DarenBean.DataBean.ItemsBean itemsBean = datas.get(position);
        holder.tvItemName.setText(itemsBean.getUsername());
        holder.tvItemJob.setText(itemsBean.getDuty());

        String imageUrl = itemsBean.getUser_images().getOrig();
        Glide.with(context)
                .load(imageUrl)
                .into(holder.ivItemDaren);

        holder.llItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,DetailsDarenActivity.class);
                //序列化
                intent.putExtra("user_id",datas.get(position).getUid());
                Log.e("TAG","个人ID" + datas.get(position).getUid());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return datas == null ? 0 : datas.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        @InjectView(R.id.iv_item_daren)
        ImageView ivItemDaren;
        @InjectView(R.id.tv_item_name)
        TextView tvItemName;
        @InjectView(R.id.tv_item_job)
        TextView tvItemJob;
        @InjectView(R.id.ll_item)
        LinearLayout llItem;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);
        }
    }
}
