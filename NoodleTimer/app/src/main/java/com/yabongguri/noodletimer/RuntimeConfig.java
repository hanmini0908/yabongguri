package com.yabongguri.noodletimer;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

public class RuntimeConfig {
    private final static String PREFERENCE_FILE_NAME				= "noodle:preferences";

    public final static String PREFERENCE_LAMYUN				    = "lamyun";
    public final static String PREFERENCE_KAL  				        = "kal";
    public final static String PREFERENCE_JJOLMYUN				    = "jjolmyun";
    public final static String PREFERENCE_WOODONG				    = "woodong";
    public final static String PREFERENCE_PASTA				        = "pasta";
    public final static String PREFERENCE_SOMYUN				    = "somyun";
    public final static String PREFERENCE_DANGMYUN				    = "dangmyun";
    public final static String PREFERENCE_MINE		    		    = "mine";

    public final static int PREFERENCE_LAMYUN_DEFAULT		        = 240;
    public final static int PREFERENCE_KAL_DEFAULT  			    = 330;
    public final static int PREFERENCE_JJOLMYUN_DEFAULT			    = 180;
    public final static int PREFERENCE_WOODONG_DEFAULT				= 120;
    public final static int PREFERENCE_PASTA_DEFAULT				= 480;
    public final static int PREFERENCE_SOMYUN_DEFAULT				= 210;
    public final static int PREFERENCE_DANGMYUN_DEFAULT			    = 360;
    public final static int PREFERENCE_MINE_DEFAULT		    	    = 180;

    public final static String PREFERENCE_LAMYUN_SET_TIME		    = "lamyun_set_time";
    public final static String PREFERENCE_KAL_SET_TIME		        = "kal_set_time";
    public final static String PREFERENCE_JJOLMYUN_SET_TIME		    = "jjolmyun_set_time";
    public final static String PREFERENCE_WOODONG_SET_TIME		    = "woodong_set_time";
    public final static String PREFERENCE_PASTA_SET_TIME	        = "pasta_set_time";
    public final static String PREFERENCE_SOMYUN_SET_TIME		    = "somyun_set_time";
    public final static String PREFERENCE_DANGMYUN_SET_TIME		    = "dangmyun_set_time";
    public final static String PREFERENCE_MINE_SET_TIME    		    = "mine_set_time";

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
        return getPreferenceValue(context, PREFERENCE_LAMYUN);
    }

    public static int getKalPreference(Context context) {
        return getPreferenceValue(context, PREFERENCE_KAL);
    }

    public static int getJjolmyunPreference(Context context) {
        return getPreferenceValue(context, PREFERENCE_JJOLMYUN);
    }

    public static int getWoodongPreference(Context context) {
        return getPreferenceValue(context, PREFERENCE_WOODONG);
    }

    public static int getPastaPreference(Context context) {
        return getPreferenceValue(context, PREFERENCE_PASTA);
    }

    public static int getSomyunPreference(Context context) {
        return getPreferenceValue(context, PREFERENCE_SOMYUN);
    }

    public static int getDangmyunPreference(Context context) {
        return getPreferenceValue(context, PREFERENCE_DANGMYUN);
    }

    public static int getMinePreference(Context context) {
        return getPreferenceValue(context, PREFERENCE_MINE);
    }

    public static int getPreferenceValue(Context context, String key) {
        SharedPreferences oPreference = context.getSharedPreferences(PREFERENCE_FILE_NAME, Activity.MODE_PRIVATE);
        int nDefault = 0;
        switch (key) {
            case PREFERENCE_LAMYUN:
                nDefault = PREFERENCE_LAMYUN_DEFAULT;
                break;
            case PREFERENCE_KAL:
                nDefault = PREFERENCE_KAL_DEFAULT;
                break;
            case PREFERENCE_JJOLMYUN:
                nDefault = PREFERENCE_JJOLMYUN_DEFAULT;
                break;
            case PREFERENCE_WOODONG:
                nDefault = PREFERENCE_WOODONG_DEFAULT;
                break;
            case PREFERENCE_PASTA:
                nDefault = PREFERENCE_PASTA_DEFAULT;
                break;
            case PREFERENCE_SOMYUN:
                nDefault = PREFERENCE_SOMYUN_DEFAULT;
                break;
            case PREFERENCE_DANGMYUN:
                nDefault = PREFERENCE_DANGMYUN_DEFAULT;
                break;
            case PREFERENCE_MINE:
                nDefault = PREFERENCE_MINE_DEFAULT;
                break;
        }
        return  oPreference.getInt(key, nDefault);
    }

    public static long getPreferenceLongValue(Context context, String key) {
        SharedPreferences oPreference = context.getSharedPreferences(PREFERENCE_FILE_NAME, Activity.MODE_PRIVATE);
        return  oPreference.getLong(key, 0);
    }

    public static void setPreferenceValue(Context context, String key, int value) {
        SharedPreferences.Editor editor = context.getSharedPreferences(PREFERENCE_FILE_NAME, Activity.MODE_PRIVATE).edit();
        editor.putInt(key, value);
        editor.commit();
    }

    public static void setPreferenceValue(Context context, String key, Long value) {
        SharedPreferences.Editor editor = context.getSharedPreferences(PREFERENCE_FILE_NAME, Activity.MODE_PRIVATE).edit();
        editor.putLong(key, value);
        editor.commit();
    }

    public static void removePreferenceValue(Context context, String key) {
        SharedPreferences.Editor editor = context.getSharedPreferences(PREFERENCE_FILE_NAME, Activity.MODE_PRIVATE).edit();
        editor.remove(key);
        editor.commit();
    }
}
