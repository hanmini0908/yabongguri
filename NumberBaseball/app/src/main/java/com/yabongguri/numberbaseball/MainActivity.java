package com.yabongguri.numberbaseball;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //화면 세로고정
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    public void onClickStart(View v) {
        Intent intent = new Intent(this, GameLevelSelect.class);
        startActivity(intent);
    }

    public void onClickRecord(View v) {

    }

    public void onClickEnd(View v) {
        ActivityCompat.finishAffinity(this);
    }
}
