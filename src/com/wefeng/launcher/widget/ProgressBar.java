package com.wefeng.launcher.widget;

import com.wefeng.launcher.R;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

public class ProgressBar extends FrameLayout
{
  public ProgressBar(Context paramContext)
  {
    super(paramContext);
    init(paramContext);
  }

  public ProgressBar(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    init(paramContext);
  }

  public ProgressBar(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    init(paramContext);
  }

  private void init(Context paramContext)
  {
    View localView = ((LayoutInflater)paramContext.getSystemService("layout_inflater")).inflate(R.layout.progressbar, null);
    addView(localView);
    ImageView localImageView = (ImageView)localView.findViewById(R.id.progressbar_item_image_id);
    localImageView.setImageResource(R.drawable.app_loading);
    ((AnimationDrawable)localImageView.getDrawable()).start();
  }
}
