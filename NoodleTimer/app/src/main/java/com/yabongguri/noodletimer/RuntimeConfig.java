package com.yabongguri.noodletimer;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class RuntimeConfig {
    private final static String PREFERENCE_FILE_NAME				= "noodle:preferences";

    public final static String PREFERENCE_LAMYUN				    = "lanmyun";
    public final static String PREFERENCE_KAL  				        = "kal";
    public final static String PREFERENCE_JJOLMYUN				    = "jjolmyun";
    public final static String PREFERENCE_WOODONG				    = "woodong";
    public final static String PREFERENCE_PASTA				        = "pasta";
    public final static String PREFERENCE_SOMYUN				    = "somyun";
    public final static String PREFERENCE_DANGMYUN				    = "dangmyun";
    public final static String PREFERENCE_MINE		    		    = "mine";
    public final static int PREFERENCE_LAMYUN_DEFAULT		        = 270;
    public final static int PREFERENCE_KAL_DEFAULT  			    = 370;
    public final static int PREFERENCE_JJOLMYUN_DEFAULT			= 400;
    public final static int PREFERENCE_WOODONG_DEFAULT				= 350;
    public final static int PREFERENCE_PASTA_DEFAULT				= 480;
    public final static int PREFERENCE_SOMYUN_DEFAULT				= 350;
    public final static int PREFERENCE_DANGMYUN_DEFAULT			= 420;
    public final static int PREFERENCE_MINE_DEFAULT		    	= 100;

    public static void setLamyunPreference(Context context, int value) {
        setPreferenceValue(context, PREFERENCE_LAMYUN, value);
    }

    public static void setKalPreference(Context context, int value) {
        setPreferenceValue(context, PREFERENCE_KAL, value);
    }

    public static void setJjolmyunPreference(Context context, int value) {
        setPreferenceValue(context, PREFERENCE_JJOLMYUN, value);
    }

    public static void setWoodongPreference(Context context, int value) {
        setPreferenceValue(context, PREFERENCE_WOODONG, value);
    }

    public static void setPastaPreference(Context context, int value) {
        setPreferenceValue(context, PREFERENCE_PASTA, value);
    }

    public static void setSomyunPreference(Context context, int value) {
        setPreferenceValue(context, PREFERENCE_SOMYUN, value);
    }

    public static void setDangmyunPreference(Context context, int value) {
        setPreferenceValue(context, PREFERENCE_DANGMYUN, value);
    }

    public static void setMinePreference(Context context, int value) {
        setPreferenceValue(context, PREFERENCE_MINE, value);
    }

    public static int getLamyunPreference(Context context) {
        return getPreferenceValue(context, PREFERENCE_LAMYUN, PREFERENCE_LAMYUN_DEFAULT);
    }

    public static int getKalPreference(Context context) {
        return getPreferenceValue(context, PREFERENCE_KAL, PREFERENCE_KAL_DEFAULT);
    }

    public static int getJjolmyunPreference(Context context) {
        return getPreferenceValue(context, PREFERENCE_JJOLMYUN, PREFERENCE_JJOLMYUN_DEFAULT);
    }

    public static int getWoodongPreference(Context context) {
        return getPreferenceValue(context, PREFERENCE_WOODONG, PREFERENCE_WOODONG_DEFAULT);
    }

    public static int getPastaPreference(Context context) {
        return getPreferenceValue(context, PREFERENCE_PASTA, PREFERENCE_PASTA_DEFAULT);
    }

    public static int getSomyunPreference(Context context) {
        return getPreferenceValue(context, PREFERENCE_SOMYUN, PREFERENCE_SOMYUN_DEFAULT);
    }

    public static int getDangmyunPreference(Context context) {
        return getPreferenceValue(context, PREFERENCE_DANGMYUN, PREFERENCE_DANGMYUN_DEFAULT);
    }

    public static int getMinePreference(Context context) {
        return getPreferenceValue(context, PREFERENCE_MINE, PREFERENCE_MINE_DEFAULT);
    }

    public static int getPreferenceValue(Context context, String key, int nDefault) {
        SharedPreferences oPreference = context.getSharedPreferences(PREFERENCE_FILE_NAME, Activity.MODE_PRIVATE);
        return  oPreference.getInt(key, nDefault);
    }

    public static void setPreferenceValue(Context context, String key, int value) {
        SharedPreferences.Editor editor = context.getSharedPreferences(PREFERENCE_FILE_NAME, Activity.MODE_PRIVATE).edit();
        editor.putInt(key, value);
        editor.commit();
    }
}
