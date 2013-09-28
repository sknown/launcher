package com.wefeng.launcher.widget;

import com.wefeng.launcher.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

public class TabWatchTvItemView extends FrameLayout
{
  private ImageView mChannelImage = null;
  private Context mContext = null;
  private ImageView mHighLightImage = null;
  private TextView mWatchTvTitle = null;

  public TabWatchTvItemView(Context paramContext)
  {
    super(paramContext);
    this.mContext = paramContext;
    init();
  }

  public TabWatchTvItemView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    this.mContext = paramContext;
    init();
  }

  public TabWatchTvItemView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    this.mContext = paramContext;
    init();
  }

  private void init()
  {
    addView(((LayoutInflater)this.mContext.getSystemService("layout_inflater")).inflate(R.layout.view_tab_watchtv_item, null));
    this.mChannelImage = ((ImageView)findViewById(R.id.tab_recommend_item_image));
    this.mWatchTvTitle = ((TextView)findViewById(R.id.tab_recommend_item_text));
    this.mHighLightImage = ((ImageView)findViewById(R.id.tab_recommend_highlight_image));
  }

  public void onImageButtonFocusChanged(boolean paramBoolean)
  {
    if (paramBoolean)
    {
      this.mWatchTvTitle.setTextColor(this.mContext.getResources().getColor(R.color.album_text_focus));
      this.mHighLightImage.setVisibility(0);
      return;
    }
    this.mWatchTvTitle.setTextColor(this.mContext.getResources().getColor(R.color.album_text_normal));
    this.mHighLightImage.setVisibility(4);
  }

  public void setHighLightImageRes(int paramInt)
  {
    this.mHighLightImage.setImageResource(paramInt);
    this.mHighLightImage.setVisibility(4);
  }

  public void setWatchTvImageBitmap(Bitmap paramBitmap)
  {
    this.mChannelImage.setImageBitmap(paramBitmap);
  }

  public void setWatchTvImageRes(int paramInt)
  {
    this.mChannelImage.setImageResource(paramInt);
  }

  public void setWatchTvTitleText(String paramString)
  {
    this.mWatchTvTitle.setText(paramString);
    setWatchTvTitleVisibility(true);
  }

  public void setWatchTvTitleText(int resId)
  {
      mWatchTvTitle.setText(resId);
      setWatchTvTitleVisibility(true);
  }

  public void setWatchTvTitleVisibility(boolean paramBoolean)
  {
    if (paramBoolean)
    {
      this.mWatchTvTitle.setVisibility(0);
      return;
    }
    this.mWatchTvTitle.setVisibility(4);
  }
}
