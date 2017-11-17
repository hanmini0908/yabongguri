package com.yabongguri.dnflukelaidmap;

import android.app.Activity;
import android.content.res.Configuration;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.net.HttpURLConnection;

/**
 * Created by JoHanmin on 2017-11-15.
 */

public class HellChannel extends Activity {

    ImageView mImg = null;
    AnimationDrawable mAni = null;
    TextView mTextView = null;
    Handler mHandler = null;
    String mChannelTitle = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hell_channel);

        mTextView = (TextView)findViewById(R.id.htmlText);
        mImg = (ImageView)findViewById(R.id.img);
        mAni = (AnimationDrawable) mImg.getDrawable();

        mAni.start();

        if (savedInstanceState != null && savedInstanceState.getString("Channel") != null) {
            mChannelTitle = savedInstanceState.getString("Channel");
            mTextView.setText(mChannelTitle);
            mTextView.setTextSize(30);
        } else {
            mHandler = new SetTextViewHandler();

            Thread thread = new Thread(new ControlTime()); // Runnable 객체를 통해 작업 스레드 생성
            thread.start(); // 작업스레드 시작
        }
    }

    public void onSaveInstanceState(Bundle outState) {
        outState.putString("Channel", mChannelTitle);
        super.onSaveInstanceState(outState);
    }

    class SetTextViewHandler extends Handler{
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    mChannelTitle = msg.obj.toString();
                    mTextView.setText(mChannelTitle);
                    mTextView.setTextSize(30);
                    break;
            }
            super.handleMessage(msg);
        }
    }

    // Runnable 인터페이스를 구현한 클래스
    class ControlTime implements Runnable{
        HttpURLConnection http = null;
        BufferedReader in = null;
        // Thread 때와 마찬가지로 run() 메소드 구현
        @Override
        public void run() {
            try {
                Thread.sleep(1000 * 3);
                Message msg = mHandler.obtainMessage(1, getHellChannel());
                mHandler.sendMessage(msg);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } // end run()
    } // end class BackRunnable

    private String getHellChannel() {
        String title = "";
        int channel = ((int)(Math.random() * 152)) + 1;

        if (channel >= 1 && channel <= 15) {
            title = "그란플로리스 " + Integer.toString(channel);
        } else if (channel >= 16 && channel <= 25) {
            title = "하늘성 " + Integer.toString(channel - 15);
        } else if (channel >= 26 && channel <= 35) {
            title = "베히모스 " + Integer.toString(channel - 25 + 10);
        } else if (channel >= 36 && channel <= 40) {
            title = "알프라이라 " + Integer.toString(channel - 35);
        } else if (channel >= 41 && channel <= 45) {
            title = "노이어페라 " + Integer.toString(channel - 40 + 5);
        } else if (channel >= 46 && channel <= 50) {
            title = "이브노바 " + Integer.toString(channel - 45 + 15);
        } else if (channel >= 51 && channel <= 55) {
            title = "멜트다운 " + Integer.toString(channel - 50 + 20);
        } else if (channel >= 56 && channel <= 60) {
            title = "역천의 폭포 " + Integer.toString(channel - 55 + 15);
        } else if (channel >= 61 && channel <= 70) {
            title = "안트베르 협곡 " + Integer.toString(channel - 60);
        } else if (channel >= 71 && channel <= 75) {
            title = "해상열차 " + Integer.toString(channel - 70);
        } else if (channel >= 76 && channel <= 85) {
            title = "시간의 문 " + Integer.toString(channel - 75 + 10);
        } else if (channel >= 86 && channel <= 90) {
            title = "파워 스테이션 " + Integer.toString(channel - 85 + 20);
        } else if (channel >= 91 && channel <= 100) {
            title = "노블스카이 " + Integer.toString(channel - 90);
        } else if (channel >= 101 && channel <= 105) {
            title = "죽은자의 성 " + Integer.toString(channel - 100 + 10);
        } else if (channel >= 106 && channel <= 120) {
            title = "메트로센터 " + Integer.toString(channel - 105 + 29);
        } else if (channel >= 121 && channel <= 125) {
            title = "망자의협곡 " + Integer.toString(channel - 120 + 20);
        } else if (channel >= 126 && channel <= 135) {
            title = "이계던전 " + Integer.toString(channel - 125);
        } else if (channel >= 136 && channel <= 140) {
            title = "고대던전 " + Integer.toString(channel - 135 + 20);
        } else if (channel >= 141 && channel <= 142) {
            title = "거래 - 경매장 " + Integer.toString(channel - 140 + 60);
        } else if (channel >= 143 && channel <= 147) {
            title = "설산 " + Integer.toString(channel - 142 + 10);
        } else if (channel >= 148 && channel <= 152) {
            title = "노스마이어 " + Integer.toString(channel - 147 + 15);
        }

        return title;
    }
}
