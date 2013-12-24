package com.wefeng.launcher.widget;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo.DetailedState;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.wefeng.hisi.network.EthCtl;
import com.wefeng.hisi.network.WiFiCtl;
import com.wefeng.hisi.wifi.AP;
import com.wefeng.launcher.R;
import com.wefeng.launcher.util.GetApk;
import com.wefeng.launcher.util.WifiAdapter;

import java.util.ArrayList;
/**
 * Created by Administrator on 13-10-11.
 */
public class NetworkDialog extends RightDialog implements EthCtl.StatusCallBack, WiFiCtl.WIFICtlCallback {

    private Context mContext = null;
    private TextView mAutoWiredBtn = null;
    private TextView mAutoWirelessBtn = null;
    private TextView mManualWiredBtn = null;
    private LinearLayout mMainLayout = null;
    private EthCtl mNetworkCtl;
    private String TAG = "launcher NetworkDialog";
    private ConnectivityManager mConnectivityManager = null;
    private TextView mWiredAutoStated = null;
    private WiFiCtl mWifiCtl = null;
    private ListView mWifiListView = null;
    private ArrayList<AP> mAccessPoint;
    private boolean editing = false;
    private Button mConnectBtn = null;
    private LinearLayout mItemLayout,password,txt;
    private TextView mWirelessAutoStated = null;
    private Animation animation;

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

    public void init() {
        mNetworkCtl = new EthCtl(mContext);
        mWifiCtl = new WiFiCtl(mContext, (WiFiCtl.WIFICtlCallback) this);

        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View layout = inflater.inflate(R.layout.network_dialog_layout, null);
        setContainerView(layout);
        mMainLayout = (LinearLayout) layout.findViewById(R.id.network_dialog_layout);
        mAutoWiredBtn = (TextView) layout.findViewById(R.id.tv_auto_wired);
        mAutoWirelessBtn = (TextView) layout.findViewById(R.id.tv_auto_wireless);
        mManualWiredBtn = (TextView) layout.findViewById(R.id.tv_manul_wired);
        mWiredAutoStated = (TextView) layout.findViewById(R.id.tv_wired_auto_stated);
        mWifiListView = (ListView) layout.findViewById(R.id.lv_wireless_ssid);
        mWirelessAutoStated = (TextView)layout.findViewById(R.id.tv_wireless_auto_stated);

        if(mWifiCtl.isConnected() == true)
        {
            mWirelessAutoStated.setVisibility(View.VISIBLE);
            showWifiSSID();
        }
	
	showDhcpIP();

        mManualWiredBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GetApk.startNewApp(mContext, "com.android.settings");
            }
        });
        //自动获取有线网络
        mAutoWiredBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mNetworkCtl.startEth();
                if(mWiredAutoStated.getVisibility() == View.GONE)
                {
                    mWiredAutoStated.setVisibility(View.VISIBLE);

                }
                mWiredAutoStated.setText(R.string.auto_get_ip_please_wait);
                WiFiCtl.stopWIFI(mContext);
                mNetworkCtl.startDHCP(true);

                if(mWifiListView.getVisibility() != View.GONE)
                {
                    mWifiListView.setVisibility(View.GONE);
                }
                if(mWirelessAutoStated.getVisibility() != View.GONE)
                {
                    mWirelessAutoStated.setVisibility(View.GONE);
                }
