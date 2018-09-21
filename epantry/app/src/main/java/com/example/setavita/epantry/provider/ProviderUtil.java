package com.example.setavita.epantry.provider;

import android.content.Context;
import android.util.Log;

public class ProviderUtil {
    private static final String TAG = "ProviderUtil";

    public ProviderUtil() {
    }

    public static String getFileProviderName(Context context) {
        Log.d(TAG, "getFileProviderName: " + context.getPackageName());
        return context.getPackageName() + ".provider";
    }

}
