package com.atguigu.tiankuo.liangcang0224.fragment.magazinefragment;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.atguigu.tiankuo.liangcang0224.R;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * 作者：田阔
 * 邮箱：1226147264@qq.com
 * Created by Administrator on 2017/7/11 0011.
 */

public class MagazineAdapter extends RecyclerView.Adapter<MagazineAdapter.MyViewHolder> {

    private final Context context;
    private final MagazineBean datas;
    private MagazineBean.DataBean.ItemsBean magazineBean;

    public MagazineAdapter(Context mContext, MagazineBean datas) {
        this.context = mContext;
        this.datas = datas;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.fragment_magazine_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        magazineBean = datas.getData().getItems();


    }

    @Override
    public int getItemCount() {
        return 0;
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

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);
        }
    }
}
