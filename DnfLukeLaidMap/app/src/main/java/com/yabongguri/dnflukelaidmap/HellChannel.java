package com.yabongguri.dnflukelaidmap;

import android.app.Activity;
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

    ImageView mImg;
    AnimationDrawable mAni;
    TextView mTextView;
    Handler mHandler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hell_channel);

        mTextView = (TextView)findViewById(R.id.htmlText);
        mImg =(ImageView)findViewById(R.id.img);
        mAni =(AnimationDrawable) mImg.getDrawable();

        mAni.start();

        mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case 1:
                        mTextView.setText(msg.obj.toString());
                        break;
                }
                super.handleMessage(msg);
            }
        };

        Thread thread = new Thread(new BackRunnable()); // Runnable 객체를 통해 작업 스레드 생성
        thread.start(); // 작업스레드 시작

    }

    // Runnable 인터페이스를 구현한 클래스
    class BackRunnable implements Runnable{
        HttpURLConnection http = null;
        BufferedReader in = null;
        // Thread 때와 마찬가지로 run() 메소드 구현
        @Override
        public void run() {
            try {
                Thread.sleep(1000 * 3);
                Message msg = mHandler.obtainMessage(1, getRandom());
                mHandler.sendMessage(msg);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } // end run()
    } // end class BackRunnable

    private int getRandom() {
        return ((int)(Math.random() * 100)) + 1;
    }
}
