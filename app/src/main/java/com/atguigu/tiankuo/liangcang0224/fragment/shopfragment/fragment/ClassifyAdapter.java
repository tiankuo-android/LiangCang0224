package com.atguigu.tiankuo.liangcang0224.fragment.shopfragment.fragment;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.atguigu.tiankuo.liangcang0224.R;
import com.atguigu.tiankuo.liangcang0224.fragment.darenfragment.MyDarenAdapter;
import com.atguigu.tiankuo.liangcang0224.fragment.shopfragment.bean.ClassifyBean;

import java.util.List;

/**
 * 作者：田阔
 * 邮箱：1226147264@qq.com
 * Created by Administrator on 2017/7/8 0008.
 */
public class ClassifyAdapter extends RecyclerView.Adapter<ClassifyAdapter.MyViewHolder>{

    private final Context context;
    private final List<ClassifyBean.DataBean.ItemsBean> datas;

    public ClassifyAdapter(Context context, List<ClassifyBean.DataBean.ItemsBean> datas) {
        this.context = context;
        this.datas = datas;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_daren,parent,false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    private class MyViewHolder {
        public MyViewHolder(View itemView) {

        }
    }
}
