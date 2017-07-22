package com.atguigu.tiankuo.liangcang0224;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.atguigu.tiankuo.liangcang0224.fragment.GoodDetailsBean;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * 作者：田阔
 * 邮箱：1226147264@qq.com
 * Created by Administrator on 2017/7/22 0022.
 */
public class ShoppingCartAdapter extends RecyclerView.Adapter<ShoppingCartAdapter.MyViewHolder> {
    private final List<GoodDetailsBean.DataBean.ItemsBean.GoodsInfoBean> datas;
    private final Context context;

    public ShoppingCartAdapter(Context context, List<GoodDetailsBean.DataBean.ItemsBean.GoodsInfoBean> datas) {
        this.context = context;
        this.datas = datas;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_shop_cart, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        @InjectView(R.id.cb_shop)
        CheckBox cbShop;
        @InjectView(R.id.iv_shop)
        ImageView ivShop;
        @InjectView(R.id.iv_sub)
        ImageView ivSub;
        @InjectView(R.id.tv_value)
        TextView tvValue;
        @InjectView(R.id.iv_add)
        ImageView ivAdd;
        @InjectView(R.id.tv_good_content)
        TextView tvGoodContent;
        @InjectView(R.id.tv_desc_gov)
        TextView tvDescGov;
        @InjectView(R.id.tv_good_price)
        TextView tvGoodPrice;
        @InjectView(R.id.tv_number)
        TextView tvNumber;
        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);
        }
    }
}
