package com.atguigu.tiankuo.liangcang0224.fragment.magazinefragment;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.atguigu.tiankuo.liangcang0224.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * 作者：田阔
 * 邮箱：1226147264@qq.com
 * Created by Administrator on 2017/7/13 0013.
 */

public class AuthorAdapter extends RecyclerView.Adapter<AuthorAdapter.MyViewHolder> {
    private final Context context;
    private final List<AuthorBean.DataBean.ItemsBean> datas;

    public AuthorAdapter(Context mContext, List<AuthorBean.DataBean.ItemsBean> datas) {
        this.datas = datas;
        this.context = mContext;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_magazine_author, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
//        Glide.with(context).load(datas.get(position).getThumb()).into(holder.ivAuthor);
        Glide.with(context)
                .load(datas.get(position).getThumb())
                .asBitmap()
                .centerCrop()
                .into(new BitmapImageViewTarget(holder.ivAuthor) {
                    @Override
                    protected void setResource(Bitmap resource) {
                        RoundedBitmapDrawable circularBitmapDrawable =
                                RoundedBitmapDrawableFactory.create(context.getResources(), resource);
                        circularBitmapDrawable.setCircular(true);
                        holder.ivAuthor.setImageDrawable(circularBitmapDrawable);
                    }
                });
        holder.tvAuthorName.setText(datas.get(position).getAuthor_name());
        holder.tvAuthorContent.setText(datas.get(position).getNote());
    }

    @Override
    public int getItemCount() {
        return datas == null ? 0 : datas.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        @InjectView(R.id.iv_author)
        ImageView ivAuthor;
        @InjectView(R.id.tv_author_name)
        TextView tvAuthorName;
        @InjectView(R.id.tv_author_content)
        TextView tvAuthorContent;
        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this,itemView);
        }
    }
}
