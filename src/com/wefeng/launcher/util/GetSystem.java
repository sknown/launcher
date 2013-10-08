package com.wefeng.launcher.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 13-10-8.
 */
public class GetSystem {

    public static String getTime()
    {
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
        Date curDate  = new Date(System.currentTimeMillis());//获取当前时间

        return formatter.format(curDate);
    }
}
