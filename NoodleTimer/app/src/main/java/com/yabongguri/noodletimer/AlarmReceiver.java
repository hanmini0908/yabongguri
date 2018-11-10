package com.yabongguri.noodletimer;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.PowerManager;
import android.util.Log;

import java.util.List;

/**
 * Created by JoHanmin on 2018-11-06.
 */

public class AlarmReceiver extends BroadcastReceiver {

    private static PowerManager.WakeLock sCpuWakeLock;

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e("hanmin", "AlarmReceiver" + intent.getStringExtra("select_item"));

        PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
        sCpuWakeLock = pm.newWakeLock(
                PowerManager.SCREEN_BRIGHT_WAKE_LOCK |
                        PowerManager.ACQUIRE_CAUSES_WAKEUP |
                        PowerManager.ON_AFTER_RELEASE, "");

        sCpuWakeLock.acquire();


        if (sCpuWakeLock != null) {
            sCpuWakeLock.release();
            sCpuWakeLock = null;
        }

        Intent finish = new Intent(context, FinishActivity.class);
        RuntimeConfig.removePreferenceValue(context, intent.getStringExtra("select_item_key"));
        finish.putExtra("select_item", intent.getStringExtra("select_item"));
        finish.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        context.startActivity(finish);
    }

//    private boolean isAppRunning(Context context){
//        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
//        List<RunningAppProcessInfo> procInfos = activityManager.getRunningAppProcesses();
//
//        for(int i = 0; i < procInfos.size(); i++){
//            RunningAppProcessInfo process = procInfos.get(i);
//            Log.e("hanmin", "AlarmReceiver isAppRunning : " + process.importance + " / " + process.processName);
//            if (process.importance == RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
//                if (process.processName.equals(context.getPackageName())) {
//                    return true;
//                }
//            }
//        }
//
//        return false;
//    }
}
