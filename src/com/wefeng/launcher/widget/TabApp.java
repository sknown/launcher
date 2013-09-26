package com.wefeng.launcher.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.wefeng.launcher.R;

/**
 * Created by Administrator on 13-9-26.
 */
public class TabApp extends FrameLayout {

    private Context mContext = null;
    private RelativeLayout mContainer = null;
    private LinearLayout mAppButton_01;
    private LinearLayout mAppButton_02;
    private LinearLayout mAppButton_03;
    private LinearLayout mAppButton_04;
    private LinearLayout mAppButton_05;
    private LinearLayout mAppButton_06;
    private LinearLayout mAppButton_07;

    private View.OnFocusChangeListener mFocusListener = new View.OnFocusChangeListener()
    {
        public void onFocusChange(View paramAnonymousView, boolean paramAnonymousBoolean)
        {
            if (paramAnonymousBoolean)
            {
                paramAnonymousView.bringToFront();
                TabApp.this.mContainer.invalidate();
            }
        }
    };

    public TabApp(Context context) {
        super(context);
        mContext = context;
        init();
    }

    public TabApp(Context paramContext, AttributeSet paramAttributeSet)
    {
        super(paramContext, paramAttributeSet);
        mContext = paramContext;
        init();
    }

    public TabApp(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
    {
        super(paramContext, paramAttributeSet, paramInt);
        this.mContext = paramContext;
        init();
    }

    private void init()
    {
        LayoutInflater lf = (LayoutInflater)mContext.getSystemService("layout_inflater");
        addView(lf.inflate(R.layout.view_tab_apps, null));

        mContainer = ((RelativeLayout)findViewById(R.id.tab_recommend_container));
        mAppButton_01 = ((LinearLayout)findViewById(R.id.tab_recommend_history_button));
        mAppButton_02 = ((LinearLayout)findViewById(R.id.tab_recommend_7_day_button));
        mAppButton_03 = ((LinearLayout)findViewById(R.id.tab_recommend_image_button_1));
        mAppButton_04 = ((LinearLayout)findViewById(R.id.tab_recommend_image_button_2));
        mAppButton_05 = ((LinearLayout)findViewById(R.id.tab_recommend_image_button_3));
        mAppButton_06 = ((LinearLayout)findViewById(R.id.tab_recommend_image_button_4));
        mAppButton_07 = ((LinearLayout)findViewById(R.id.tab_recommend_image_button_5));

        mAppButton_01.setOnFocusChangeListener(this.mFocusListener);
        mAppButton_02.setOnFocusChangeListener(this.mFocusListener);
        mAppButton_03.setOnFocusChangeListener(this.mFocusListener);
        mAppButton_04.setOnFocusChangeListener(this.mFocusListener);
        mAppButton_05.setOnFocusChangeListener(this.mFocusListener);
        mAppButton_06.setOnFocusChangeListener(this.mFocusListener);
        mAppButton_07.setOnFocusChangeListener(this.mFocusListener);

    }
}
