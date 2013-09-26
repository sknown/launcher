package com.wefeng.launcher.widget;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;

public class CustomDrawingOrderLayout extends RelativeLayout
{
  public static final String TAG = "CustomDrawingOrderLayout";

  public CustomDrawingOrderLayout(Context paramContext)
  {
    super(paramContext);
    init();
  }

  public CustomDrawingOrderLayout(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    init();
  }

  public CustomDrawingOrderLayout(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    init();
  }

  private void init()
  {
    super.setChildrenDrawingOrderEnabled(true);
  }

  @Override
	protected void onFocusChanged(boolean gainFocus, int direction,
			Rect previouslyFocusedRect) {
		super.onFocusChanged(gainFocus, direction, previouslyFocusedRect);
	}
  protected int getChildDrawingOrder(int childCount, int i)
  {

    int focusId = 0;
    int childId = 0;

    for(int j=0; j<childCount; j++)
    {
    	if(getChildAt(j).isFocused())
    	{
    		focusId = j;
    		break;
    	}

    }
    
    if (i == childCount - 1) {
    	childId = focusId;
    } else if (i >= focusId) {
    	childId = i + 1;
    } else {
    	childId = i;
    }
    
    return childId;
   
  }
}