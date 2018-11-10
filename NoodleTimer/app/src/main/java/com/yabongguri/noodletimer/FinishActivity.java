package com.yabongguri.noodletimer;

import android.content.Intent;
import android.os.Vibrator;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.Locale;

public class FinishActivity extends AppCompatActivity {
    private TextToSpeech mTts;
    private TextView mFinish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish);

        if (Define.IS_FINISH_AD) {
            AdView adView = (AdView) findViewById(R.id.adView);
            AdRequest adRequest = new AdRequest.Builder().build();
            adView.loadAd(adRequest);
        }

        mFinish = (TextView) findViewById(R.id.tv_finish);

        Intent intent = new Intent(getIntent());
        final String selectItem = intent.getStringExtra("select_item");

        mFinish.setText(getSpeechString(selectItem));

        Vibrator vibrator = (Vibrator)getSystemService(VIBRATOR_SERVICE);

        long[] pattern = {1000, 500, 1000, 500, 1000, 500, 1000, 500, 1000};
        vibrator.vibrate(pattern, -1);

        // TTS를 생성하고 OnInitListener로 초기화 한다.
        mTts = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != android.speech.tts.TextToSpeech.ERROR) {
                    // 언어를 선택한다.
                    mTts.setLanguage(Locale.KOREAN);
                    mTts.setPitch(2.0f);         // 음성 톤을 2.0배 올려준다.
                    mTts.setSpeechRate(1.0f);    // 읽는 속도는 기본 설정

                    mTts.speak(getSpeechString(selectItem), TextToSpeech.QUEUE_FLUSH, null);
                }
            }
        });
    }

    private String getSpeechString(String selectItem) {
        int resourceId = -1;

        if (selectItem.equals(RuntimeConfig.PREFERENCE_LAMYUN)) {
            resourceId = R.string.lamyun;
        } else if (selectItem.equals(RuntimeConfig.PREFERENCE_KAL)) {
            resourceId = R.string.kal;
        } else if (selectItem.equals(RuntimeConfig.PREFERENCE_JJOLMYUN)) {
            resourceId = R.string.jjolmyun;
        } else if (selectItem.equals(RuntimeConfig.PREFERENCE_WOODONG)) {
            resourceId = R.string.woodong;
        } else if (selectItem.equals(RuntimeConfig.PREFERENCE_PASTA)) {
            resourceId = R.string.pasta;
        } else if (selectItem.equals(RuntimeConfig.PREFERENCE_SOMYUN)) {
            resourceId = R.string.somyun;
        } else if (selectItem.equals(RuntimeConfig.PREFERENCE_DANGMYUN)) {
            resourceId = R.string.dangmyun;
        } else if (selectItem.equals(RuntimeConfig.PREFERENCE_MINE)) {
            resourceId = R.string.mine;
        }

        return getString(resourceId);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
