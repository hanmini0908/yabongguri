package com.yabongguri.dnflukeraidmap.juji;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.content.Intent;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.yabongguri.dnflukeraidmap.Define;
import com.yabongguri.dnflukeraidmap.R;
import com.yabongguri.dnflukeraidmap.RuntimeConfig;
import com.yabongguri.dnflukeraidmap.tobul.Tb;

/**
 * Created by BitnaKeum on 2017-11-13.
 */

public class Pppt extends Activity {

    private TextView mTv_pppt;
    private TextView mTv_named;
    private TextView mTv_hard;
    private ImageView mIv_pppt;
    private Button mBtn_prev;
    private Button mBtn_next;
    private Button mBtn_others;
    private Button mBtn_tb;

    private int mMapIndex;
    private int mCardPatternIndex;
    private boolean mIsOthers;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jj_pppt);

        if (Define.IS_MAP_AD) {     //광고 없는 버전 만들기 위해서 추가해놓음
            AdView adView = (AdView) findViewById(R.id.adView);
            AdRequest adRequest = new AdRequest.Builder().build();
            adView.loadAd(adRequest);
        }

        //Activity에 입력이 없어도 화면 꺼지지 않게 하기
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        mTv_pppt = (TextView)findViewById(R.id.tv_pppt);
        mTv_named = (TextView)findViewById(R.id.tv_pppt_named);
