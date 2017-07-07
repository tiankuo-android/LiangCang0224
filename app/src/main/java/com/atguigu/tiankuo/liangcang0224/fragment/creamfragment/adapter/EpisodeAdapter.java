package com.atguigu.tiankuo.liangcang0224.fragment.creamfragment.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.atguigu.tiankuo.liangcang0224.R;
import com.atguigu.tiankuo.liangcang0224.fragment.creamfragment.domain.EpisodeBean;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * 作者：田阔
 * 邮箱：1226147264@qq.com
 * Created by Administrator on 2017/7/7 0007.
 */

public class EpisodeAdapter extends BaseAdapter {
    private final List<EpisodeBean.ListBean> datas;
    private final Context context;

    public EpisodeAdapter(Context mContext, List<EpisodeBean.ListBean> datas) {
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
        viewHolder.tvName.setText(datas.get(0).getU().getName());
        viewHolder.tvTimeRefresh.setText(datas.get(0).getPasstime());

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
        @InjectView(R.id.iv_right_more)
        ImageView ivRightMore;

        ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
