package com.wefeng.launcher.widget;

import com.wefeng.launcher.R;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

public class TabRecommendItemView extends FrameLayout
{
  private ImageView mChannelImage = null;
  private Context mContext = null;
  private ImageView mHighLightImage = null;
  private TextView mRecommendTitle = null;

  public TabRecommendItemView(Context paramContext)
  {
    super(paramContext);
    this.mContext = paramContext;
    init();
  }

  public TabRecommendItemView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    this.mContext = paramContext;
    init();
  }

  public TabRecommendItemView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    this.mContext = paramContext;
    init();
  }

  private void init()
  {
    addView(((LayoutInflater)this.mContext.getSystemService("layout_inflater")).inflate(R.layout.view_tab_recommend_item, null));
    this.mChannelImage = ((ImageView)findViewById(R.id.tab_recommend_item_image));
    this.mRecommendTitle = ((TextView)findViewById(R.id.tab_recommend_item_text));
    this.mHighLightImage = ((ImageView)findViewById(R.id.tab_recommend_highlight_image));
  }

  public void onImageButtonFocusChanged(boolean paramBoolean)
  {
    if (paramBoolean)
    {
      this.mRecommendTitle.setTextColor(this.mContext.getResources().getColor(2131034137));
      this.mHighLightImage.setVisibility(0);
      return;
    }
    this.mRecommendTitle.setTextColor(this.mContext.getResources().getColor(2131034136));
    this.mHighLightImage.setVisibility(4);
  }

  public void setHighLightImageRes(int paramInt)
  {
    this.mHighLightImage.setImageResource(paramInt);
    this.mHighLightImage.setVisibility(4);
  }

  public void setRecommendImageBitmap(Bitmap paramBitmap)
  {
    this.mChannelImage.setImageBitmap(paramBitmap);
  }

  public void setRecommendImageRes(int paramInt)
  {
    this.mChannelImage.setImageResource(paramInt);
  }

  public void setRecommendTitleText(String paramString)
  {
    this.mRecommendTitle.setText(paramString);
    setRecommendTitleVisibility(true);
  }

  public void setRecommendTitleVisibility(boolean paramBoolean)
  {
    if (paramBoolean)
    {
      this.mRecommendTitle.setVisibility(0);
      return;
    }
    this.mRecommendTitle.setVisibility(4);
  }
}
