package com.wefeng.launcher.widget;

import android.content.Context;
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

  protected int getChildDrawingOrder(int childCount, int i)
  {

    View localView = getFocusedChild();

    int focusId = 0;
    
    for(int j=0; j<childCount; j++)
    {
    	if(getChildAt(j) == localView)
    	{
    		focusId = j;
    		break;
    	}

    }
    
    if (i == childCount - 1) {
        return focusId;
    } else if (i >= focusId) {
        return i + 1;
    } else {
        return i;
    }
   
  }
}