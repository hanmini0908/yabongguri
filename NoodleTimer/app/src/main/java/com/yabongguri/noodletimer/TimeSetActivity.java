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
    private String selectItemKey;
    private int timeValue;
    private Thread timer;
    private LinearLayout mParentLayout;
    private Button mSetTime;

    private final int LAMYUN_ALARM_ID = 1;
    private final int KAL_ALARM_ID = 2;
    private final int JJOLMYUN_ALARM_ID = 3;
    private final int WOODONG_ALARM_ID = 4;
    private final int PASTA_ALARM_ID = 5;
    private final int SOMYUN_ALARM_ID = 6;
    private final int DANGMYUN_ALARM_ID = 7;
    private final int MINE_ALARM_ID = 8;

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
        selectItemKey = intent.getStringExtra("select_item_key");

        setTimeValue(selectItem, selectItemKey);
        displayTimeValues(timeValue);

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

                    if (checkSetAlarm(selectItem) == false) {
                        setAlarmManager(timeValue, selectItem);
                    }

                    mTrigger.setText(getString(R.string.stop_button));

                    timer = getTimeThread();
                    timer.start();

                    setUseableEditText(mTvMinute, false);
                    setUseableEditText(mTvSecond, false);
                    setUseableButton(mSetTime, false);
                } else {
                    threadStop();
                    cancelAlarmManager(getAlarmId(selectItem));
                    mTrigger.setText(getString(R.string.start_button));

                    setUseableEditText(mTvMinute, true);
                    setUseableEditText(mTvSecond, true);
                    setUseableButton(mSetTime, true);
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
                        displayTimeValues(timeValue);
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
                        displayTimeValues(timeValue);
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

    private void setTimeValue(String selectItem, String selectItemKey) {
        boolean isStart = false;
        long currentTime = SystemClock.elapsedRealtime();
        long alarmTime = RuntimeConfig.getPreferenceLongValue(getApplicationContext(), selectItemKey);
        if (alarmTime - currentTime > 0) {
            Log.e("hanmin", "setTimeValue" + (alarmTime - currentTime));
            timeValue = (int)((alarmTime - currentTime) / 1000);
            Log.e("hanmin", "setTimeValue" + timeValue);
            isStart = true;
        } else {
            timeValue = RuntimeConfig.getPreferenceValue(getApplicationContext(), selectItem);
        }

        displayTimeValues(timeValue);

        if (isStart) {
            try {
                Thread.sleep((alarmTime - currentTime) % 1000);
                mTrigger.setText(getString(R.string.stop_button));

                timer = getTimeThread();
                timer.start();

                setUseableEditText(mTvMinute, false);
                setUseableEditText(mTvSecond, false);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void displayTimeValues(int time) {
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

    private void setUseableButton(Button button, boolean useable) {
        button.setClickable(useable);
        button.setEnabled(useable);
        button.setFocusable(useable);
        button.setFocusableInTouchMode(useable);
    }

    private Thread getTimeThread() {
        final Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            public void run() {
                while (!Thread.interrupted()) {
                    try {
                        Thread.sleep(1000);

                        handler.post(new Runnable(){
                            public void run() {
                                if (timeValue == 0) {
                                    timer.interrupt();
                                    mTrigger.setText(getString(R.string.start_button));

                                    setUseableEditText(mTvMinute, true);
                                    setUseableEditText(mTvSecond, true);
                                }

                                timeValue = Math.max(timeValue - 1, 0);
                                displayTimeValues(timeValue);
                            }
                        });
                    }
                    catch (InterruptedException e) {
                        e.printStackTrace();
                        timer.interrupt();
                    }
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

    private void setAlarmManager(int time, String selectItem) {
        Intent alarmIntent = new Intent(getApplicationContext(), AlarmReceiver.class);
        long currentTime = SystemClock.elapsedRealtime();
        currentTime = currentTime + (time * 1000); // n초 후 알람 이벤트 발생
        Log.e("hanmin", "setAlarmManager!! " + currentTime);
        alarmIntent.putExtra("set_time", currentTime);
        alarmIntent.putExtra("select_item_key", selectItemKey);
        RuntimeConfig.setPreferenceValue(getApplicationContext(), selectItemKey, currentTime);  // 설정 시간 preference 에 저장
        alarmIntent.putExtra("select_item", selectItem);
        PendingIntent sender = PendingIntent.getBroadcast(getApplicationContext(), getAlarmId(selectItem), alarmIntent, PendingIntent.FLAG_UPDATE_CURRENT);
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

    private boolean checkSetAlarm(String selectItem) {
        Intent intent = new Intent(getApplicationContext(), AlarmReceiver.class);
        PendingIntent sender = PendingIntent.getBroadcast(getApplicationContext(), getAlarmId(selectItem), intent, PendingIntent.FLAG_NO_CREATE);

        if (sender == null) {
            return false;   // 알람 없음
        } else {
            return true;    // 알람 있음
        }
    }

    private void cancelAlarmManager(int alarmId) {
        Log.e("hanmin", "cancelAlarmManager");
        AlarmManager am = (AlarmManager)getApplicationContext().getSystemService(ALARM_SERVICE);
        Intent intent = new Intent(this, AlarmReceiver.class);
        PendingIntent sender = PendingIntent.getBroadcast(getApplicationContext(), alarmId, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        if (sender != null) {
            am.cancel(sender);
            sender.cancel();
            RuntimeConfig.removePreferenceValue(getApplicationContext(), selectItemKey);
        }
    }

    private int getAlarmId(String selectItem) {
        int alarmId = -1;

        if (selectItem.equals(RuntimeConfig.PREFERENCE_LAMYUN)) {
            alarmId = LAMYUN_ALARM_ID;
        } else if (selectItem.equals(RuntimeConfig.PREFERENCE_KAL)) {
            alarmId = KAL_ALARM_ID;
        } else if (selectItem.equals(RuntimeConfig.PREFERENCE_JJOLMYUN)) {
            alarmId = JJOLMYUN_ALARM_ID;
        } else if (selectItem.equals(RuntimeConfig.PREFERENCE_WOODONG)) {
            alarmId = WOODONG_ALARM_ID;
        } else if (selectItem.equals(RuntimeConfig.PREFERENCE_PASTA)) {
            alarmId = PASTA_ALARM_ID;
        } else if (selectItem.equals(RuntimeConfig.PREFERENCE_SOMYUN)) {
            alarmId = SOMYUN_ALARM_ID;
        } else if (selectItem.equals(RuntimeConfig.PREFERENCE_DANGMYUN)) {
            alarmId = DANGMYUN_ALARM_ID;
        } else if (selectItem.equals(RuntimeConfig.PREFERENCE_MINE)) {
            alarmId = MINE_ALARM_ID;
        }

        return alarmId;
    }

    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager =(InputMethodManager)getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
