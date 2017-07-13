package com.atguigu.tiankuo.liangcang0224.fragment.shopfragment.fragment.homefragment;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.atguigu.tiankuo.liangcang0224.R;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.ButterKnife;
import butterknife.InjectView;
import okhttp3.Call;

public class HomeActivity extends AppCompatActivity {

    @InjectView(R.id.tv_date)
    TextView tvDate;
    @InjectView(R.id.iv_search)
    ImageView ivSearch;
    @InjectView(R.id.iv_daren_back)
    ImageView ivDarenBack;
    @InjectView(R.id.tv_mgz)
    TextView tvMgz;
    @InjectView(R.id.tv_fragment)
    TextView tvFragment;
    @InjectView(R.id.iv_shop)
    ImageView ivShop;
    @InjectView(R.id.iv_daren)
    ImageView ivDaren;
    @InjectView(R.id.iv_back)
    ImageView ivBack;
    @InjectView(R.id.ll_title)
    LinearLayout llTitle;
    @InjectView(R.id.web_view)
    WebView webView;
    @InjectView(R.id.activity_home)
    LinearLayout activityHome;
    private String url;
    private String named;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.inject(this);
        ivDarenBack.setVisibility(View.VISIBLE);

        Intent intent = getIntent();
        url = intent.getStringExtra("url");
        named = intent.getStringExtra("named");

        initData();
        initListener();
    }

    private void initData() {
        getDataFromNet(url);
    }

    private void getDataFromNet(String url) {
        OkHttpUtils.get().url(url).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                Log.e("TAG", "请求失败==Subject");
            }

            @Override
            public void onResponse(String response, int id) {
                Log.e("TAG", "请求成功==Subject");
                processData(response);
            }
        });
    }

    private void processData(String response) {
        tvFragment.setText(named);
        tvFragment.setTextSize(20);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setUseWideViewPort(true);

        //设置检索缓存的
        webSettings.setCacheMode(webSettings.LOAD_CACHE_ELSE_NETWORK);

        //设置不跳转到系统的浏览器
        webView.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    view.loadUrl(request.getUrl().toString());
                }
                return true;
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
            }
        });

        webView.loadUrl(url);

    }

    private void initListener() {
        ivDarenBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
