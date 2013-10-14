package com.wefeng.launcher.widget;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wefeng.launcher.R;

@SuppressWarnings("ALL")
public class RightDialog extends Dialog {

    private View mContainerView;
    private Context mContext = null;

    public RightDialog(Context context) {
        super(context, R.style.RightDialog);
        mContext = context;

    }

    public RightDialog(Context context, int theme) {
        super(context, theme);
        mContext = context;

    }

    public void setContainerView(View v) {
        mContainerView = v;
    }


    public void init()
    {
        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View layout = inflater.inflate(R.layout.right_dialog_layout, null);

        addContentView(layout, new LayoutParams(
                LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));

        if(mContainerView != null)
        {
            LinearLayout container = (LinearLayout)layout.findViewById(R.id.dialog_container);
            container.addView(mContainerView);
        }

        setContentView(layout);

    }

}
