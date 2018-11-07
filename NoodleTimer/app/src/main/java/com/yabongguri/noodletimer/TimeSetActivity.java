package com.yabongguri.noodletimer;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class TimeSetActivity extends AppCompatActivity {
    private EditText mTvMinute;
    private EditText mTvSecond;
    private Button mTrigger;
    private String selectItem;
    private int timeValue;
    private Thread timer;
    private LinearLayout mParentLayout;
    private Button mSetTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_set);

        if (Define.IS_Time_AD) {
            AdView adView = (AdView) findViewById(R.id.adView);
            AdRequest adRequest = new AdRequest.Builder().build();
            adView.loadAd(adRequest);
        }

        mTvMinute = (EditText) findViewById(R.id.tv_minute);
        mTvSecond = (EditText) findViewById(R.id.tv_second);
        mTrigger = (Button) findViewById(R.id.b_trigger);
        mParentLayout = (LinearLayout) findViewById(R.id.parent_layout);
        mSetTime = (Button) findViewById(R.id.b_set_time);

        Intent intent = new Intent(getIntent());
        selectItem = intent.getStringExtra("select_item");
        timeValue = RuntimeConfig.getPreferenceValue(getApplicationContext(), selectItem, 0);
        Log.e("hanmin", "first timeValue : " + String.valueOf(timeValue));
        setTimeValues(timeValue);

        mTrigger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mTrigger.getText().equals(getString(R.string.start_button))) {
                    if (mTvMinute.getText().length() == 0) {
                        mTvMinute.setText(String.valueOf(timeValue / 60));
                    }

                    if (mTvSecond.getText().length() == 0) {
                        mTvSecond.setText(String.valueOf(timeValue % 60));
                    }

                    timeValue = Integer.valueOf(mTvMinute.getText().toString()) * 60 + Integer.valueOf(mTvSecond.getText().toString());
//                    setAlarmManager(timeValue);
                    mTrigger.setText(getString(R.string.stop_button));

                    timer = getTimeThread();
                    timer.start();

                    setUseableEditText(mTvMinute, false);
                    setUseableEditText(mTvSecond, false);
                } else {
                    threadStop();
                    mTrigger.setText(getString(R.string.start_button));

                    setUseableEditText(mTvMinute, true);
                    setUseableEditText(mTvSecond, true);
                }
            }
        });

        mTvMinute.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                Log.e("hanmin mTvMinute", String.valueOf(hasFocus));
                if (hasFocus) {
                    mTvMinute.setText(null);
                } else {
                    Log.e("hanmin", String.valueOf(mTvMinute.getText().length()));
                    Log.e("hanmin", String.valueOf(mTvMinute.getText()));
                    if (mTvMinute.getText().length() == 0) {
                        mTvMinute.setText(String.valueOf(timeValue / 60));
                    } else {
                        timeValue = Math.min(3599, Integer.valueOf(mTvMinute.getText().toString()) * 60 + Integer.valueOf(mTvSecond.getText().toString()));
                        setTimeValues(timeValue);
                        hideKeyboard(v);
                    }
                }
            }
        });

        mTvMinute.setImeOptions(EditorInfo.IME_ACTION_DONE); // 키보드 확인 버튼 클릭시
        mTvMinute.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE)
                    mParentLayout.requestFocus(); // searchBtn 이란 Button 누르는 동작 실행
                return false;
            }
        });

        mTvSecond.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                Log.e("hanmin mTvSecond", String.valueOf(hasFocus));
                if (hasFocus) {
                    mTvSecond.setText(null);
                } else {
                    if (mTvSecond.getText().length() == 0) {
                        mTvSecond.setText(String.valueOf(timeValue % 60));
                    } else {
                        timeValue = Math.min(3599, Integer.valueOf(mTvMinute.getText().toString()) * 60 + Integer.valueOf(mTvSecond.getText().toString()));
                        setTimeValues(timeValue);
                        hideKeyboard(v);
                    }
                }
            }
        });

        mTvSecond.setImeOptions(EditorInfo.IME_ACTION_DONE); // 키보드 확인 버튼 클릭시
        mTvSecond.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE)
                    mParentLayout.requestFocus(); // searchBtn 이란 Button 누르는 동작 실행
                return false;
            }
        });

        mParentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideKeyboard(v);
            }
        });

        mSetTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RuntimeConfig.setPreferenceValue(getApplicationContext(), selectItem, (Integer.valueOf(mTvMinute.getText().toString()) * 60 + Integer.valueOf(mTvSecond.getText().toString())));
                Log.e("hanmin", "set time!!!" + (Integer.valueOf(mTvMinute.getText().toString()) * 60 + Integer.valueOf(mTvSecond.getText().toString())));
                Toast.makeText(getApplicationContext(), getString(R.string.set_time_toast), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        threadStop();
    }

    private void setTimeValues(int time) {
        mTvMinute.setText(String.valueOf(time / 60));
        mTvSecond.setText(String.valueOf(time % 60));
    }

    private void setUseableEditText(EditText et, boolean useable) {
        et.setClickable(useable);
        et.setEnabled(useable);
        et.setFocusable(useable);
        et.setFocusableInTouchMode(useable);
        et.setCursorVisible(useable);
    }

    private Thread getTimeThread() {
        final Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            public void run() {
                while (!Thread.interrupted()) {
                    try {
                        Thread.sleep(1000);
                    }
                    catch (InterruptedException e) {
                        e.printStackTrace();
                        timer.interrupt();
                    }
                    handler.post(new Runnable(){
                        public void run() {
                            timeValue = timeValue - 1;
                            setTimeValues(timeValue);
                        }
                    });
                }
            }
        };
        return new Thread(runnable);
    }

    private void threadStop() {
        try {
            if (timer != null) {
                timer.interrupt();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setAlarmManager(int time) {
        Intent alarmIntent = new Intent("AlarmService");
        PendingIntent sender = PendingIntent.getBroadcast(getApplicationContext(), 0, alarmIntent, 0);
        long currentTime = SystemClock.elapsedRealtime();
        currentTime = currentTime + (time * 1000); //10초 후 알람 이벤트 발생
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

    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager =(InputMethodManager)getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
