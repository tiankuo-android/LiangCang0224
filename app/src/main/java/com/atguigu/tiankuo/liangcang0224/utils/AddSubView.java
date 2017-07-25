package com.atguigu.tiankuo.liangcang0224.utils;

import android.content.Context;
import android.support.v7.widget.TintTypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.atguigu.tiankuo.liangcang0224.R;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * 作者：田阔
 * 邮箱：1226147264@qq.com
 * Created by Administrator on 2017/7/25 0025.
 */

public class AddSubView extends LinearLayout {
    private final Context context;
    @InjectView(R.id.tv_sub)
    TextView tvSub;
    @InjectView(R.id.tv_value)
    TextView tvValue;
    @InjectView(R.id.tv_add)
    TextView tvAdd;

    private int value = 1;
    private int maxvalue = 10;
    private int minvalue = 1;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
        tvValue.setText(value + "");
    }

    public int getMaxvalue() {
        return maxvalue;
    }

    public void setMaxvalue(int maxvalue) {
        this.maxvalue = maxvalue;
    }

    public int getMinvalue() {
        return minvalue;
    }

    public void setMinvalue(int minvalue) {
        this.minvalue = minvalue;
    }

    public AddSubView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        ButterKnife.inject(this, View.inflate(context, R.layout.add_sub_view, this));

        if (attrs != null) {
            //取出属性
            TintTypedArray tintTypedArray = TintTypedArray.obtainStyledAttributes(context, attrs, R.styleable.AddSubView);
            int value = tintTypedArray.getInt(R.styleable.AddSubView_value, 0);
            if (value > 0) {
                setValue(value);
            }
            int minValue = tintTypedArray.getInt(R.styleable.AddSubView_minValue, 0);
            if (minvalue > 0) {
                setMinvalue(minValue);
            }
            int maxValue = tintTypedArray.getInt(R.styleable.AddSubView_maxValue, 0);
            if (maxvalue > 0) {
                setMaxvalue(maxValue);
            }
        }
    }

    @OnClick({R.id.tv_sub, R.id.tv_add})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_sub:
                if (value > minvalue) {
                    value--;
                }
                tvValue.setText(value + "");
                break;
            case R.id.tv_add:
                if (value < maxvalue) {
                    value++;
                }
                tvValue.setText(value + "");
                break;
        }


        if (changeListener != null) {
            changeListener.numberChange(value);
        }
    }


    public interface OnNumberChangeListener {
        public void numberChange(int value);
    }

    private OnNumberChangeListener changeListener;

    public void setOnNumberChangeListener(OnNumberChangeListener changeListener) {
        this.changeListener = changeListener;
    }
}
