package com.zakir.euvatcalculation.utils;

import android.content.Context;

public class DeviceUtils {

    private DeviceUtils() {
    }

    public static float getDeviceDensity(Context context) {
        return context.getResources().getDisplayMetrics().density;
    }
}
