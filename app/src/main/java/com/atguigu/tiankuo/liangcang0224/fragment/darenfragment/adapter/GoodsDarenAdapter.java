package com.atguigu.tiankuo.liangcang0224.fragment.darenfragment.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.atguigu.tiankuo.liangcang0224.R;
import com.atguigu.tiankuo.liangcang0224.fragment.darenfragment.bean.GoodsDarenBean;

/**
 * 作者：田阔
 * 邮箱：1226147264@qq.com
 * Created by Administrator on 2017/7/14 0014.
 */

public class GoodsDarenAdapter extends RecyclerView.Adapter<GoodsDarenAdapter.MyViewHolder> {
    private final GoodsDarenBean.DataBean.ItemsBean datas;
    private final Context context;

    public GoodsDarenAdapter(Context context, GoodsDarenBean.DataBean.ItemsBean datas) {
        this.context =context;
        this.datas = datas;
    }

    @Override
    public GoodsDarenAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_daren_goods,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(GoodsDarenAdapter.MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        public MyViewHolder(View itemView) {
            super(itemView);
        }
    }
}
