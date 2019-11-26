package com.tool.cn.receiver;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.telephony.TelephonyManager;

import static android.telephony.TelephonyManager.NETWORK_TYPE_GSM;
import static android.telephony.TelephonyManager.NETWORK_TYPE_IWLAN;
import static android.telephony.TelephonyManager.NETWORK_TYPE_TD_SCDMA;

/**
 * author 张海洋
 * create on   2019/11/20 11:51
 * description   网络监听广播
 */

public class NetworkReceiver extends BroadcastReceiver {

    private NetworkCallback networkCallback;
    private ConnectivityManager.NetworkCallback callback;
    private Activity activity;
    ConnectivityManager connManager = null;

    WifiManager wifiManager = null;

    public NetworkReceiver(Activity activity, final NetworkCallback networkCallback) {
        this.networkCallback = networkCallback;
        this.activity = activity;
        connManager = (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);
        wifiManager = (WifiManager) activity.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            callback = new ConnectivityManager.NetworkCallback() {
                @Override
                public void onAvailable(Network network) {
                    //    WifiInfo wifiInfo = wifiManager.getConnectionInfo();
                    NetworkInfo activeNetworkInfo = connManager.getActiveNetworkInfo();
                    int type = activeNetworkInfo.getType();

                    //wifi
                    String wifiName = "";
                    if (type == ConnectivityManager.TYPE_WIFI) {
                        WifiInfo wifiInfo = wifiManager.getConnectionInfo();
                        wifiName = wifiInfo.getSSID();
                    } else if (type == ConnectivityManager.TYPE_MOBILE) {
                        switch (activeNetworkInfo.getSubtype()) {
                            case NETWORK_TYPE_GSM:
                            case TelephonyManager.NETWORK_TYPE_GPRS:
                            case TelephonyManager.NETWORK_TYPE_CDMA:
                            case TelephonyManager.NETWORK_TYPE_EDGE:
                            case TelephonyManager.NETWORK_TYPE_1xRTT:
                            case TelephonyManager.NETWORK_TYPE_IDEN:
                                wifiName = "2G";
                                break;

                            case NETWORK_TYPE_TD_SCDMA:
                            case TelephonyManager.NETWORK_TYPE_EVDO_A:
                            case TelephonyManager.NETWORK_TYPE_UMTS:
                            case TelephonyManager.NETWORK_TYPE_EVDO_0:
                            case TelephonyManager.NETWORK_TYPE_HSDPA:
                            case TelephonyManager.NETWORK_TYPE_HSUPA:
                            case TelephonyManager.NETWORK_TYPE_HSPA:
                            case TelephonyManager.NETWORK_TYPE_EVDO_B:
                            case TelephonyManager.NETWORK_TYPE_EHRPD:
                            case TelephonyManager.NETWORK_TYPE_HSPAP:
                                wifiName = "3G";
                                break;

                            case NETWORK_TYPE_IWLAN:
                            case TelephonyManager.NETWORK_TYPE_LTE:
                                wifiName = "4G";
                                break;
                            default:

                                String subtypeName = activeNetworkInfo.getSubtypeName();
                                if (subtypeName.equalsIgnoreCase("TD-SCDMA")
                                        || subtypeName.equalsIgnoreCase("WCDMA")
                                        || subtypeName.equalsIgnoreCase("CDMA2000")) {
                                    wifiName = "3G";
                                } else {
                                    wifiName = "UNKNOWN";
                                }
                                break;
                        }
                    } else {
                        wifiName = "UNKNOWN";
                    }
                    networkCallback.onAvailable(wifiName);

                    super.onAvailable(network);
                }

                @Override
                public void onLost(Network network) {
                    super.onLost(network);
                    networkCallback.onLost();
                }
            };
            connManager.registerDefaultNetworkCallback(callback);
        } else {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction(WifiManager.WIFI_STATE_CHANGED_ACTION);
            intentFilter.addAction(WifiManager.NETWORK_STATE_CHANGED_ACTION);
            intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
            activity.registerReceiver(this, intentFilter);
        }
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction() == ConnectivityManager.CONNECTIVITY_ACTION) {
            NetworkInfo activeNetworkInfo = intent.getParcelableExtra(ConnectivityManager.EXTRA_NETWORK_INFO);


            boolean connected = activeNetworkInfo.isConnected();
            if (connected) {
                int type = activeNetworkInfo.getType();
                //wifi
                String wifiName = "";
                if (type == ConnectivityManager.TYPE_WIFI) {
                    WifiInfo wifiInfo = wifiManager.getConnectionInfo();
                    wifiName = wifiInfo.getSSID();
                } else if (type == ConnectivityManager.TYPE_MOBILE) {
                    switch (activeNetworkInfo.getSubtype()) {
                        case NETWORK_TYPE_GSM:
                        case TelephonyManager.NETWORK_TYPE_GPRS:
                        case TelephonyManager.NETWORK_TYPE_CDMA:
                        case TelephonyManager.NETWORK_TYPE_EDGE:
                        case TelephonyManager.NETWORK_TYPE_1xRTT:
                        case TelephonyManager.NETWORK_TYPE_IDEN:
                            wifiName = "2G";
                            break;

                        case NETWORK_TYPE_TD_SCDMA:
                        case TelephonyManager.NETWORK_TYPE_EVDO_A:
                        case TelephonyManager.NETWORK_TYPE_UMTS:
                        case TelephonyManager.NETWORK_TYPE_EVDO_0:
                        case TelephonyManager.NETWORK_TYPE_HSDPA:
                        case TelephonyManager.NETWORK_TYPE_HSUPA:
                        case TelephonyManager.NETWORK_TYPE_HSPA:
                        case TelephonyManager.NETWORK_TYPE_EVDO_B:
                        case TelephonyManager.NETWORK_TYPE_EHRPD:
                        case TelephonyManager.NETWORK_TYPE_HSPAP:
                            wifiName = "3G";
                            break;

                        case NETWORK_TYPE_IWLAN:
                        case TelephonyManager.NETWORK_TYPE_LTE:
                            wifiName = "4G";
                            break;
                        default:

                            String subtypeName = activeNetworkInfo.getSubtypeName();
                            if (subtypeName.equalsIgnoreCase("TD-SCDMA")
                                    || subtypeName.equalsIgnoreCase("WCDMA")
                                    || subtypeName.equalsIgnoreCase("CDMA2000")) {
                                wifiName = "3G";
                            } else {
                                wifiName = "UNKNOWN";
                            }
                            break;
                    }
                } else {
                    wifiName = "UNKNOWN";
                }
                networkCallback.onAvailable(wifiName);


            } else {
                networkCallback.onLost();
            }
        }
    }

    /**
     * 取消网络监听
     *
     * @param activity
     */
    public void unRegister(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            ConnectivityManager connManager = (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);

            connManager.unregisterNetworkCallback(callback);
        } else {
            activity.unregisterReceiver(this);
        }
    }

    /**
     * 网络状态回调
     */
    public interface NetworkCallback {
        /**
         * 网络可用
         */
        void onAvailable(String wifiName);

        /**
         * 网络不可用，丢失连接
         */
        void onLost();

    }
}
