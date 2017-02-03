package com.rba.botdemo.util;

import android.text.TextUtils;

/**
 * Created by Ricardo Bravo on 3/02/17.
 */

public class Util {

    public static boolean validMessage(String message){
        return !TextUtils.isEmpty(message);
    }

}
