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
            return new ImageViewHolder(context, mLayoutInflater.inflate(R.layout.fragment_shop_home_image, parent,false));
        } else if (viewType == TYPE_GLIDE) {
            return new GlideViewHolder(context, mLayoutInflater.inflate(R.layout.fragment_shop_home_glide, parent,false));
        } else if (viewType == TYPE_LINEA) {
            return new LineaViewHolder(context, mLayoutInflater.inflate(R.layout.fragment_shop_home_linea, parent,false));
        } else if (viewType == TYPE_LISTV) {
            return new ListyViewHolder(context, mLayoutInflater.inflate(R.layout.fragment_shop_home_listv, parent,false));
        }
        return null;
    }

    @Override
    public int getItemViewType(int position) {

        HomeBean.DataBean.ItemsBean.ListBeanX listBean = datas.get(position);
        int type = listBean.getHome_type();//得到类型
        Log.e("TAG", "数据类型" + type);
        if (type == 6) {
            return TYPE_IMAGE;
        } else if (type == 4) {
            return TYPE_GLIDE;
        } else if (type == 1) {
            return TYPE_LINEA;
        } else if (type == 2) {
            return TYPE_LISTV;
        }
        ;
        return super.getItemViewType(position);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Log.e("TAG", "position == " + position);
        if (getItemViewType(position) == TYPE_IMAGE) {
            ImageViewHolder imageViewHolder = (ImageViewHolder) holder;
            imageViewHolder.setData(datas.get(position).getList(),position);

        } else if (getItemViewType(position) == TYPE_GLIDE) {
            GlideViewHolder glideViewHolder = (GlideViewHolder) holder;

            glideViewHolder.setData1(datas.get(position).getOne(),position);
            glideViewHolder.setData2(datas.get(position).getTwo(),position);
            glideViewHolder.setData3(datas.get(position).getThree(),position);
            glideViewHolder.setData4(datas.get(position).getFour(),position);

        } else if (getItemViewType(position) == TYPE_LINEA) {

            LineaViewHolder lineaViewHolder = (LineaViewHolder) holder;
            lineaViewHolder.setData(datas.get(position).getOne(),position);

        } else if (getItemViewType(position) == TYPE_LISTV) {

            ListyViewHolder listyViewHolder = (ListyViewHolder) holder;
            listyViewHolder.setData1(datas.get(position).getOne(), position);
            listyViewHolder.setData2(datas.get(position).getTwo(), position);

        }
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    class ImageViewHolder extends RecyclerView.ViewHolder {
        private final Context context;
        @InjectView(R.id.iv_shop_image)
        ImageView ivShopImage;

        public ImageViewHolder(Context context, View itemView) {
            super(itemView);
            this.context = context;
            ButterKnife.inject(this, itemView);
        }


        public void setData(List<HomeBean.DataBean.ItemsBean.ListBeanX.ListBean> listdata, int position) {
            //使用Glide加载图片
            Glide.with(context)
                    .load(listdata.get(position).getPic_url())
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .crossFade() //设置淡入淡出效果，默认300ms，可以传参
                    .into(ivShopImage);
        }
    }

    class GlideViewHolder extends RecyclerView.ViewHolder {
        private final Context context;
        @InjectView(R.id.iv_shop_glide1)
        ImageView ivShopGlide1;
        @InjectView(R.id.iv_shop_glide2)
        ImageView ivShopGlide2;
        @InjectView(R.id.iv_shop_glide3)
        ImageView ivShopGlide3;
        @InjectView(R.id.iv_shop_glide4)
        ImageView ivShopGlide4;

        public GlideViewHolder(Context context, View inflate) {
            super(inflate);
            this.context = context;
            ButterKnife.inject(this, inflate);
        }

        public void setData1(HomeBean.DataBean.ItemsBean.ListBeanX.OneBean one, int position) {
            Glide.with(context).load(one.getPic_url()).into(ivShopGlide1);
        }

        public void setData2(HomeBean.DataBean.ItemsBean.ListBeanX.TwoBean two, int position) {
            Glide.with(context).load(two.getPic_url()).into(ivShopGlide2);
        }

        public void setData3(HomeBean.DataBean.ItemsBean.ListBeanX.ThreeBean three, int position) {
            Glide.with(context).load(three.getPic_url()).into(ivShopGlide3);
        }

        public void setData4(HomeBean.DataBean.ItemsBean.ListBeanX.FourBean four, int position) {
            Glide.with(context).load(four.getPic_url()).into(ivShopGlide4);
        }
    }


    class LineaViewHolder extends RecyclerView.ViewHolder {
        private final Context context;
        @InjectView(R.id.iv_shop_linea)
        ImageView ivShopLinea;
        public LineaViewHolder(Context context, View inflate) {
            super(inflate);
            this.context = context;
            ButterKnife.inject(this,inflate);
        }


        public void setData(HomeBean.DataBean.ItemsBean.ListBeanX.OneBean one, int position) {
            Glide.with(context).load(one.getPic_url()).into(ivShopLinea);
        }
    }

    class ListyViewHolder extends RecyclerView.ViewHolder {
        private final Context context;
        @InjectView(R.id.iv_list1)
        ImageView ivList1;
        @InjectView(R.id.iv_list2)
        ImageView ivList2;

        public ListyViewHolder(Context context, View inflate) {
            super(inflate);
            this.context = context;
            ButterKnife.inject(this, inflate);
        }

        public void setData1(HomeBean.DataBean.ItemsBean.ListBeanX.OneBean one, int position) {
            Glide.with(context).load(one.getPic_url()).into(ivList1);
        }

        public void setData2(HomeBean.DataBean.ItemsBean.ListBeanX.TwoBean two, int position) {
            Glide.with(context).load(two.getPic_url()).into(ivList2);
        }
    }
}
