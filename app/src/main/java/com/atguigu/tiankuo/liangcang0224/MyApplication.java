package com.atguigu.tiankuo.liangcang0224;

import android.app.Application;

import org.xutils.x;

import cn.jpush.android.api.JPushInterface;
import cn.sharesdk.framework.ShareSDK;
import cn.smssdk.SMSSDK;


/**
 * 作者：田阔
 * 邮箱：1226147264@qq.com
 * Created by Administrator on 2017/7/7 0007.
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        x.Ext.setDebug(org.xutils.BuildConfig.DEBUG);
//        SpeechUtility.createUtility(this, SpeechConstant.APPID +"=5928ea01");
        SMSSDK.initSDK(MyApplication.this, "1f88046b6dde0", "18984c4e84844cd674edb07e57d68981");
        ShareSDK.initSDK(this);

        JPushInterface.setDebugMode(true); 	// 设置开启日志,发布时请关闭日志
        JPushInterface.init(this);     		// 初始化 JPush
    }
}
