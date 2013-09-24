package com.wefeng.launcher.widget;

import com.wefeng.launcher.R;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

public class ProgressBarItem extends FrameLayout
{
  private TextView itemTextView;

  public ProgressBarItem(Context paramContext)
  {
    super(paramContext);
    init(paramContext);
  }

  public ProgressBarItem(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    init(paramContext);
  }

  public ProgressBarItem(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    init(paramContext);
  }

  private void init(Context paramContext)
  {
    View localView = ((LayoutInflater)paramContext.getSystemService("layout_inflater")).inflate(R.layout.progressbar_item, null);
    addView(localView);
    ImageView localImageView = (ImageView)localView.findViewById(R.id.progressbar_item_image_id);
    this.itemTextView = ((TextView)localView.findViewById(R.id.progressbar_tagtext_id));
    localImageView.setImageResource(R.drawable.app_loading);
    ((AnimationDrawable)localImageView.getDrawable()).start();
  }

  public void setText(String paramString)
  {
    if (paramString != null)
      this.itemTextView.setText(paramString);
  }
}
