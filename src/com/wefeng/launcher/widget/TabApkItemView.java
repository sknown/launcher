package com.wefeng.launcher.widget;

import com.wefeng.launcher.R;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

public class TabApkItemView extends FrameLayout implements View.OnClickListener {
    private ImageView mApkImage = null;
    private Context mContext = null;
    private ImageView mHighLightImage = null;
    private TextView mTitle = null;

    public TabApkItemView(Context paramContext)
    {
        super(paramContext);
        this.mContext = paramContext;
        init();
    }

    public TabApkItemView(Context paramContext, AttributeSet paramAttributeSet)
    {
        super(paramContext, paramAttributeSet);
        this.mContext = paramContext;
        init();
    }

    public TabApkItemView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
    {
        super(paramContext, paramAttributeSet, paramInt);
        this.mContext = paramContext;
        init();
    }

    private void init()
    {
        addView(((LayoutInflater)this.mContext.getSystemService("layout_inflater")).inflate(R.layout.view_tab_apk_item, null));
        this.mApkImage = ((ImageView)findViewById(R.id.tab_channel_item_image));
        this.mHighLightImage = ((ImageView)findViewById(R.id.tab_channel_highlight_image));
        mTitle = (TextView)findViewById(R.id.tab_apk_item_text);

        setOnClickListener(this);
    }

    public void onImageButtonFocusChanged(boolean paramBoolean)
    {
        if (paramBoolean)
        {
            this.mHighLightImage.setVisibility(0);
            return;
        }

        this.mHighLightImage.setVisibility(4);
    }

    public void setApkImageRes(int paramInt)
    {
        this.mApkImage.setImageResource(paramInt);
    }

    public void setBackgroud(int resId)
    {
        mApkImage.setBackgroundResource(resId);
    }

    public void setApkImage(Drawable icon)
    {
        mApkImage.setImageDrawable(icon);
    }

    public void setHighLightImageRes(int paramInt)
    {
        this.mHighLightImage.setImageResource(paramInt);
        this.mHighLightImage.setVisibility(4);
    }

    public void setTitle(String title)
    {
        mTitle.setText(title);
    }

    public void setTitle(int resId)
    {
        mTitle.setText(resId);
    }
    @Override
    public void onClick(View v) {
        String packageName = (String)v.getTag();

        Intent mainIntent = mContext.getPackageManager().getLaunchIntentForPackage(packageName);

        assert mainIntent != null;

        mainIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        mContext.startActivity(mainIntent);
    }
}
