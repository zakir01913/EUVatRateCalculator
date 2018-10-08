package com.zakir.euvatcalculation.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class DeviceUtils {

    private DeviceUtils() {
    }

    public static float getDeviceDensity(Context context) {
        return context.getResources().getDisplayMetrics().density;
    }

    public static boolean isThereInternetConnection(Context context) {
        boolean isConnected;

        ConnectivityManager connectivityManager =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        isConnected = (networkInfo != null && networkInfo.isConnectedOrConnecting());

        return isConnected;
    }
}
