package com.wefeng.launcher.widget;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.NetworkInfo;
import android.net.wifi.WifiConfiguration;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wefeng.hisi.network.EthCtl;
import com.wefeng.hisi.network.WiFiCtl;
import com.wefeng.hisi.wifi.AP;
import com.wefeng.launcher.R;

import java.util.ArrayList;

/**
 * Created by Administrator on 13-10-11.
 */
public class NetworkDialog extends RightDialog implements EthCtl.StatusCallBack {

    private Context mContext = null;
    private TextView mAutoWiredBtn = null;
    private TextView mAutoWirelessBtn = null;
    private TextView mManualWiredBtn  = null;
    private LinearLayout mMainLayout = null;
    private EthCtl mNetworkCtl;
    private String TAG = "launcher NetworkDialog";

    public NetworkDialog(Context context) {
        super(context);

        mContext = context;
        init();
    }

    public NetworkDialog(Context context, int theme) {
        super(context, theme);

        mContext = context;
        init();
    }

    public void init()
    {
        mNetworkCtl = new EthCtl(mContext);

        LayoutInflater inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View layout = inflater.inflate(R.layout.network_dialog_layout, null);
        setContainerView(layout);

        mMainLayout = (LinearLayout)layout.findViewById(R.id.network_dialog_layout);
        mAutoWiredBtn  = (TextView)layout.findViewById(R.id.tv_auto_wired);
        mAutoWirelessBtn = (TextView)layout.findViewById(R.id.tv_auto_wireless);
        mManualWiredBtn = (TextView)layout.findViewById(R.id.tv_manul_wired);

        mAutoWiredBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mNetworkCtl.startEth();
                WiFiCtl.stopWIFI(mContext);
                mNetworkCtl.startDHCP(true);
            }
        });

        mAutoWirelessBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        super.init();
    }

    public String getEthIP()
    {
        return mNetworkCtl.getEthIP();
    }

    @Override
    protected void onStart() {
        mNetworkCtl.startBroadcast(mContext, this);
        super.onStart();
    }

    @Override
    protected void onStop() {
        mNetworkCtl.stopBroadcast(mContext);
        super.onStop();
    }

    private void showDhcpIP()
    {

    }

    private void showStaticIP()
    {

    }

    @Override
    public void onReceive(Context context, Intent intent, int message) {
        switch (message) {
            case EthCtl.EVENT_DHCP_CONNECT_SUCCESSED:
                Log.w(TAG, "EVENT_DHCP_CONNECT_SUCCESSED ");
                showDhcpIP();
                break;
            case EthCtl.EVENT_DHCP_CONNECT_FAILED:
                Log.w(TAG, "EVENT_DHCP_CONNECT_FAILED ");
                //show connect faled
                break;
            case EthCtl.EVENT_DHCP_DISCONNECT_SUCCESSED:
                Log.w(TAG, "EVENT_DHCP_DISCONNECT_SUCCESSED ");
                //show disconnect successe
                break;
            case EthCtl.EVENT_DHCP_DISCONNECT_FAILED:
                Log.w(TAG, "EVENT_DHCP_DISCONNECT_FAILED ");
                //show disconnect failed
                break;
            case EthCtl.EVENT_STATIC_CONNECT_SUCCESSED:
                Log.w(TAG, "EVENT_STATIC_CONNECT_SUCCESSED ");
                showStaticIP();
                //show set static ip connect success
                break;
            case EthCtl.EVENT_STATIC_CONNECT_FAILED:
                Log.w(TAG, "EVENT_STATIC_CONNECT_FAILED ");
                //show set static ip connect failed
                break;
            case EthCtl.EVENT_STATIC_DISCONNECT_SUCCESSED:
                Log.w(TAG, "EVENT_STATIC_DISCONNECT_SUCCESSED ");
                //show set static ip disconnect success
                break;
            case EthCtl.EVENT_STATIC_DISCONNECT_FAILED:
                Log.w(TAG, "EVENT_STATIC_DISCONNECT_FAILED ");
                //show set static ip disconnect failed
                break;
            case EthCtl.EVENT_PHY_LINK_UP:
                Log.w(TAG, "EVENT_PHY_LINK_UP ");
                //show phy link up
                break;
            case EthCtl.EVENT_PHY_LINK_DOWN:
                Log.w(TAG, "EVENT_PHY_LINK_DOWN ");
                //show phy link down
                break;
            default:
                break;
        }
    }
}
