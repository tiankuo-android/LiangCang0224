package com.atguigu.tiankuo.liangcang0224.fragment.shopfragment.fragment.brandfragment.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.atguigu.tiankuo.liangcang0224.R;
import com.atguigu.tiankuo.liangcang0224.fragment.shopfragment.fragment.brandfragment.BrandProductActivity;
import com.atguigu.tiankuo.liangcang0224.fragment.shopfragment.fragment.brandfragment.bean.BrandBean;
import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * 作者：田阔
 * 邮箱：1226147264@qq.com
 * Created by Administrator on 2017/7/8 0008.
 */

public class BrandAdapter extends BaseAdapter {

    private final List<BrandBean.DataBean.ItemsBean> datas;
    private final Context context;

    public BrandAdapter(Context mContext, List<BrandBean.DataBean.ItemsBean> datas) {
        this.context = mContext;
        this.datas = datas;
    }

    @Override
    public int getCount() {
        return datas == null ? 0 : datas.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_shop_brand, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tvName.setText(datas.get(position).getBrand_name());
        String imageView = datas.get(position).getBrand_logo();
        Glide.with(context)
                .load(imageView)
                .into(viewHolder.ivHead);

        viewHolder.llItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, BrandProductActivity.class);
                intent.putExtra("brand", datas.get(position).getBrand_id() + "");
                intent.putExtra("position",position + "");
                context.startActivity(intent);
            }
        });
        return convertView;
    }

    class ViewHolder {
        @InjectView(R.id.iv_head)
        ImageView ivHead;
        @InjectView(R.id.tv_name)
        TextView tvName;
        @InjectView(R.id.iv_putin)
        ImageView ivPutin;
        @InjectView(R.id.ll_item)
        LinearLayout llItem;

        ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
