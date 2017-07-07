package com.atguigu.tiankuo.liangcang0224.fragment.darenfragment;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.atguigu.tiankuo.liangcang0224.R;
import com.atguigu.tiankuo.liangcang0224.bean.DarenBean;
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
        Log.e("TAG","空指针");
//        View itemView = View.inflate(context, R.layout.item_daren, null);
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_daren,parent,false);
        Log.e("TAG",itemView + "");
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        DarenBean.DataBean.ItemsBean itemsBean = datas.get(position);
        holder.tvItemName.setText(itemsBean.getUsername());
        holder.tvItemJob.setText(itemsBean.getDuty());

        String imageUrl = itemsBean.getUser_images().getOrig();
        Glide.with(context)
                .load(imageUrl)
                .into(holder.ivItemDaren);
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

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);
        }
    }

}
