package com.yabongguri.dnflukelaidmap.tobul;

import android.app.Activity;
import android.os.Bundle;
import android.view.WindowManager;

import com.yabongguri.dnflukelaidmap.R;

/**
 * Created by BitnaKeum on 2017-11-15.
 */

public class Caligo extends Activity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tb_caligo);

        //Activity에 입력이 없어도 화면 꺼지지 않게 하기
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    }
}
