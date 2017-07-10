package com.atguigu.tiankuo.liangcang0224.fragment.darenfragment;

import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.atguigu.tiankuo.liangcang0224.R;
import com.atguigu.tiankuo.liangcang0224.base.BaseFragment;
import com.atguigu.tiankuo.liangcang0224.fragment.darenfragment.bean.DarenBean;
import com.atguigu.tiankuo.liangcang0224.utils.UrlUtils;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import okhttp3.Call;

/**
 * 作者：田阔
 * 邮箱：1226147264@qq.com
 * Created by Administrator on 2017/7/5 0005.
 */

public class DarenFragment extends BaseFragment {
    @InjectView(R.id.iv_search)
    ImageView ivSearch;
    @InjectView(R.id.tv_fragment)
    TextView tvFragment;
    @InjectView(R.id.iv_daren)
    ImageView ivDaren;
    @InjectView(R.id.recyclerview)
    RecyclerView recyclerview;
    @InjectView(R.id.iv_back)
    ImageView ivBack;
    private String url;
    private List<DarenBean.DataBean.ItemsBean> datas;
    private RecyclerView.Adapter adapter;
    private PopupWindow popupWindow;

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment_daren, null);
        ButterKnife.inject(this, view);
        ivSearch.setVisibility(View.VISIBLE);
        tvFragment.setText("达人");
        ivDaren.setVisibility(View.VISIBLE);
        ivBack.setVisibility(View.GONE);
        return view;
    }

    @Override
    public void initData() {
        super.initData();
        url = UrlUtils.DAREN_MAIN_URL;
        getDataFromNet(url);
    }

    private void getDataFromNet(String url) {
        OkHttpUtils.get().url(url).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                Log.e("TAG", "请求失败==Daren");
            }

            @Override
            public void onResponse(String response, int id) {
                Log.e("TAG", "请求成功==Daren");
                processData(response);
            }
        });
    }

    private void processData(String response) {

        DarenBean bean = new Gson().fromJson(response, DarenBean.class);
//        Log.e("TAG",bean.getData().getItems().get(0).getNickname()+ "");
        datas = bean.getData().getItems();
        if (datas != null && datas.size() > 0) {
            //有数据
            adapter = new MyDarenAdapter(getActivity(), datas);
            recyclerview.setAdapter(adapter);
            recyclerview.setLayoutManager(new GridLayoutManager(mContext, 3, GridLayoutManager.VERTICAL, false));
        } else {
            //没有数据
            Log.e("TAG", "没有数据");
        }
    }

    @Override
    public void initListener() {

//        super.initListener();

        ivDaren.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopupWindow(view);
            }
        });

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ivDaren.setVisibility(View.VISIBLE);
                ivBack.setVisibility(View.GONE);
                popupWindow.dismiss();
            }
        });
    }

    private void showPopupWindow(View view) {
        // 一个自定义的布局，作为显示的内容
        View contentView = LayoutInflater.from(mContext).inflate(
                R.layout.popup_view, null);

        ivSearch.setVisibility(View.VISIBLE);
        tvFragment.setText("达人");
        ivDaren.setVisibility(View.GONE);
        ivBack.setVisibility(View.VISIBLE);

        //设置按钮的点击事件
        TextView tvDefault = (TextView) contentView.findViewById(R.id.tv_default);
        TextView tvMore = (TextView) contentView.findViewById(R.id.tv_more);
        TextView tvHot = (TextView) contentView.findViewById(R.id.tv_hot);
        TextView tvNew = (TextView) contentView.findViewById(R.id.tv_new);
        TextView tvPutin = (TextView) contentView.findViewById(R.id.tv_putin);
        Button btn = (Button) contentView.findViewById(R.id.btn_background);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ivDaren.setVisibility(View.VISIBLE);
                ivBack.setVisibility(View.GONE);
                popupWindow.dismiss();
            }
        });



        tvDefault.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                url = UrlUtils.DAREN_MAIN_URL;
                getDataFromNet(url);
                ivDaren.setVisibility(View.VISIBLE);
                ivBack.setVisibility(View.GONE);
                popupWindow.dismiss();
            }
        });
        tvMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                url = UrlUtils.DAREN_MORE_URL;
                getDataFromNet(url);
                ivDaren.setVisibility(View.VISIBLE);
                ivBack.setVisibility(View.GONE);
                popupWindow.dismiss();
            }
        });
        tvHot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                url = UrlUtils.DAREN_HOT_URL;
                getDataFromNet(url);
                ivDaren.setVisibility(View.VISIBLE);
                ivBack.setVisibility(View.GONE);
                popupWindow.dismiss();
            }
        });
        tvNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                url = UrlUtils.DAREN_NEW_URL;
                getDataFromNet(url);
                ivDaren.setVisibility(View.VISIBLE);
                ivBack.setVisibility(View.GONE);
                popupWindow.dismiss();
            }
        });
        tvPutin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                url = UrlUtils.DAREN_IN_URL;
                getDataFromNet(url);
                ivDaren.setVisibility(View.VISIBLE);
                ivBack.setVisibility(View.GONE);
                popupWindow.dismiss();
            }
        });
        if (popupWindow == null) {
            popupWindow = new PopupWindow(contentView, RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT, true);
        }

        popupWindow.setTouchable(true);
        popupWindow.setTouchInterceptor(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                Log.i("mengdd", "onTouch : ");

                return false;
                // 这里如果返回true的话，touch事件将被拦截
                // 拦截后 PopupWindow的onTouchEvent不被调用，这样点击外部区域无法dismiss
            }
        });

        // 如果不设置PopupWindow的背景，无论是点击外部区域还是Back键都无法dismiss弹框
        // 我觉得这里是API的一个bug
        popupWindow.setBackgroundDrawable(new ColorDrawable(0x00000000));


        // 设置好参数之后再show
        popupWindow.showAsDropDown(view);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }


}