//        mTv_hard = (TextView)findViewById(R.id.tv_pppt_hard);
        mIv_pppt = (ImageView)findViewById(R.id.iv_pppt);
        mBtn_prev = (Button)findViewById(R.id.btn_pppt_prev);
        mBtn_next = (Button)findViewById(R.id.btn_pppt_next);
        mBtn_others = (Button)findViewById(R.id.btn_pppt_others);
        mBtn_tb = (Button)findViewById(R.id.btn_pppt_tb);

        mCardPatternIndex = RuntimeConfig.getCardPreference(this);

        if (savedInstanceState == null) {
            mMapIndex = 0;
            mIsOthers = false;

            Intent intent = getIntent();
            if (intent != null) {
                mMapIndex = intent.getIntExtra("mapIndex", 0);
                mIsOthers = intent.getBooleanExtra("isOthers", false);
            }
        } else {
            mMapIndex = savedInstanceState.getInt("MapIndex");
        }

        viewMapIndex();
    }

    public void onSaveInstanceState(Bundle outState) {
        outState.putInt("MapIndex", mMapIndex);
        super.onSaveInstanceState(outState);
    }

    private void viewMapIndex() {
        if (mMapIndex == 0)
            pa1();
        else if (mMapIndex == 1)
            pa2();
        else if (mMapIndex == 2)
            pa3();
        else if (mMapIndex == 3)
            tan3();
    }

    public void onClickPrev(View v) {
        if (mMapIndex > 0)
            mMapIndex--;

        viewMapIndex();
    }

    public void onClickNext(View v) {
        if (mMapIndex < 3)
            mMapIndex++;

        viewMapIndex();
    }

    public void onClickOthersMap(View v) {
        finish();
        Intent intent = new Intent(this, Others.class);
        startActivity(intent);
    }

    public void onClickTb(View v) {
        Intent intent = new Intent(this, Tb.class);
        startActivity(intent);
    }

    private void setBtnVisibility(boolean isLastMap) {
        if (!mIsOthers) {
            if (!isLastMap) {
                mBtn_prev.setVisibility(View.VISIBLE);
                mBtn_next.setVisibility(View.VISIBLE);
                mBtn_others.setVisibility(View.INVISIBLE);
                mBtn_tb.setVisibility(View.GONE);
            }
            else {
                mBtn_prev.setVisibility(View.VISIBLE);
                mBtn_next.setVisibility(View.GONE);
                mBtn_others.setVisibility(View.VISIBLE);
                mBtn_tb.setVisibility(View.VISIBLE);
            }
        }
        else
        {
            mBtn_prev.setVisibility(View.INVISIBLE);
            mBtn_next.setVisibility(View.GONE);
            mBtn_others.setVisibility(View.VISIBLE);
            mBtn_tb.setVisibility(View.VISIBLE);
        }
    }

    private void pa1() {
        mBtn_prev.setEnabled(false);
        setBtnVisibility(false);

        String strMap = getString(R.string.jj_p);
        mTv_pppt.setText(strMap + " 1");

//        String strHard = getString(R.string.hard_p);
//        mTv_hard.setText(strHard);

        String strMst1 = getString(R.string.mst_red);    //카드패턴 0, 2
        String strBoss = getString(R.string.mst_nerbe);  //카드패턴 0, 2
        int nDrawableId = R.drawable.map_pa1_0;    //카드패턴 0, 2

        switch (mCardPatternIndex) {
            case 1:
                strMst1 = getString(R.string.mst_beki);  //카드패턴 1
                strBoss = getString(R.string.mst_ramp);  //카드패턴 1, 4
                nDrawableId = R.drawable.map_pa1_1;    //카드패턴 1
                break;
            case 3:
                strMst1 = getString(R.string.mst_karina);  //카드패턴 3
                strBoss = getString(R.string.mst_red);     //카드패턴 3
                nDrawableId = R.drawable.map_pa1_3;    //카드패턴 3
                break;
            case 4:
                strMst1 = getString(R.string.mst_iron);  //카드패턴 4
                strBoss = getString(R.string.mst_ramp);  //카드패턴 1, 4
                nDrawableId = R.drawable.map_pa1_4;    //카드패턴 4
                break;
        }

        mTv_named.setText("1. " + strMst1 + "\nB. " + strBoss);
        mIv_pppt.setImageResource(nDrawableId);
    }

    private void pa2() {
        mBtn_prev.setEnabled(true);
        setBtnVisibility(false);

        String strMap = getString(R.string.jj_p);
        mTv_pppt.setText(strMap + " 2");

//        String strHard = getString(R.string.hard_p);
//        mTv_hard.setText(strHard);

        String strMst1 = getString(R.string.mst_argos);  //카드패턴 0, 2
        String strBoss = getString(R.string.mst_mark);
        int nDrawableId = R.drawable.map_pa2_0;    //카드패턴 0, 2

        switch (mCardPatternIndex) {
            case 1:
                strMst1 = getString(R.string.mst_jakel);  //카드패턴 1
                nDrawableId = R.drawable.map_pa2_1;    //카드패턴 1
                break;
            case 3:
                strMst1 = getString(R.string.mst_iron);  //카드패턴 3
                nDrawableId = R.drawable.map_pa2_3;    //카드패턴 3
                break;
            case 4:
                strMst1 = getString(R.string.mst_nerbe);  //카드패턴 4
                nDrawableId = R.drawable.map_pa2_4;    //카드패턴 4
                break;
        }

        mTv_named.setText("1. " + strMst1 + "\nB. " + strBoss);
        mIv_pppt.setImageResource(nDrawableId);
    }

    private void pa3() {
        mBtn_prev.setEnabled(true);
        setBtnVisibility(false);

        String strMap = getString(R.string.jj_p);
        mTv_pppt.setText(strMap + " 3");

//        String strHard = getString(R.string.hard_p);
 //       mTv_hard.setText(strHard);

        String strBoss = getString(R.string.mst_bupon);
        int nDrawableId = R.drawable.map_pa3;

        mTv_named.setText("B. " + strBoss);
        mIv_pppt.setImageResource(nDrawableId);
    }

    private void tan3() {
        mBtn_prev.setEnabled(true);
        setBtnVisibility(true);

        String strMap = getString(R.string.jj_t);
        mTv_pppt.setText(strMap + " 3");

//        String strHard = getString(R.string.hard_t);
//        mTv_hard.setText(strHard);

        String strMst1 = getString(R.string.mst_beki);  //카드패턴 0, 3
        String strBoss = getString(R.string.mst_losa);
        int nDrawableId = R.drawable.map_tan3_0;    //카드패턴 0, 3

        switch (mCardPatternIndex) {
            case 1:
                strMst1 = getString(R.string.mst_nerbe);  //카드패턴 1
                nDrawableId = R.drawable.map_tan3_1;    //카드패턴 1
                break;
            case 2:
            case 4:
                strMst1 = getString(R.string.mst_jakel);  //카드패턴 2, 4
                nDrawableId = R.drawable.map_tan3_2;    //카드패턴 2, 4
                break;
        }

        mTv_named.setText("1. " + strMst1 + "\nB. " + strBoss);
        mIv_pppt.setImageResource(nDrawableId);
    }
}