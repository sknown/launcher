package com.wefeng.launcher.widget;

import com.wefeng.launcher.R;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageView;

public class TabChannelsItemView extends FrameLayout
{
  private ImageView mChannelImage = null;
  private Context mContext = null;
  private ImageView mHighLightImage = null;

  public TabChannelsItemView(Context paramContext)
  {
    super(paramContext);
    this.mContext = paramContext;
    init();
  }

  public TabChannelsItemView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    this.mContext = paramContext;
    init();
  }

  public TabChannelsItemView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    this.mContext = paramContext;
    init();
  }

  private void init()
  {
    addView(((LayoutInflater)this.mContext.getSystemService("layout_inflater")).inflate(R.layout.view_tab_channel_item, null));
    this.mChannelImage = ((ImageView)findViewById(R.id.tab_channel_item_image));
    this.mHighLightImage = ((ImageView)findViewById(R.id.tab_channel_highlight_image));
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

  public void setChannelImageRes(int paramInt)
  {
    this.mChannelImage.setImageResource(paramInt);
  }

  public void setHighLightImageRes(int paramInt)
  {
    this.mHighLightImage.setImageResource(paramInt);
    this.mHighLightImage.setVisibility(4);
  }
}

/* Location:           D:\ApktoolGui v2.0 Final\爱奇�?.0forTV_7po_dex2jar.jar
 * Qualified Name:     com.qiyi.video.widget.TabChannelsItemView
 * JD-Core Version:    0.6.0
 */