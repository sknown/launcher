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
public class TabTop extends FrameLayout {

    private Context mContext = null;
    private RelativeLayout mContainer = null;
    private TabTopItemView mTopButton_01;
    private TabTopItemView mTopButton_02;
    private TabTopItemView mTopButton_03;
    private TabTopItemView mTopButton_04;
    private TabTopItemView mTopButton_05;
    private TabTopItemView mTopButton_06;

    private OnFocusChangeListener mFocusListener = new OnFocusChangeListener()
    {
        public void onFocusChange(View paramAnonymousView, boolean paramAnonymousBoolean)
        {
            if (paramAnonymousBoolean)
            {
                paramAnonymousView.bringToFront();
                TabTop.this.mContainer.invalidate();
            }
        }
    };

    public TabTop(Context context) {
        super(context);
        mContext = context;
        init();
    }

    public TabTop(Context paramContext, AttributeSet paramAttributeSet)
    {
        super(paramContext, paramAttributeSet);
        mContext = paramContext;
        init();
    }

    public TabTop(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
    {
        super(paramContext, paramAttributeSet, paramInt);
        this.mContext = paramContext;
        init();
    }

    private void init()
    {
        LayoutInflater lf = (LayoutInflater)mContext.getSystemService("layout_inflater");
        addView(lf.inflate(R.layout.view_tab_tops, null));

        mContainer = ((RelativeLayout)findViewById(R.id.tab_tops_container));
        mTopButton_01 = ((TabTopItemView)findViewById(R.id.tab_tops_hot_button));
        mTopButton_02 = ((TabTopItemView)findViewById(R.id.tab_tops_image_button_1));
        mTopButton_03 = ((TabTopItemView)findViewById(R.id.tab_tops_image_button_2));
        mTopButton_04 = ((TabTopItemView)findViewById(R.id.tab_tops_haoping_button));
        mTopButton_05 = ((TabTopItemView)findViewById(R.id.tab_tops_image_button_3));
        mTopButton_06 = ((TabTopItemView)findViewById(R.id.tab_tops_image_button_4));

        mTopButton_01.setOnFocusChangeListener(this.mFocusListener);
        mTopButton_02.setOnFocusChangeListener(this.mFocusListener);
        mTopButton_03.setOnFocusChangeListener(this.mFocusListener);
        mTopButton_04.setOnFocusChangeListener(this.mFocusListener);
        mTopButton_05.setOnFocusChangeListener(this.mFocusListener);
        mTopButton_06.setOnFocusChangeListener(this.mFocusListener);

    }

    public void initImage()
    {
        this.mTopButton_01.setTopImageRes(R.drawable.tab_top_meishi);
        this.mTopButton_02.setTopImageRes(R.drawable.tab_top_sina);
        this.mTopButton_03.setTopImageRes(R.drawable.tab_top_jd);
        this.mTopButton_04.setTopImageRes(R.drawable.tab_top_gupiao);
        this.mTopButton_05.setTopImageRes(R.drawable.tab_top_dangdang);
        this.mTopButton_06.setTopImageRes(R.drawable.tab_top_travell);

//        this.mTopButton_01.setHighLightImageRes(R.drawable.tab_recommend_highlight_270_270);
//        this.mTopButton_02.setHighLightImageRes(R.drawable.tab_recommend_highlight_270_270);
//        this.mTopButton_03.setHighLightImageRes(R.drawable.tab_recommend_highlight_270_270);
//        this.mTopButton_04.setHighLightImageRes(R.drawable.tab_recommend_highlight_270_270);
//        this.mTopButton_05.setHighLightImageRes(R.drawable.tab_recommend_highlight_270_270);
//        this.mTopButton_06.setHighLightImageRes(R.drawable.tab_recommend_highlight_270_270);

        mTopButton_01.setTopTitleText(R.string.dou_guo_mei_shi);
        mTopButton_02.setTopTitleText(R.string.sina_news);
        mTopButton_03.setTopTitleText(R.string.jd);
        mTopButton_04.setTopTitleText(R.string.gu_piao);
        mTopButton_05.setTopTitleText(R.string.dang_dang);
        mTopButton_06.setTopTitleText(R.string.travel);
    }
}
