package com.wefeng.launcher.widget;

import android.content.Context;
import android.content.DialogInterface;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.wefeng.launcher.R;
import com.wefeng.launcher.util.GetApk;

/**
 * Created by Administrator on 13-9-26.
 */
public class TabSettings extends FrameLayout {

    private Context mContext = null;
    private RelativeLayout mContainer = null;
    private LinearLayout mTopButtonUpdate;
    private LinearLayout mTopButtonNetwork;
    private LinearLayout mTopButtonDlna;
    private LinearLayout mTopButtonMultiscreen;
    private LinearLayout mTopButtonHelp;
    private LinearLayout mTopButtonMore;

    private OnFocusChangeListener mFocusListener = new OnFocusChangeListener()
    {
        public void onFocusChange(View view, boolean isFocus)
        {

            if (isFocus)
            {
                view.bringToFront();
                TabSettings.this.mContainer.invalidate();
            }
        }
    };


    public TabSettings(Context context) {
        super(context);
        mContext = context;
        init();
    }

    public TabSettings(Context paramContext, AttributeSet paramAttributeSet)
    {
        super(paramContext, paramAttributeSet);
        mContext = paramContext;
        init();
    }

    public TabSettings(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
    {
        super(paramContext, paramAttributeSet, paramInt);
        this.mContext = paramContext;
        init();
    }

    private void init()
    {
        LayoutInflater lf = (LayoutInflater)mContext.getSystemService("layout_inflater");
        addView(lf.inflate(R.layout.view_tab_settings, null));

        mContainer = ((RelativeLayout)findViewById(R.id.tab_settings_container));

        mTopButtonUpdate = ((LinearLayout)findViewById(R.id.tab_settings_account_button));
        mTopButtonNetwork = ((LinearLayout)findViewById(R.id.tab_settings_network_button));
        mTopButtonDlna = ((LinearLayout)findViewById(R.id.tab_settings_dlna_button));
        mTopButtonMultiscreen = ((LinearLayout)findViewById(R.id.tab_settings_multiscreen_button));
        mTopButtonHelp = ((LinearLayout)findViewById(R.id.tab_settings_system_help_button));
        mTopButtonMore = ((LinearLayout)findViewById(R.id.tab_settings_more_button));

        mTopButtonUpdate.setOnFocusChangeListener(this.mFocusListener);
        mTopButtonNetwork.setOnFocusChangeListener(this.mFocusListener);
        mTopButtonDlna.setOnFocusChangeListener(this.mFocusListener);
        mTopButtonMultiscreen.setOnFocusChangeListener(this.mFocusListener);
        mTopButtonHelp.setOnFocusChangeListener(this.mFocusListener);
        mTopButtonMore.setOnFocusChangeListener(this.mFocusListener);


        addButtonFunc();
    }

    NetworkDialog mNetworkdialog = null;

    private void addButtonFunc()
    {
        mTopButtonMore.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                GetApk.startNewApp(mContext, "com.android.settings");
            }
        });

        mTopButtonDlna.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                GetApk.startNewApp(mContext, "com.hisilicon.dlna.settings");
            }
        });

        mTopButtonNetwork.setOnClickListener(new OnClickListener()
        {

            @Override
            public void onClick(View v) {

                mNetworkdialog = new NetworkDialog(mContext);

                mNetworkdialog.show();
//                    NetworkDialog.Builder builder = new NetworkDialog.Builder(mContext);
//                    builder.setMessage("这个就是自定义的提示框");
//                    builder.setTitle("提示");
//                    builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
//                        public void onClick(DialogInterface dialog, int which) {
//                            dialog.dismiss();
//                            //设置你的操作事项
//                        }
//                    });
//
//                    builder.setNegativeButton("取消",
//                            new android.content.DialogInterface.OnClickListener() {
//                                public void onClick(DialogInterface dialog, int which) {
//                                    dialog.dismiss();
//                                }
//                            });
//
//                    builder.create().show();

            }
        });
    }
}
