package com.wefeng.launcher.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.wefeng.launcher.R;

public class TabTopItemView extends FrameLayout
{
  private ImageView mChannelImage = null;
  private Context mContext = null;
  private ImageView mHighLightImage = null;
  //private TextView mTopTitle = null;

  public TabTopItemView(Context paramContext)
  {
    super(paramContext);
    this.mContext = paramContext;
    init();
  }

  public TabTopItemView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    this.mContext = paramContext;
    init();
  }

  public TabTopItemView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    this.mContext = paramContext;
    init();
  }

  private void init()
  {
    addView(((LayoutInflater)this.mContext.getSystemService("layout_inflater")).inflate(R.layout.view_tab_top_item, null));
    this.mChannelImage = ((ImageView)findViewById(R.id.tab_top_item_image));
    //this.mTopTitle = ((TextView)findViewById(R.id.tab_top_item_text));
    this.mHighLightImage = ((ImageView)findViewById(R.id.tab_top_highlight_image));
  }

  public void onImageButtonFocusChanged(boolean paramBoolean)
  {
    if (paramBoolean)
    {
      //this.mTopTitle.setTextColor(this.mContext.getResources().getColor(R.color.album_text_focus));
      this.mHighLightImage.setVisibility(0);
      return;
    }
    //this.mTopTitle.setTextColor(this.mContext.getResources().getColor(R.color.album_text_normal));
    this.mHighLightImage.setVisibility(4);
  }

  public void setHighLightImageRes(int paramInt)
  {
    this.mHighLightImage.setImageResource(paramInt);
    this.mHighLightImage.setVisibility(4);
  }

  public void setTopImageBitmap(Bitmap paramBitmap)
  {
    this.mChannelImage.setImageBitmap(paramBitmap);
  }

  public void setTopImageRes(int paramInt)
  {
    this.mChannelImage.setImageResource(paramInt);
  }

  public void setTopTitleText(String paramString)
  {
   // this.mTopTitle.setText(paramString);
    setTopTitleVisibility(true);
  }

    public void setTopTitleVisibility(boolean paramBoolean)
    {
        if (paramBoolean)
        {
          //this.mTopTitle.setVisibility(0);
          return;
        }
        //this.mTopTitle.setVisibility(4);
    }
}
