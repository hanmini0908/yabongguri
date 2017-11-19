package com.yabongguri.dnflukelaidmap.tobul;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.yabongguri.dnflukelaidmap.MainActivity;
import com.yabongguri.dnflukelaidmap.R;

/**
 * Created by BitnaKeum on 2017-11-19.
 */

public class Success extends Activity {

    private ImageView mImgCong;
    private  AnimationDrawable mAniCong;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tb_success);

        mImgCong = (ImageView)findViewById(R.id.imgCong);
        mAniCong = (AnimationDrawable) mImgCong.getDrawable();
        mAniCong.start();
    }

    public void onClickGoHome(View v) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
