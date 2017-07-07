package com.atguigu.tiankuo.liangcang0224.fragment.creamfragment.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.atguigu.tiankuo.liangcang0224.R;
import com.atguigu.tiankuo.liangcang0224.fragment.creamfragment.domain.CreamBean;
import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * 作者：田阔
 * 邮箱：1226147264@qq.com
 * Created by Administrator on 2017/7/7 0007.
 */

public class EpisodeAdapter extends BaseAdapter {
    private final List<CreamBean.ListBean> datas;
    private final Context context;

    public EpisodeAdapter(Context mContext, List<CreamBean.ListBean> datas) {
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
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_episode, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tvName.setText(datas.get(position).getU().getName());
        viewHolder.tvTimeRefresh.setText(datas.get(position).getPasstime());
        viewHolder.tvContext.setText(datas.get(position).getText());
        String url = datas.get(position).getU().getRoom_url();

        Glide.with(context)
                .load(url)
                .into(viewHolder.ivHeadpic);
        return convertView;
    }

    class ViewHolder {
        @InjectView(R.id.iv_headpic)
        ImageView ivHeadpic;
        @InjectView(R.id.tv_name)
        TextView tvName;
        @InjectView(R.id.tv_time_refresh)
        TextView tvTimeRefresh;
        @InjectView(R.id.ll_video_user_info)
        LinearLayout llVideoUserInfo;
        @InjectView(R.id.tv_context)
        TextView tvContext;
        @InjectView(R.id.tv_ding)
        TextView tvDing;
        @InjectView(R.id.tv_shenhe_ding_number)
        TextView tvShenheDingNumber;
        @InjectView(R.id.ll_ding)
        LinearLayout llDing;
        @InjectView(R.id.iv_cai)
        TextView ivCai;
        @InjectView(R.id.tv_shenhe_cai_number)
        TextView tvShenheCaiNumber;
        @InjectView(R.id.ll_cai)
        LinearLayout llCai;
        @InjectView(R.id.tv_posts_number)
        TextView tvPostsNumber;
        @InjectView(R.id.ll_share)
        LinearLayout llShare;
        @InjectView(R.id.tv_download_number)
        TextView tvDownloadNumber;
        @InjectView(R.id.ll_download)
        LinearLayout llDownload;

        ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
