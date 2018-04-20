package com.yabongguri.dnflukeraidmap.tobul;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
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
    private  AnimationDrawable mAniCong;

    private boolean mIsRaid;

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

        Intent intent = getIntent();
        if (intent != null) {
            mIsRaid = intent.getBooleanExtra("isRaid", true);
        }

        if (mIsRaid)
            mTvSuccess.setText("루크 레이드 성공을\n축하드립니다!");
        else
            mTvSuccess.setText("일반 루크 성공을\n축하드립니다!");
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