package com.atguigu.tiankuo.liangcang0224.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * 作者：田阔
 * 邮箱：1226147264@qq.com
 * Created by Administrator on 2017/7/24 0024.
 */

public class CacheUtils {
    /**
     * 得到String类型的数据
     * @param context
     * @param key
     * @return
     */
    public static String getString(Context context, String key) {
        SharedPreferences sp = context.getSharedPreferences("atguigu",Context.MODE_PRIVATE);
        return sp.getString(key,"");
    }


    /**
     * 保持文本数据
     * @param context
     * @param key
     * @param value
     */
    public static void   putString(Context context, String key,String value) {
        SharedPreferences sp = context.getSharedPreferences("atguigu",Context.MODE_PRIVATE);
        sp.edit().putString(key,value).commit();
    }

}
