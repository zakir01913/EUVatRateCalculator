package com.zakir.euvatcalculation.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.zakir.euvatcalculation.di.ApplicationContext;

import javax.inject.Inject;

public class DeviceUtils {

    private Context context;

    @Inject
    public DeviceUtils(@ApplicationContext Context context) {
        this.context = context;
    }

    public float getDeviceDensity() {
        return context.getResources().getDisplayMetrics().density;
    }

    public boolean isThereInternetConnection() {
        boolean isConnected;

        ConnectivityManager connectivityManager =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        isConnected = (networkInfo != null && networkInfo.isConnectedOrConnecting());

        return isConnected;
    }
}
