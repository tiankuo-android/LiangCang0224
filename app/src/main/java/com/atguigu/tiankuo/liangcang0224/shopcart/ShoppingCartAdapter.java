package com.atguigu.tiankuo.liangcang0224.shopcart;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.atguigu.tiankuo.liangcang0224.R;
import com.atguigu.tiankuo.liangcang0224.fragment.goodsdetail.GoodDetailsBean;
import com.atguigu.tiankuo.liangcang0224.utils.AddSubView;
import com.atguigu.tiankuo.liangcang0224.utils.CartStorage;
import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * 作者：田阔
 * 邮箱：1226147264@qq.com
 * Created by Administrator on 2017/7/22 0022.
 */
public class ShoppingCartAdapter extends RecyclerView.Adapter<ShoppingCartAdapter.MyViewHolder> {
    private final Context context;
    private final List<GoodDetailsBean> datas;
    private final CheckBox checkboxAll;
    private final TextView tvShopcartTotal;


    public ShoppingCartAdapter(Context context, List<GoodDetailsBean> datas, CheckBox checkboxAll, TextView tvShopcartTotal) {
        this.context = context;
        this.datas = datas;
        this.checkboxAll = checkboxAll;
        this.tvShopcartTotal = tvShopcartTotal;

        showTotalPrice();
        checkAll();
    }

    private void checkAll() {
        if (datas != null && datas.size() > 0) {
            int number = 0;

            for (int i = 0; i < datas.size(); i++) {
                GoodDetailsBean.DataBean.ItemsBean itemsBean = datas.get(i).getData().getItems();
                //只要有一个不选中就设置非全选
                if (!itemsBean.isCheck()) {
                    checkboxAll.setChecked(false);
                } else {
                    number++;
                }
            }
            if (number == datas.size()) {
                checkboxAll.setChecked(true);
            }
        } else {
            //没有数据
            checkboxAll.setChecked(false);
        }
    }

    public void showTotalPrice() {

        tvShopcartTotal.setText("￥" + getTotalPrice());
    }

    private double getTotalPrice() {
        double result = 0;
        if (datas != null && datas.size() > 0) {
            for (int i = 0; i < datas.size(); i++) {
                GoodDetailsBean.DataBean.ItemsBean itemsBean = datas.get(i).getData().getItems();
                if (itemsBean.isCheck()) {
                    result = result + itemsBean.getNumber() * Double.parseDouble(itemsBean.getPrice());
                }
            }
        }
        return result;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_shop_cart, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final MyViewHolder viewHolder = (MyViewHolder) holder;
        //1.更加位置得到数据
        final GoodDetailsBean purchaseBean = datas.get(position);
        final GoodDetailsBean.DataBean.ItemsBean itemsBean = purchaseBean.getData().getItems();
        //2.绑定数据
        viewHolder.checkboxItemAll.setChecked(itemsBean.isCheck());
        //图片
        Glide.with(context).load(itemsBean.getGoods_image()).into(viewHolder.ivItem);

        viewHolder.tvItemName1.setText(itemsBean.getGoods_name());
        viewHolder.tvItemName2.setText(itemsBean.getGoods_name());
        //设置价格
        viewHolder.tvItemPrice1.setText("￥"+itemsBean.getPrice());
        viewHolder.tvItemPrice2.setText("￥"+itemsBean.getPrice());

        viewHolder.tvItemNumber.setText("x"+itemsBean.getNumber());

        viewHolder.itemAddSubView.setValue(itemsBean.getNumber());
        viewHolder.itemAddSubView.setMinvalue(1);
        //库存
        viewHolder.itemAddSubView.setMaxvalue(Integer.parseInt(itemsBean.getSku_inv().get(0).getAmount()));

        viewHolder.itemAddSubView.setOnNumberChangeListener(new AddSubView.OnNumberChangeListener() {
            @Override
            public void numberChange(int value) {
                //把Bean对象更新一下
                itemsBean.setNumber(value);
                //更新存储到本地或者服务器上
                CartStorage.getInstance(context).updateData(purchaseBean);

                notifyItemChanged(position);

                showTotalPrice();

            }

        });

        viewHolder.checkboxItemAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //状态取反
                itemsBean.setCheck(!itemsBean.isCheck());

                //刷新适配器
                notifyItemChanged(position);

                //重新显示总价格
                showTotalPrice();

                //校验是否全选
                checkAll();
            }
        });


        if (itemsBean.isDelete()) {

            viewHolder.llItem1.setVisibility(View.GONE);
            viewHolder.llItem2.setVisibility(View.VISIBLE);
            viewHolder.tvItemDelete.setVisibility(View.VISIBLE);

        } else {

            viewHolder.llItem1.setVisibility(View.VISIBLE);
            viewHolder.llItem2.setVisibility(View.GONE);
            viewHolder.tvItemDelete.setVisibility(View.GONE);
        }


        //从购物车删除
        viewHolder.tvItemDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                datas.remove(purchaseBean);
                //同步到本地
                CartStorage.getInstance(context).deleteData(purchaseBean);
                notifyItemRemoved(position);
                checkAll();
                showTotalPrice();
            }
        });
    }

    public void showDelete(boolean isShow) {
        for (int i = 0; i < datas.size(); i++) {
            GoodDetailsBean.DataBean.ItemsBean itemsBean = datas.get(i).getData().getItems();
            itemsBean.setDelete(isShow);
            notifyItemChanged(i);
        }
    }

    public void checkAll_none(boolean isCheck) {
        if (datas != null && datas.size() > 0) {
            int number = 0;

            for (int i = 0; i < datas.size(); i++) {
                GoodDetailsBean.DataBean.ItemsBean itemsBean = datas.get(i).getData().getItems();
                //只要有一个不选中就设置非全选
                itemsBean.setCheck(isCheck);
                notifyItemChanged(i);
            }
        } else {
            checkboxAll.setChecked(false);
        }

    }

    @Override
    public int getItemCount() {
        return datas == null ? 0 : datas.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        @InjectView(R.id.checkbox_item_all)
        CheckBox checkboxItemAll;
        @InjectView(R.id.iv_item)
        ImageView ivItem;
        @InjectView(R.id.tv_item_name1)
        TextView tvItemName1;
        @InjectView(R.id.tv_item_price1)
        TextView tvItemPrice1;
        @InjectView(R.id.tv_item_number)
        TextView tvItemNumber;
        @InjectView(R.id.ll_item1)
        LinearLayout llItem1;
        @InjectView(R.id.item_addSubView)
        AddSubView itemAddSubView;
        @InjectView(R.id.tv_item_name2)
        TextView tvItemName2;
        @InjectView(R.id.tv_item_price2)
        TextView tvItemPrice2;
        @InjectView(R.id.ll_item2)
        LinearLayout llItem2;
        @InjectView(R.id.tv_item_delete)
        TextView tvItemDelete;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);
        }
    }
}
