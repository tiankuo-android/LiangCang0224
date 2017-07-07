package com.atguigu.tiankuo.liangcang0224.utils;

/**
 * 作者：田阔
 * 邮箱：1226147264@qq.com
 * Created by Administrator on 2017/7/6 0006.
 */

public class DarenUtils {

    public static String BASE_URL = "http://mobile.iliangcang.com/user/masterList?app_key=Android&count=18&";
    public static String LAST_URL = "page=1&sig=79F01B94B8EBEFAC8EEB344EE2B20AA2%7C383889010803768&v=1.0";
    //达人首页
    public static String MAIN_URL = BASE_URL + LAST_URL;
    //最多推荐
    public static String MORE_URL = BASE_URL + "orderby=goods_sum&" + LAST_URL;
    //最受欢迎
    public static String HOT_URL = BASE_URL + "orderby=followers&" + LAST_URL;
    //最新推荐
    public static String NEW_URL = BASE_URL + "orderby=action_time&" + LAST_URL;
    //最新加入
    public static String IN_URL = BASE_URL + "orderby=reg_time&" + LAST_URL;
}
