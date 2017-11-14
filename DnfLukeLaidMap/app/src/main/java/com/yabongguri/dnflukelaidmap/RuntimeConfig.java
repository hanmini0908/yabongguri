package com.yabongguri.dnflukelaidmap;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class RuntimeConfig {
    private final static String PREFERENCE_FILE_NAME				= "dnflukelaidmap:preferences";
    private final static String PREFERENCE_CARD						= "key_card";

    public static void setCardPreference(Context context, int value)
    {
        SharedPreferences.Editor editor = context.getSharedPreferences(PREFERENCE_FILE_NAME, Activity.MODE_PRIVATE).edit();
        editor.putInt(PREFERENCE_CARD, value);
        editor.commit();
    }

    public static int getCardPreference(Context context)
    {
        SharedPreferences oPreference = context.getSharedPreferences(PREFERENCE_FILE_NAME, Activity.MODE_PRIVATE);
        return  oPreference.getInt(PREFERENCE_CARD, -1);
    }
}