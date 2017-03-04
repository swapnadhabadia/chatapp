package com.app.haptikchat;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by swapna on 4/3/17.
 */
public class Connectivity {
    public static NetworkInfo getNetworkInfo(Context context){
        ConnectivityManager cm = null;
        try {
            cm = (ConnectivityManager) context.getSystemService(context.CONNECTIVITY_SERVICE);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cm.getActiveNetworkInfo();
    }

    /**
     * Check if there is any connectivity
     * @param context
     * @return
     */
    public static boolean isConnected(Context context){
        NetworkInfo info = Connectivity.getNetworkInfo(context);
        return (info != null && info.isConnected());
    }
}
