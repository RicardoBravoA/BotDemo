package com.rba.botdemo.util;

import android.content.Context;
import android.text.TextUtils;
import android.util.DisplayMetrics;

/**
 * Created by Ricardo Bravo on 3/02/17.
 */

public class Util {

    public static boolean validMessage(String message){
        return !TextUtils.isEmpty(message);
    }

    public static int dpToPx(int dp, Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
    }

}
