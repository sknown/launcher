package com.wefeng.launcher.widget;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.wefeng.launcher.R;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

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

        TabApkItemView moreApp =mImageButton_12;

        moreApp.setApkImage(getResources().getDrawable(R.drawable.tab_apk_more_icon));
        moreApp.setTitle(R.string.more_app);
        moreApp.setTag("com.unionman.androidappmanager");

    }

    private final int BUTTON_COUNT = 12;

    public void initImage()
    {
        this.mImageButton_01.setBackgroud(R.drawable.tab_channel_image_qiyi );
        this.mImageButton_02.setBackgroud(R.drawable.tab_channel_image_qiyi);
        this.mImageButton_03.setBackgroud(R.drawable.tab_channel_image_qiyi);
        this.mImageButton_04.setBackgroud(R.drawable.tab_channel_image_qiyi);
        this.mImageButton_05.setBackgroud(R.drawable.tab_channel_image_qiyi);
        this.mImageButton_06.setBackgroud(R.drawable.tab_channel_image_qiyi);
        this.mImageButton_07.setBackgroud(R.drawable.tab_channel_image_qiyi);
        this.mImageButton_08.setBackgroud(R.drawable.tab_channel_image_qiyi);
        this.mImageButton_09.setBackgroud(R.drawable.tab_channel_image_qiyi);
        this.mImageButton_10.setBackgroud(R.drawable.tab_channel_image_qiyi);
        this.mImageButton_11.setBackgroud(R.drawable.tab_channel_image_qiyi);
        this.mImageButton_12.setBackgroud(R.drawable.tab_channel_image_qiyi);

    }

	public void setApk(HashMap<String,Drawable> apkList, HashMap<String,PackageInfo> packageList)
    {
        int i = BUTTON_COUNT-2;

        Iterator iterator = apkList.keySet().iterator();
        while(iterator.hasNext() && i>=0 && i<packageList.size())
        {
            String label = (String)iterator.next();
            Drawable icon = apkList.get(label);
            PackageInfo packInfo= packageList.get(label);
            TabApkItemView view = mApkButton.get(i);
            view.setApkImage(icon);
            view.setTitle(label);
            view.setTag(packInfo.packageName);
            i--;
        }
    }
}
