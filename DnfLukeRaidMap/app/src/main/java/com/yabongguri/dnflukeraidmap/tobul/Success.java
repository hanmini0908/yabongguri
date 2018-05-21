package com.yabongguri.dnflukeraidmap.tobul;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.yabongguri.dnflukeraidmap.Define;
import com.yabongguri.dnflukeraidmap.MainActivity;
import com.yabongguri.dnflukeraidmap.R;
import com.yabongguri.dnflukeraidmap.juji.Jj;

/**
 * Created by BitnaKeum on 2017-11-19.
 */

public class Success extends Activity {

    private TextView mTvSuccess;
    private ImageView mImgCong;
    private AnimationDrawable mAniCong;
    private Button mBtnGoJj;

    private int nRaidType;  // 0:일반루크, 1:루크레이드, 2:싱글레이드

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tb_success);

        if (Define.IS_SUCCESS_AD) {     //광고 없는 버전 만들기 위해서 추가해놓음
            AdView adView = (AdView) findViewById(R.id.adView);
            AdRequest adRequest = new AdRequest.Builder().build();
            adView.loadAd(adRequest);
        }

        mTvSuccess = (TextView)findViewById(R.id.tv_success);
        mImgCong = (ImageView)findViewById(R.id.imgCong);
        mAniCong = (AnimationDrawable) mImgCong.getDrawable();
        mAniCong.start();
        mBtnGoJj = (Button)findViewById(R.id.btn_go_jj);

        mBtnGoJj.setVisibility(View.INVISIBLE);

        Intent intent = getIntent();
        if (intent != null) {
            nRaidType = intent.getIntExtra("nRaidType", 0);
        }

        // 0:일반루크, 1:루크레이드, 2:싱글레이드
        if (nRaidType == 0)
            mTvSuccess.setText("일반 루크 성공을\n축하드립니다!");
        else {
            mTvSuccess.setText("루크 레이드 성공을\n축하드립니다!");
            if (nRaidType == 1)
                mBtnGoJj.setVisibility(View.VISIBLE);
        }
    }

    public void onClickGoHome(View v) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    public void onClickGoJj(View v) {
        Intent intent = new Intent(this, Jj.class);
        startActivity(intent);
    }
}