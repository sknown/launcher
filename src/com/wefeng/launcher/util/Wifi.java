package com.wefeng.launcher.util;

import android.content.Context;
import android.net.NetworkInfo;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.util.Log;

import com.wefeng.hisi.network.EthCtl;
import com.wefeng.hisi.network.WiFiCtl;
import com.wefeng.hisi.wifi.AP;

import java.util.ArrayList;

/**
 * Created by Administrator on 13-10-11.
 */
public class Wifi implements WiFiCtl.WIFICtlCallback{
    public static final int AP_LIST_UPDATE = 1;
    public static final int AP_STATUS_UPDATE = 2;

    private final EthCtl mNetworkCtl;
    private final WiFiCtl mWifiCtl;
    private Context mContext = null;
    private ArrayList<AP> mAccessPoint = new ArrayList<AP>();
    private WifiListener mListener = null;

    private String TAG = "launcher wifi";

    public interface WifiListener
    {
        void notifyWifi(int msg, Object object);
    }

    public Wifi(Context context, WifiListener listener)
    {
        mContext = context;
        mNetworkCtl = new EthCtl(context);
        mWifiCtl = new WiFiCtl(context, this);

        mListener = listener;

    }

    public void start()
    {
        mNetworkCtl.stopEth();
        mWifiCtl.startWIFI();
        mWifiCtl.startScan();
    }

    public void stopScan()
    {
        mWifiCtl.stopScan();
    }

    public void stopWifi()
    {
        mWifiCtl.stopWIFI();
    }

    public ArrayList<AP> getAPList()
    {
        return mAccessPoint;
    }

    public  void connect(int index, String strPassword)
    {
        int networkSetupMethod = WiFiCtl.MANUAL;
        AP accessPoint = mAccessPoint.get(index);
        String password = strPassword;
        String ssid = accessPoint.ssid;
        int accessPointSecurity = accessPoint.getSecurity();

        mWifiCtl.forgetConnecting();
        mWifiCtl.setAccessPoint(accessPoint);
        mWifiCtl.submit(networkSetupMethod, accessPoint, password, ssid, accessPointSecurity,
                null, null, null, null, null, null, null, true);
    }

    @Override
    public void onResult(int wifiState, ArrayList<AP> list) {
        Log.v(TAG, wifiState + "  " + list);
        switch (wifiState)
        {
            case WifiManager.WIFI_STATE_ENABLED :
                {
                        boolean foundSame;
                        ArrayList<AP> tmpList = new ArrayList<AP>();

                        for (AP accessPoint : list)
                        {
                            foundSame = false;

                            for (AP ap : mAccessPoint)
                            {
                                if (ap.ssid.equals(accessPoint.ssid)
                                        && (accessPoint.bssid == null
                                        || ap.bssid == null
                                        || ap.bssid.equals(accessPoint.bssid)))
                                {
                                    int pos = mAccessPoint.indexOf(ap);
//                                    Log.e(TAG, "found ap update ssid: " + accessPoint.ssid + " level "
//                                                + accessPoint.getLevel() + " pos " + pos);
                                    mAccessPoint.set(pos, accessPoint);
                                    foundSame = true;
                                    break;
                                }

                            }

                            if (foundSame)
                            {
                                continue;
                            }
                            else
                            {
                                tmpList.add(accessPoint);
                            }

                        }

                        if (tmpList.size() != 0)
                        {
                            mAccessPoint.addAll(tmpList);
                        }

                        mListener.notifyWifi(AP_LIST_UPDATE, null);
                }
                break;
            case WifiManager.WIFI_STATE_ENABLING :
                Log.v(TAG, "WifiManager.WIFI_STATE_ENABLING");
                break;

            case WifiManager.WIFI_STATE_DISABLING :
                Log.v(TAG, "WifiManager.WIFI_STATE_DISABLING");
                break;

            case WifiManager.WIFI_STATE_DISABLED :
                Log.v(TAG, "WifiManager.WIFI_STATE_DISABLED");
                break;
            default :
                break;
        }
    }

    @Override
    public void notifyChanged(int i, WifiConfiguration wifiConfiguration) {

    }

    @Override
    public void notifyChanged(NetworkInfo.DetailedState detailedState, WifiConfiguration wifiConfiguration) {
        mListener.notifyWifi(AP_STATUS_UPDATE, detailedState.toString());
    }
}