//                mWiredAutoStated.setText(R.string.auto_get_ip_please_wait);

            }
        });
        //获取自动获取无线网络的功能
        mAutoWirelessBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mWiredAutoStated.setVisibility(View.GONE);
                if (mWifiCtl.isConnected() == true)
                {
                    mWirelessAutoStated.setVisibility(View.VISIBLE);
                    mWirelessAutoStated.setText(R.string.connecting);
                    showWifiSSID();
                    //mWifiListView.setVisibility(View.GONE);
                }else
                {
                    mNetworkCtl.stopEth();
                    mWifiCtl.startWIFI();
                    mWifiCtl.startScan();
                    //mWirelessAutoStated.setVisibility(View.VISIBLE);

                   // mWirelessAutoStated.setText(R.string.auto_get_ip_please_wait);
                }
                mWifiListView.setVisibility(View.VISIBLE);

                mWifiListView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        setButtonFocus(true,0);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });
                mWifiListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        final int pos = i;
                        editing = true;
                        final AP scanResult = mAccessPoint.get(i);
                        int animId = R.anim.push_right_in;
                        animation = AnimationUtils.loadAnimation(mContext,
                                animId);
                        setButtonFocus(false,0);
                        mItemLayout = (LinearLayout) view;
                        password = (LinearLayout) ((FrameLayout) mItemLayout.getChildAt(0)).getChildAt(0);
                        txt = (LinearLayout) ((FrameLayout) mItemLayout.getChildAt(0)).getChildAt(1);
                        password.setVisibility(View.VISIBLE);
                        txt.setVisibility(View.INVISIBLE);
                        view.setBackgroundResource(R.drawable.item_focus_bg);

                        mConnectBtn = (Button) view.findViewById(R.id.btn_connect);

                        mConnectBtn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                editing = false;
                                password.setVisibility(View.INVISIBLE);
                                txt.setVisibility(View.VISIBLE);
                                mWirelessAutoStated.setVisibility(View.VISIBLE);
                                mWirelessAutoStated.setText(R.string.connecting);
                                mWifiListView.setDescendantFocusability(ViewGroup.FOCUS_BEFORE_DESCENDANTS);

                                EditText password = (EditText) ((LinearLayout) v.getParent()).getChildAt(0);
                                String strPassword = password.getText().toString();
                                wifiConnect(scanResult, strPassword);

                                View myView = ((View) v.getParent().getParent().getParent());
                                myView.startAnimation(animation);
                                myView.setBackgroundDrawable(null);
                            }
                        });
                        mConnectBtn.setOnKeyListener(new View.OnKeyListener() {
                            @Override
                            public boolean onKey(View v, int keyCode, KeyEvent event) {
                                View myView = ((View) v.getParent().getParent().getParent());
                                switch (keyCode) {
                                    case KeyEvent.KEYCODE_BACK:
                                    case KeyEvent.KEYCODE_DPAD_DOWN:
                                        setItemInitView(myView);
                                        setButtonFocus(true,pos);

                                        return true;
                                    case KeyEvent.KEYCODE_DPAD_UP:
                                            setButtonFocus(true,pos);
                                        setItemInitView(myView);
                                        return false;
                                }
                                return false;
                            }
                        });

                        mWifiListView.setDescendantFocusability(ViewGroup.FOCUS_AFTER_DESCENDANTS);
                        EditText editText = (EditText) view.findViewById(R.id.et_password);
                        editText.requestFocus();
                        editText.setOnKeyListener(new View.OnKeyListener() {
                            @Override
                            public boolean onKey(View v, int keyCode, KeyEvent event) {
                                View myView = ((View) v.getParent().getParent().getParent());
                                switch (keyCode) {
                                    case KeyEvent.KEYCODE_BACK:
      
                                    case KeyEvent.KEYCODE_DPAD_DOWN:
                                    	setButtonFocus(true,pos);
                                        setItemInitView(myView);

                                        return true;
                                    case KeyEvent.KEYCODE_DPAD_UP:
                                            setButtonFocus(true,pos);
                                        setItemInitView(myView);
                                        return false;
                                }
                                return false;
                            }
                        });

                        view.startAnimation(animation);
                    }
                });

            }
        });
        super.init();
    }

    private void showWifiSSID()
    {
        
        WifiInfo info = mWifiCtl.getWiFiInfo();
        if (info != null && info.getSSID() != null)
        {
            String ssid = info.getSSID();
            String str = mContext.getResources().getString(R.string.aleady_connect);
            mWirelessAutoStated.setText(str + ":" + ssid);
        }
    }

    private void wifiConnect(AP scanResult, String strPassword) {
        int networkSetupMethod = WiFiCtl.MANUAL;
        AP accessPoint = scanResult;
        String password = strPassword;
        String ssid = scanResult.ssid;
        int accessPointSecurity = scanResult.getSecurity();

        mWifiCtl.forgetConnecting();
        mWifiCtl.setAccessPoint(scanResult);
        mWifiCtl.submit(networkSetupMethod, accessPoint, password, ssid, accessPointSecurity,
                null, null, null, null, null, null, null, true);
    }

    public String getEthIP() {
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

    private void setItemInitView(View v)
    {
        editing = false;
        password.setVisibility(View.INVISIBLE);
        txt.setVisibility(View.VISIBLE);
        mWifiListView.setDescendantFocusability(ViewGroup.FOCUS_BEFORE_DESCENDANTS);
        v.startAnimation(animation);
        v.setBackgroundDrawable(null);
    }
    private void showDhcpIP() {
        try {
            mConnectivityManager = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);

            String mIP = mConnectivityManager
                    .getLinkProperties(ConnectivityManager.TYPE_ETHERNET)
                    .getAddresses().toString();
            String[] arrIP = mIP.split("/|\\[|\\]| ");

            if (arrIP.length >= 3) {
                String str = mContext.getResources().getString(R.string.aleady_connect) +":"+ arrIP[2];
		mWiredAutoStated.setVisibility(View.VISIBLE);
                mWiredAutoStated.setText(str);
                Log.i(TAG, "net ipAddress" + arrIP[2]);
            }
        } catch (NullPointerException e) {
            Log.w("NetworkSetupActivity", "can not get IP" + e);
        }
    }

    private void showStaticIP() {

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

                mWiredAutoStated.setText(R.string.connect_fail);
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

    @Override
    public void onResult(int wifiState, ArrayList<AP> list) {
        Log.v(TAG, wifiState + "  " + list);

        switch (wifiState) {
            case WifiManager.WIFI_STATE_ENABLED:
                if (editing != true) {
                    if (mAccessPoint == null) {
                        mAccessPoint = list;
                        WifiAdapter wifiAdapter = new WifiAdapter(mContext,
                                mAccessPoint, mWifiCtl);
                        mWifiListView.setAdapter(wifiAdapter);
                        mWifiListView.requestFocus();
                        wifiAdapter.notifyDataSetChanged();
                    } else {
                        boolean foundSame = false;
                        ArrayList<AP> tmpList = new ArrayList<AP>();
                        for (AP accessPoint : list) {
                            foundSame = false;
                            for (AP ap : mAccessPoint) {
                                if (ap.ssid.equals(accessPoint.ssid)
                                        && (accessPoint.bssid == null
                                        || ap.bssid == null
                                        || ap.bssid.equals(accessPoint.bssid))) {
                                    int pos = mAccessPoint.indexOf(ap);
                                    mAccessPoint.set(pos, accessPoint);
                                    foundSame = true;
                                    break;
                                }
                            }
                            if (foundSame) {
                                continue;
                            } else {
                                tmpList.add(accessPoint);
                            }
                        }
                        if (tmpList.size() != 0) {
                            mAccessPoint.addAll(tmpList);
                        }
                        ((BaseAdapter) mWifiListView.getAdapter()).notifyDataSetChanged();
                    }
                }
                break;
            case WifiManager.WIFI_STATE_ENABLING:
                Log.v(TAG, "WifiManager.WIFI_STATE_ENABLING");
                break;

            case WifiManager.WIFI_STATE_DISABLING:
                Log.v(TAG, "WifiManager.WIFI_STATE_DISABLING");
                break;

            case WifiManager.WIFI_STATE_DISABLED:
                Log.v(TAG, "WifiManager.WIFI_STATE_DISABLED");
                break;

            default:
                break;
        }
    }

    private void setButtonFocus(boolean b , int pos)
    {
        if(pos == 0)
        {
            mAutoWirelessBtn.setFocusable(b);
            mAutoWiredBtn.setFocusable(b);
        }
    }
    @Override
    public void notifyChanged(int level, WifiConfiguration config) {
    }

    @Override
    public void notifyChanged(DetailedState status, WifiConfiguration config) {
        if(status != null)
        {
            if (status.toString().equals("CONNECTED"))
            {

                mWirelessAutoStated.setVisibility(View.VISIBLE);
//                if(mWifiCtl.isConnected() != true)
//                {
//                    mWifiListView.setVisibility(View.GONE);
//                }

                String str = mContext.getResources().getString(R.string.aleady_connect)+":"+ config.SSID.substring(1,config.SSID.length()-1);
                mWirelessAutoStated.setText(str);
                //mWirelessAutoStated.setText(R.string.aleady_connect+config.SSID);
            }
            else if (status.toString().equals("CONNECTING"))
            {
                mWirelessAutoStated.setVisibility(View.VISIBLE);
		String str = mContext.getResources().getString(R.string.connecting)+":"+ config.SSID.substring(1,config.SSID.length()-1);
                mWirelessAutoStated.setText(str);
            }
            else if(status.toString().equals("OBTAINING_IPADDR"))
            {
                mWirelessAutoStated.setVisibility(View.VISIBLE);
                String str = mContext.getResources().getString(R.string.obtaining_ipaddr)+":"+ config.SSID.substring(1,config.SSID.length()-1);
                mWirelessAutoStated.setText(str);
            }
            else if(status.toString().equals("AUTHENTICATING"))
            {
                mWirelessAutoStated.setVisibility(View.VISIBLE);
                mWirelessAutoStated.setText(R.string.authentivating);
            }
            else if(status.toString().equals("DISCONNECTED"))
            {
            }
        }
    }
}
