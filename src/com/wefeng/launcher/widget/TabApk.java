package com.wefeng.launcher.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.wefeng.launcher.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by Administrator on 13-9-26.
 */
public class TabApk extends FrameLayout {

    private Context mContext = null;
    private RelativeLayout mContainer = null;
    private TabApkItemView mImageButton_01;
    private TabApkItemView mImageButton_02;
    private TabApkItemView mImageButton_03;
    private TabApkItemView mImageButton_04;
    private TabApkItemView mImageButton_05;
    private TabApkItemView mImageButton_06;
    private TabApkItemView mImageButton_07;
    private TabApkItemView mImageButton_08;
    private TabApkItemView mImageButton_09;
    private TabApkItemView mImageButton_10;
    private TabApkItemView mImageButton_11;
    private TabApkItemView mImageButton_12;

    private ArrayList<TabApkItemView> mApkButton = new ArrayList<TabApkItemView>();

    private View.OnFocusChangeListener mFocusListener = new View.OnFocusChangeListener()
    {
        public void onFocusChange(View paramAnonymousView, boolean paramAnonymousBoolean)
        {
            if ((paramAnonymousView instanceof TabApkItemView))
                ((TabApkItemView)paramAnonymousView).onImageButtonFocusChanged(paramAnonymousBoolean);
            if (paramAnonymousBoolean)
            {
                paramAnonymousView.bringToFront();
                TabApk.this.mContainer.invalidate();
            }
        }
    };

    public TabApk(Context context) {
        super(context);
        mContext = context;
        init();
    }

    public TabApk(Context paramContext, AttributeSet paramAttributeSet)
    {
        super(paramContext, paramAttributeSet);
        this.mContext = paramContext;
        init();
    }

    public TabApk(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
    {
        super(paramContext, paramAttributeSet, paramInt);
        this.mContext = paramContext;
        init();
    }

    private void init()
    {
        LayoutInflater lf = (LayoutInflater)mContext.getSystemService("layout_inflater");
        addView(lf.inflate(R.layout.view_tab_apk, null));

        mContainer = ((RelativeLayout)findViewById(R.id.tab_channel_container));
        mImageButton_01 = ((TabApkItemView)findViewById(R.id.tab_channel_button_1));
        mImageButton_02 = ((TabApkItemView)findViewById(R.id.tab_channel_button_2));
        mImageButton_03 = ((TabApkItemView)findViewById(R.id.tab_channel_button_3));
        mImageButton_04 = ((TabApkItemView)findViewById(R.id.tab_channel_button_4));
        mImageButton_05 = ((TabApkItemView)findViewById(R.id.tab_channel_button_5));
        mImageButton_06 = ((TabApkItemView)findViewById(R.id.tab_channel_button_6));
        mImageButton_07 = ((TabApkItemView)findViewById(R.id.tab_channel_button_7));
        mImageButton_08 = ((TabApkItemView)findViewById(R.id.tab_channel_button_8));
        mImageButton_09 = ((TabApkItemView)findViewById(R.id.tab_channel_button_9));
        mImageButton_10 = ((TabApkItemView)findViewById(R.id.tab_channel_button_10));
        mImageButton_11 = ((TabApkItemView)findViewById(R.id.tab_channel_button_11));
        mImageButton_12 = ((TabApkItemView)findViewById(R.id.tab_channel_button_12));

        mApkButton.add(mImageButton_01);
        mApkButton.add(mImageButton_02);
        mApkButton.add(mImageButton_03);
        mApkButton.add(mImageButton_04);
        mApkButton.add(mImageButton_05);
        mApkButton.add(mImageButton_06);
        mApkButton.add(mImageButton_07);
        mApkButton.add(mImageButton_08);
        mApkButton.add(mImageButton_09);
        mApkButton.add(mImageButton_10);
        mApkButton.add(mImageButton_11);

        mImageButton_01.setOnFocusChangeListener(this.mFocusListener);
        mImageButton_02.setOnFocusChangeListener(this.mFocusListener);
        mImageButton_03.setOnFocusChangeListener(this.mFocusListener);
        mImageButton_04.setOnFocusChangeListener(this.mFocusListener);
        mImageButton_05.setOnFocusChangeListener(this.mFocusListener);
        mImageButton_06.setOnFocusChangeListener(this.mFocusListener);
        mImageButton_07.setOnFocusChangeListener(this.mFocusListener);
        mImageButton_08.setOnFocusChangeListener(this.mFocusListener);
        mImageButton_09.setOnFocusChangeListener(this.mFocusListener);
        mImageButton_10.setOnFocusChangeListener(this.mFocusListener);
        mImageButton_11.setOnFocusChangeListener(this.mFocusListener);
        mImageButton_12.setOnFocusChangeListener(this.mFocusListener);


    }

    public void initImage()
    {
        this.mImageButton_01.setBackgroud(R.drawable.tab_channel_image_variety );
        this.mImageButton_02.setBackgroud(R.drawable.tab_channel_image_sports);
        this.mImageButton_03.setBackgroud(R.drawable.tab_channel_image_documentaryfilm);
        this.mImageButton_04.setBackgroud(R.drawable.tab_channel_image_education);
        this.mImageButton_05.setBackgroud(R.drawable.tab_channel_image_entertainment);
        this.mImageButton_06.setBackgroud(R.drawable.tab_channel_image_episode);
        this.mImageButton_07.setBackgroud(R.drawable.tab_channel_image_fashion);
        this.mImageButton_08.setBackgroud(R.drawable.tab_channel_image_flower);
        this.mImageButton_09.setBackgroud(R.drawable.tab_channel_image_micro_movie);
        this.mImageButton_10.setBackgroud(R.drawable.tab_channel_image_movie);
        this.mImageButton_11.setBackgroud(R.drawable.tab_channel_image_music);
        this.mImageButton_12.setBackgroud(R.drawable.tab_channel_image_qiyi);
        this.mImageButton_01.setHighLightImageRes(R.drawable.tab_recommend_highlight_270_270);
        this.mImageButton_02.setHighLightImageRes(R.drawable.tab_recommend_highlight_270_270);
        this.mImageButton_03.setHighLightImageRes(R.drawable.tab_recommend_highlight_270_270);
        this.mImageButton_04.setHighLightImageRes(R.drawable.tab_recommend_highlight_270_270);
        this.mImageButton_05.setHighLightImageRes(R.drawable.tab_recommend_highlight_270_270);
        this.mImageButton_06.setHighLightImageRes(R.drawable.tab_recommend_highlight_270_270);
        this.mImageButton_07.setHighLightImageRes(R.drawable.tab_recommend_highlight_270_270);
        this.mImageButton_08.setHighLightImageRes(R.drawable.tab_recommend_highlight_270_270);
        this.mImageButton_09.setHighLightImageRes(R.drawable.tab_recommend_highlight_270_270);
        this.mImageButton_10.setHighLightImageRes(R.drawable.tab_recommend_highlight_270_270);
        this.mImageButton_11.setHighLightImageRes(R.drawable.tab_recommend_highlight_270_270);
        this.mImageButton_12.setHighLightImageRes(R.drawable.tab_recommend_highlight_270_270);
    }

    public void setApk(HashMap<String,Drawable> apk)
    {
        int BUTTON_COUNT = 11;
        int i = BUTTON_COUNT-1;

        Iterator iterator = apk.keySet().iterator();
        while(iterator.hasNext() && i>=0)
        {
            String label = (String)iterator.next();
            Drawable icon = apk.get(label);

            TabApkItemView view = mApkButton.get(i);
            view.setApkImage(icon);
            view.setTitle(label);
            i--;
        }
    }
}
