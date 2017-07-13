package com.atguigu.tiankuo.liangcang0224.fragment.shopfragment.fragment.classifyfragment.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.atguigu.tiankuo.liangcang0224.R;
import com.atguigu.tiankuo.liangcang0224.fragment.shopfragment.bean.ClassifyBean;
import com.atguigu.tiankuo.liangcang0224.fragment.shopfragment.StoreActivity;
import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * 作者：田阔
 * 邮箱：1226147264@qq.com
 * Created by Administrator on 2017/7/8 0008.
 */
public class ClassifyAdapter extends RecyclerView.Adapter<ClassifyAdapter.MyViewHolder> {

    private final Context context;
    private final List<ClassifyBean.DataBean.ItemsBean> datas;

    public ClassifyAdapter(Context context, List<ClassifyBean.DataBean.ItemsBean> datas) {
        this.context = context;
        this.datas = datas;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_classify, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final ClassifyBean.DataBean.ItemsBean classifyBean = datas.get(position);
        String imageUrl = classifyBean.getCover_img();
        Glide.with(context)
                .load(imageUrl)
                .into(holder.ivClassify);

        holder.ivClassify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,StoreActivity.class);
                intent.putExtra("classify" , classifyBean.getCat_id());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return datas == null ? 0 : datas.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        @InjectView(R.id.iv_classify)
        ImageView ivClassify;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this,itemView);
        }
    }
}
