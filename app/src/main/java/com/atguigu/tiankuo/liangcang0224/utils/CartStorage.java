package com.atguigu.tiankuo.liangcang0224.utils;

import android.content.Context;
import android.text.TextUtils;
import android.util.SparseArray;

import com.atguigu.tiankuo.liangcang0224.fragment.goodsdetail.GoodDetailsBean;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：田阔
 * 邮箱：1226147264@qq.com
 * Created by Administrator on 2017/7/24 0024.
 */

public class CartStorage {
    private static final String JSON_CART = "json_cart";
    private static  CartStorage cartStorage;
    private final Context mContext;
    private SparseArray<GoodDetailsBean> sparseArray;

    private CartStorage(Context context) {
        this.mContext = context;
        sparseArray = new SparseArray<>();
        listToSpare();
    }

    /**
     * List数据转SparseArray
     */
    private void listToSpare() {
        List<GoodDetailsBean> list= getAllData();
        if(list != null  && list.size()  > 0){
            for (int i = 0 ;i < list.size(); i++){
                GoodDetailsBean GoodDetailsBean = list.get(i);
                sparseArray.put(Integer.valueOf(GoodDetailsBean.getVersion()),GoodDetailsBean);
            }
        }
    }

    /**
     * 得到所有数据
     * @return
     */
    public List<GoodDetailsBean> getAllData() {
        return getDataFromLocal();
    }

    /**
     * 得到本地保持的数据
     * @return
     */
    private List<GoodDetailsBean> getDataFromLocal() {
        List<GoodDetailsBean> list  = new ArrayList<>();
        //得到保持的数据
        String saveJson = CacheUtils.getString(mContext,JSON_CART);
        if(!TextUtils.isEmpty(saveJson)){
            list = new Gson().fromJson(saveJson,new TypeToken<List<GoodDetailsBean>>(){}.getType());
        }
        return list;
    }

    /**
     * 得到CartStorage实例
     * @param context
     * @return
     */
    public static CartStorage getInstance(Context context){

        if(cartStorage == null){
            synchronized (CartStorage.class){
                if(cartStorage == null){
                    cartStorage = new CartStorage(context);
                }
            }
        }
        return  cartStorage;
    }

    /**
     * 增加数据
     * @param GoodDetailsBean
     */
    public void addData(GoodDetailsBean GoodDetailsBean){
        //1.增加数据
        GoodDetailsBean temp = sparseArray.get(Integer.valueOf(GoodDetailsBean.getVersion()));
        if(temp != null){
            //存在
            temp.getData().getItems().setNumber(GoodDetailsBean.getData().getItems().getNumber()+temp.getData().getItems().getNumber());
        }else {
            temp = GoodDetailsBean;
        }

        //添加
        sparseArray.put(temp.getVersion(),temp);


        //2.保持数据到本地
        commit();
    }

    /**
     * 修改数据
     * @param GoodDetailsBean
     */
    public void updateData(GoodDetailsBean GoodDetailsBean){
        //1.修改数据

        sparseArray.put(GoodDetailsBean.getVersion(),GoodDetailsBean);

        //2.保持数据到本地
        commit();
    }

    /**
     * 删除数据
     * @param GoodDetailsBean
     */
    public void deleteData(GoodDetailsBean GoodDetailsBean){
        //1.删除数据
        sparseArray.delete(GoodDetailsBean.getVersion());

        //2.保持数据到本地
        commit();
    }

    /**
     * 保持数据
     */
    private void commit() {
        List<GoodDetailsBean> list = sparseArrayToList();
        String json = new Gson().toJson(list);
        CacheUtils.putString(mContext,JSON_CART,json);

    }

    /**
     * sparseArray转List数据
     * @return
     */
    private List<GoodDetailsBean> sparseArrayToList() {
        List<GoodDetailsBean> list = new ArrayList<>();
        if (sparseArray != null && sparseArray.size() > 0) {
            for (int i = 0; i < sparseArray.size(); i++) {
                GoodDetailsBean shoppingCart = sparseArray.valueAt(i);
                list.add(shoppingCart);
            }
        }
        return list;
    }

}
