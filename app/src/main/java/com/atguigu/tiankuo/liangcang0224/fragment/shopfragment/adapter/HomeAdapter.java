package com.atguigu.tiankuo.liangcang0224.fragment.shopfragment.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.atguigu.tiankuo.liangcang0224.R;
import com.atguigu.tiankuo.liangcang0224.fragment.shopfragment.bean.HomeBean;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * 作者：田阔
 * 邮箱：1226147264@qq.com
 * Created by Administrator on 2017/7/10 0010.
 */

public class HomeAdapter extends RecyclerView.Adapter {

    private final List<HomeBean.DataBean.ItemsBean.ListBeanX> datas;
    private final Context context;
    public static final int TYPE_IMAGE = 0;
    public static final int TYPE_GLIDE = 1;
    public static final int TYPE_LINEA = 2;
    public static final int TYPE_LISTV = 3;
    private final LayoutInflater mLayoutInflater;

    public HomeAdapter(Context mContext, List<HomeBean.DataBean.ItemsBean.ListBeanX> datas) {
        this.context = mContext;
        this.datas = datas;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_IMAGE) {
            return new ImageViewHolder(context , mLayoutInflater.inflate(R.layout.fragment_shop_home_image, null));
        }
//        if (viewType == TYPE_GLIDE) {
//            return new GlideViewHolder(mLayoutInflater.inflate(R.layout.fragment_shop_home_glide, null));
//        }
//        if (viewType == TYPE_LINEA) {
//            return new LineaViewHolder(mLayoutInflater.inflate(R.layout.fragment_shop_home_linea, null));
//        }
//        if (viewType == TYPE_LISTV) {
//            return new ListyViewHolder(mLayoutInflater.inflate(R.layout.fragment_shop_home_listv, null));
//        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == TYPE_IMAGE) {
            ImageViewHolder imageViewHolder = (ImageViewHolder) holder;
            List<HomeBean.DataBean.ItemsBean.ListBeanX.ListBean> listdata = datas.get(0).getList();
            Log.e("TAG","主页" + listdata.get(0).getPic_url());
            imageViewHolder.setData(listdata);
        }
//        if (getItemViewType(position) == TYPE_GLIDE) {
//            GlideViewHolder glideViewHolder = (GlideViewHolder) holder;
//            List<HomeBean.DataBean.ItemsBean.ListBeanX.ListBean> glidedata = datas.get(1).getList();
//            glideViewHolder.setData(glidedata);
//        }
//        if (getItemViewType(position) == TYPE_LINEA) {
//            LineaViewHolder lineaViewHolder = (LineaViewHolder) holder;
//            List<HomeBean.DataBean.ItemsBean.ListBeanX.ListBean> lineadata = datas.get(2).getList();
//            lineaViewHolder.setData(lineadata);
//        }
//        if (getItemViewType(position) == TYPE_LISTV) {
//            ListyViewHolder listyViewHolder = (ListyViewHolder) holder;
//            List<HomeBean.DataBean.ItemsBean.ListBeanX.ListBean> listvdata = datas.get(3).getList();
//            listyViewHolder.setData(listvdata);
//        }
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    @Override
    public int getItemViewType(int position) {
        int itemViewType = 0;
        HomeBean.DataBean.ItemsBean.ListBeanX listBean = datas.get(position);
        int type = listBean.getHome_type();//得到类型
        if ("6".equals(type)) {
            itemViewType = TYPE_IMAGE;
        } else if ("4".equals(type)) {
            itemViewType = TYPE_GLIDE;
        } else if ("1".equals(type)) {
            itemViewType = TYPE_LINEA;
        } else if ("2".equals(type)) {
            itemViewType = TYPE_LISTV;
        };
        return itemViewType;
    }

//    @Override
//    public long getItemId(int position) {
//        return super.getItemId(position);
//    }

    class ImageViewHolder extends RecyclerView.ViewHolder {
        private final Context context;
        @InjectView(R.id.iv_shop_image)
        ImageView ivShopImage;

        public ImageViewHolder(Context context , View itemView) {
            super(itemView);
            this.context = context;
            ButterKnife.inject(this, itemView);
        }

        public void setData(List<HomeBean.DataBean.ItemsBean.ListBeanX.ListBean> list0data) {
            //使用Glide加载图片
            Glide.with(context)
                    .load(datas.get(0).getPic_url())
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .crossFade() //设置淡入淡出效果，默认300ms，可以传参
                    .into(ivShopImage);
        }
    }

//    private class GlideViewHolder extends RecyclerView.ViewHolder {
//        public GlideViewHolder(View inflate) {
//            super(inflate);
//        }
//
//        public void setData(List<HomeBean.DataBean.ItemsBean.ListBeanX.ListBean> glidedata) {
//
//        }
//    }
//
//    private class LineaViewHolder extends RecyclerView.ViewHolder {
//        public LineaViewHolder(View inflate) {
//            super(inflate);
//        }
//
//        public void setData(List<HomeBean.DataBean.ItemsBean.ListBeanX.ListBean> lineadata) {
//
//        }
//    }
//
//    private class ListyViewHolder extends RecyclerView.ViewHolder {
//        public ListyViewHolder(View inflate) {
//            super(inflate);
//        }
//
//        public void setData(List<HomeBean.DataBean.ItemsBean.ListBeanX.ListBean> listvdata) {
//
//        }
//    }
}
