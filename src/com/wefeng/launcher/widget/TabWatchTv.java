package com.wefeng.launcher.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.wefeng.launcher.R;

/**
 * Created by Administrator on 13-9-26.
 */
public class TabWatchTv extends FrameLayout {

    private Context mContext = null;
    private RelativeLayout mContainer = null;
    private TabWatchTvItemView mAppButton_01;
    private TabWatchTvItemView mAppButton_02;
    private TabWatchTvItemView mAppButton_03;
    private TabWatchTvItemView mAppButton_04;
    private TabWatchTvItemView mAppButton_05;
    private TabWatchTvItemView mAppButton_06;
    private TabWatchTvItemView mAppButton_07;

    private View.OnFocusChangeListener mFocusListener = new View.OnFocusChangeListener()
    {
        public void onFocusChange(View paramAnonymousView, boolean paramAnonymousBoolean)
        {
            if (paramAnonymousBoolean)
            {
                paramAnonymousView.bringToFront();
                TabWatchTv.this.mContainer.invalidate();
            }
        }
    };

    public TabWatchTv(Context context) {
        super(context);
        mContext = context;
        init();

        mAppButton_03.requestFocus();
    }

    public TabWatchTv(Context paramContext, AttributeSet paramAttributeSet)
    {
        super(paramContext, paramAttributeSet);
        mContext = paramContext;
        init();
    }

    public TabWatchTv(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
    {
        super(paramContext, paramAttributeSet, paramInt);
        this.mContext = paramContext;
        init();
    }

    private void init()
    {
        LayoutInflater lf = (LayoutInflater)mContext.getSystemService("layout_inflater");
        addView(lf.inflate(R.layout.view_tab_watchtv, null));

        mContainer = ((RelativeLayout)findViewById(R.id.tab_recommend_container));
        mAppButton_01 = ((TabWatchTvItemView)findViewById(R.id.tab_recommend_history_button));
        mAppButton_02 = ((TabWatchTvItemView)findViewById(R.id.tab_recommend_7_day_button));
        mAppButton_03 = ((TabWatchTvItemView)findViewById(R.id.tab_recommend_image_button_1));
        mAppButton_04 = ((TabWatchTvItemView)findViewById(R.id.tab_recommend_image_button_2));
        mAppButton_05 = ((TabWatchTvItemView)findViewById(R.id.tab_recommend_image_button_3));
        mAppButton_06 = ((TabWatchTvItemView)findViewById(R.id.tab_recommend_image_button_4));
        mAppButton_07 = ((TabWatchTvItemView)findViewById(R.id.tab_recommend_image_button_5));

        mAppButton_01.setOnFocusChangeListener(this.mFocusListener);
        mAppButton_02.setOnFocusChangeListener(this.mFocusListener);
        mAppButton_03.setOnFocusChangeListener(this.mFocusListener);
        mAppButton_04.setOnFocusChangeListener(this.mFocusListener);
        mAppButton_05.setOnFocusChangeListener(this.mFocusListener);
        mAppButton_06.setOnFocusChangeListener(this.mFocusListener);
        mAppButton_07.setOnFocusChangeListener(this.mFocusListener);

    }

    public void initImage()
    {
        this.mAppButton_01.setWatchTvImageRes(R.drawable.tab_app_0000_fun_time );
        this.mAppButton_02.setWatchTvImageRes(R.drawable.tab_app_0001_qrcode);
        this.mAppButton_03.setWatchTvImageRes(R.drawable.taijie_0000_watch_tv);
        this.mAppButton_04.setWatchTvImageRes(R.drawable.tab_recommend_image_2_temp);
        this.mAppButton_05.setWatchTvImageRes(R.drawable.tab_recommend_image_3_temp);
        this.mAppButton_06.setWatchTvImageRes(R.drawable.tab_recommend_image_4_temp);
        this.mAppButton_07.setWatchTvImageRes(R.drawable.tab_recommend_image_5_temp);

        mAppButton_01.setWatchTvTitleText(R.string.fun_time);
        mAppButton_02.setWatchTvTitleText(R.string.multi_screen);
        mAppButton_03.setWatchTvTitleText(R.string.watch_tv);
        mAppButton_04.setWatchTvTitleText(R.string.ifeng_new);
        mAppButton_05.setWatchTvTitleText(R.string.cartoon);
        mAppButton_06.setWatchTvTitleText(R.string.movie_and_soap_drama);
        mAppButton_07.setWatchTvTitleText(R.string.education_online);
    }
}
