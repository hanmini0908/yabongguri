package com.yabongguri.noodletimer;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class TimeSetActivity extends AppCompatActivity {
    private TextView mTvMinute;
    private TextView mTvSecond;
    private Button mStart;
    private String selectItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_set);

        mTvMinute = (TextView) findViewById(R.id.tv_minute);
        mTvSecond = (TextView) findViewById(R.id.tv_second);
        mStart = (Button) findViewById(R.id.b_start_timer);

        Intent intent = new Intent(getIntent());
        selectItem = intent.getStringExtra("select_item");
        Log.e("hanmin", selectItem);
        setTimeValues(selectItem);

        mStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent alarmIntent = new Intent("AlarmService");
                PendingIntent sender = PendingIntent.getBroadcast(getApplicationContext(), 0, alarmIntent, 0);
                long currentTime = SystemClock.elapsedRealtime();
                currentTime = currentTime + (10 * 1000); //10초 후 알람 이벤트 발생
                AlarmManager am = (AlarmManager)getSystemService(ALARM_SERVICE);
                if(Build.VERSION.SDK_INT < Build.VERSION_CODES.M){
                    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                        //API 19 이상 API 23미만
                        am.setExact(AlarmManager.ELAPSED_REALTIME_WAKEUP, currentTime, sender) ;
                    } else {
                        //API 19미만
                        am.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, currentTime, sender);
                    }
                } else {
                    //API 23 이상
                    am.setExactAndAllowWhileIdle(AlarmManager.ELAPSED_REALTIME_WAKEUP, currentTime, sender);
                }
            }
        });
    }

    private void setTimeValues(String selectItem) {
        int timeValue = RuntimeConfig.getPreferenceValue(getApplicationContext(), selectItem, 0);
        Log.e("hanmin", String.valueOf(timeValue));
        mTvMinute.setText(String.valueOf(timeValue / 60));
        mTvSecond.setText(String.valueOf(timeValue % 60));
    }
}
