package com.wefeng.launcher.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.wefeng.launcher.R;

/**
 * Created by Administrator on 13-9-26.
 */
public class TabChannels extends FrameLayout {

    private Context mContext = null;
    private RelativeLayout mContainer = null;
    private TabChannelsItemView mImageButton_01;
    private TabChannelsItemView mImageButton_02;
    private TabChannelsItemView mImageButton_03;
    private TabChannelsItemView mImageButton_04;
    private TabChannelsItemView mImageButton_05;
    private TabChannelsItemView mImageButton_06;
    private TabChannelsItemView mImageButton_07;
    private TabChannelsItemView mImageButton_08;
    private TabChannelsItemView mImageButton_09;
    private TabChannelsItemView mImageButton_10;
    private TabChannelsItemView mImageButton_11;
    private TabChannelsItemView mImageButton_12;

    private View.OnFocusChangeListener mFocusListener = new View.OnFocusChangeListener()
    {
        public void onFocusChange(View paramAnonymousView, boolean paramAnonymousBoolean)
        {
            if ((paramAnonymousView instanceof TabChannelsItemView))
                ((TabChannelsItemView)paramAnonymousView).onImageButtonFocusChanged(paramAnonymousBoolean);
            if (paramAnonymousBoolean)
            {
                paramAnonymousView.bringToFront();
                TabChannels.this.mContainer.invalidate();
            }
        }
    };

    public TabChannels(Context context) {
        super(context);
        mContext = context;
        init();
    }

    public TabChannels(Context paramContext, AttributeSet paramAttributeSet)
    {
        super(paramContext, paramAttributeSet);
        this.mContext = paramContext;
        init();
    }

    public TabChannels(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
    {
        super(paramContext, paramAttributeSet, paramInt);
        this.mContext = paramContext;
        init();
    }

    private void init()
    {
        LayoutInflater lf = (LayoutInflater)mContext.getSystemService("layout_inflater");
        addView(lf.inflate(R.layout.view_tab_channels, null));

        mContainer = ((RelativeLayout)findViewById(R.id.tab_channel_container));
        mImageButton_01 = ((TabChannelsItemView)findViewById(R.id.tab_channel_button_1));
        mImageButton_02 = ((TabChannelsItemView)findViewById(R.id.tab_channel_button_2));
        mImageButton_03 = ((TabChannelsItemView)findViewById(R.id.tab_channel_button_3));
        mImageButton_04 = ((TabChannelsItemView)findViewById(R.id.tab_channel_button_4));
        mImageButton_05 = ((TabChannelsItemView)findViewById(R.id.tab_channel_button_5));
        mImageButton_06 = ((TabChannelsItemView)findViewById(R.id.tab_channel_button_6));
        mImageButton_07 = ((TabChannelsItemView)findViewById(R.id.tab_channel_button_7));
        mImageButton_08 = ((TabChannelsItemView)findViewById(R.id.tab_channel_button_8));
        mImageButton_09 = ((TabChannelsItemView)findViewById(R.id.tab_channel_button_9));
        mImageButton_10 = ((TabChannelsItemView)findViewById(R.id.tab_channel_button_10));
        mImageButton_11 = ((TabChannelsItemView)findViewById(R.id.tab_channel_button_11));
        mImageButton_12 = ((TabChannelsItemView)findViewById(R.id.tab_channel_button_12));

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
        this.mImageButton_01.setChannelImageRes(R.drawable.tab_channel_image_variety );
        this.mImageButton_02.setChannelImageRes(R.drawable.tab_channel_image_sports);
        this.mImageButton_03.setChannelImageRes(R.drawable.tab_channel_image_documentaryfilm);
        this.mImageButton_04.setChannelImageRes(R.drawable.tab_channel_image_education);
        this.mImageButton_05.setChannelImageRes(R.drawable.tab_channel_image_entertainment);
        this.mImageButton_06.setChannelImageRes(R.drawable.tab_channel_image_episode);
        this.mImageButton_07.setChannelImageRes(R.drawable.tab_channel_image_fashion);
        this.mImageButton_08.setChannelImageRes(R.drawable.tab_channel_image_flower);
        this.mImageButton_09.setChannelImageRes(R.drawable.tab_channel_image_micro_movie);
        this.mImageButton_10.setChannelImageRes(R.drawable.tab_channel_image_movie);
        this.mImageButton_11.setChannelImageRes(R.drawable.tab_channel_image_music);
        this.mImageButton_12.setChannelImageRes(R.drawable.tab_channel_image_qiyi);
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
}
