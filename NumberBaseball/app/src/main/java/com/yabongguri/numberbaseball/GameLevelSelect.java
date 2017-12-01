package com.yabongguri.numberbaseball;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;

/**
 * Created by BitnaKeum on 2017-11-23.
 */

public class GameLevelSelect extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_level);

        //화면 세로고정
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    public void onClick3(View v) {

    }

    public void onClick4(View v) {

    }

    public void onClick5(View v) {

    }
}
