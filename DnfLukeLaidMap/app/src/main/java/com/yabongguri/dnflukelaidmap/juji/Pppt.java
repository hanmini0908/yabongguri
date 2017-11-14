package com.yabongguri.dnflukelaidmap.juji;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.content.Intent;

import com.yabongguri.dnflukelaidmap.R;
import com.yabongguri.dnflukelaidmap.RuntimeConfig;
import com.yabongguri.dnflukelaidmap.Tb;

/**
 * Created by BitnaKeum on 2017-11-13.
 */

public class Pppt extends Activity {

    private TextView mTv_pppt;
    private TextView mTv_named;
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
        setContentView(R.layout.activity_pppt);

        mTv_pppt = (TextView)findViewById(R.id.tv_pppt);
        mTv_named = (TextView)findViewById(R.id.tv_pppt_named);
        mIv_pppt = (ImageView)findViewById(R.id.iv_pppt);
        mBtn_prev = (Button)findViewById(R.id.btn_pppt_prev);
        mBtn_next = (Button)findViewById(R.id.btn_pppt_next);
        mBtn_others = (Button)findViewById(R.id.btn_pppt_others);
        mBtn_tb = (Button)findViewById(R.id.btn_pppt_tb);

        mCardPatternIndex = RuntimeConfig.getCardPreference(this);

        mMapIndex = 0;
        mIsOthers = false;
        Intent intent = getIntent();
        mMapIndex = intent.getIntExtra("mapIndex", 0);
        mIsOthers = intent.getBooleanExtra("isOthers", false);


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

        switch (mMapIndex) {
            case 0:
                pa1();
                break;
            case 1:
                pa2();
                break;
            case 2:
                pa3();
                break;
        }
    }

    public void onClickNext(View v) {
        if (mMapIndex < 3)
            mMapIndex++;

        switch (mMapIndex) {
            case 1:
                pa2();
                break;
            case 2:
                pa3();
                break;
            case 3:
                tan3();
                break;
        }
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

    private void setBtnVisibility(boolean isOthers, boolean isLastMap) {
        if (!isOthers) {
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
        setBtnVisibility(mIsOthers, false);

        mTv_pppt.setText("파급의 성소 1");

        String strMst1 = getString(R.string.mst_red);    //카드패턴 0, 2
        String strBoss = getString(R.string.mst_nerbe);  //카드패턴 0, 2

        switch (mCardPatternIndex) {
            case 1:
                strMst1 = getString(R.string.mst_beki);  //카드패턴 1
                strBoss = getString(R.string.mst_ramp);  //카드패턴 1, 4
                break;
            case 3:
                strMst1 = getString(R.string.mst_karina);  //카드패턴 3
                strBoss = getString(R.string.mst_red);     //카드패턴 3
                break;
            case 4:
                strMst1 = getString(R.string.mst_iron);  //카드패턴 4
                strBoss = getString(R.string.mst_ramp);  //카드패턴 1, 4
                break;
        }

        mTv_named.setText("1. " + strMst1 + "\nB. " + strBoss);
    }

    private void pa2() {
        mBtn_prev.setEnabled(true);
        setBtnVisibility(mIsOthers, false);

        mTv_pppt.setText("파급의 성소 2");

        String strMst1 = getString(R.string.mst_argos);  //카드패턴 0, 2
        String strBoss = getString(R.string.mst_mark);

        switch (mCardPatternIndex) {
            case 1:
                strMst1 = getString(R.string.mst_jakel);  //카드패턴 1
                break;
            case 3:
                strMst1 = getString(R.string.mst_iron);  //카드패턴 3
                break;
            case 4:
                strMst1 = getString(R.string.mst_nerbe);  //카드패턴 4
                break;
        }

        mTv_named.setText("1. " + strMst1 + "\nB. " + strBoss);
    }

    private void pa3() {
        mBtn_prev.setEnabled(true);
        setBtnVisibility(mIsOthers, false);

        mTv_pppt.setText("파급의 성소 3");

        String strBoss = getString(R.string.mst_bupon);

        mTv_named.setText("B. " + strBoss);
    }

    private void tan3() {
        mBtn_prev.setEnabled(true);
        setBtnVisibility(mIsOthers, true);

        mTv_pppt.setText("탄생의 성소 3");

        String strMst1 = getString(R.string.mst_beki);  //카드패턴 0, 3
        String strBoss = getString(R.string.mst_losa);

        switch (mCardPatternIndex) {
            case 1:
                strMst1 = getString(R.string.mst_nerbe);  //카드패턴 1
                break;
            case 2:
            case 4:
                strMst1 = getString(R.string.mst_jakel);  //카드패턴 2, 4
                break;
        }

        mTv_named.setText("1. " + strMst1 + "\nB. " + strBoss);
    }
}
