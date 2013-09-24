package com.wefeng.launcher.widget;

import com.wefeng.launcher.R;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

public class ExceptionDialog extends FrameLayout
{
  private TextView codeTextView;
  private TextView msgTextView;

  public ExceptionDialog(Context paramContext)
  {
    super(paramContext);
    init(paramContext);
  }

  public ExceptionDialog(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    init(paramContext);
  }

  public ExceptionDialog(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    init(paramContext);
  }

  private void init(Context paramContext)
  {
    View localView = ((LayoutInflater)paramContext.getSystemService("layout_inflater")).inflate(R.layout.exception_view_layout, null);
    this.msgTextView = ((TextView)localView.findViewById(R.id.exception_msg_textview_id));
    this.codeTextView = ((TextView)localView.findViewById(R.id.exception_errorcode_textview_id));
    addView(localView);
  }

  public void cancel()
  {
    setVisibility(8);
  }

  public boolean isShow()
  {
    return getVisibility() == 0;
  }

  public ExceptionDialog setCode(String paramString)
  {
    this.codeTextView.setText(paramString);
    return this;
  }

  public ExceptionDialog setMessage(String paramString)
  {
    this.msgTextView.setText(paramString);
    return this;
  }

  public void show()
  {
    setVisibility(0);
  }
}