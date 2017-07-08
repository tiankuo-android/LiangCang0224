package com.atguigu.tiankuo.liangcang0224.utils;

/**
 * 作者：田阔
 * 邮箱：1226147264@qq.com
 * Created by Administrator on 2017/7/6 0006.
 */

public class UrlUtils {

    public static String DAREN_BASE_URL = "http://mobile.iliangcang.com/user/masterList?app_key=Android&count=18&";
    public static String DAREN_LAST_URL = "page=1&sig=79F01B94B8EBEFAC8EEB344EE2B20AA2%7C383889010803768&v=1.0";
    //达人首页
    public static String DAREN_MAIN_URL = DAREN_BASE_URL + DAREN_LAST_URL;
    //最多推荐
    public static String DAREN_MORE_URL = DAREN_BASE_URL + "orderby=goods_sum&" + DAREN_LAST_URL;
    //最受欢迎
    public static String DAREN_HOT_URL = DAREN_BASE_URL + "orderby=followers&" + DAREN_LAST_URL;
    //最新推荐
    public static String DAREN_NEW_URL = DAREN_BASE_URL + "orderby=action_time&" + DAREN_LAST_URL;
    //最新加入
    public static String DAREN_IN_URL = DAREN_BASE_URL + "orderby=reg_time&" + DAREN_LAST_URL;


    public static String BREAD_BASE_URL = "http://mobile.iliangcang.com/brand/brandList?app_key=Android&count=20&page=";
    public static String BREAD_LAST_URL= "&sig=430BD99E6C913B8B8C3ED109737ECF15%7C830952120106768&v=1.0";
}
