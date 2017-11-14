package com.yabongguri.dnflukelaidmap.juji;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.yabongguri.dnflukelaidmap.R;
import com.yabongguri.dnflukelaidmap.RuntimeConfig;
import com.yabongguri.dnflukelaidmap.Tb;

/**
 * Created by BitnaKeum on 2017-11-13.
 */

public class Ttsss extends Activity {

    private TextView mTv_ttsss;
    private TextView mTv_named;
    private ImageView mIv_ttsss;
    private Button mBtn_prev;
    private Button mBtn_next;
    private Button mBtn_others;
    private Button mBtn_tb;

    private int mMapIndex;
    private int mCardPatternIndex;
    private boolean mIsOthers;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ttsss);

        mTv_ttsss = (TextView)findViewById(R.id.tv_ttsss);
        mTv_named = (TextView)findViewById(R.id.tv_ttsss_named);
        mIv_ttsss = (ImageView)findViewById(R.id.iv_ttsss);
        mBtn_prev = (Button)findViewById(R.id.btn_ttsss_prev);
        mBtn_next = (Button)findViewById(R.id.btn_ttsss_next);
        mBtn_others = (Button)findViewById(R.id.btn_ttsss_others);
        mBtn_tb = (Button)findViewById(R.id.btn_ttsss_tb);

        mCardPatternIndex = RuntimeConfig.getCardPreference(this);

        mMapIndex = 0;
        mIsOthers = false;
        Intent intent = getIntent();
        mMapIndex = intent.getIntExtra("mapIndex", 0);
        mIsOthers = intent.getBooleanExtra("isOthers", false);

        if (mMapIndex == 0)
            tan1();
        else if (mMapIndex == 1)
            tan2();
        else if (mMapIndex == 2)
            so1();
        else if (mMapIndex == 3)
            so2();
        else if (mMapIndex == 4)
            so3();
    }

    public void onClickPrev(View v) {
        if (mMapIndex > 0)
            mMapIndex--;

        switch (mMapIndex) {
            case 0:
                tan1();
                break;
            case 1:
                tan2();
                break;
            case 2:
                so1();
                break;
            case 3:
                so2();
                break;
        }
    }

    public void onClickNext(View v) {
        if (mMapIndex < 4)
            mMapIndex++;

        switch (mMapIndex) {
            case 1:
                tan2();
                break;
            case 2:
                so1();
                break;
            case 3:
                so2();
                break;
            case 4:
                so3();
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

    private void tan1() {
        mBtn_prev.setEnabled(false);
        setBtnVisibility(mIsOthers, false);

        mTv_ttsss.setText("탄생의 성소 1");

        String strBoss = getString(R.string.mst_metal);

        mTv_named.setText("B. " + strBoss);
    }

    private void tan2() {
        mBtn_prev.setEnabled(true);
        setBtnVisibility(mIsOthers, false);

        mTv_ttsss.setText("탄생의 성소 2");

        String strMst1 = getString(R.string.mst_karina);  //카드패턴 0, 4
        String strBoss = getString(R.string.mst_the7);

        switch (mCardPatternIndex) {
            case 1:
                strMst1 = getString(R.string.mst_red);  //카드패턴 1
                break;
            case 2:
                strMst1 = getString(R.string.mst_iron);  //카드패턴 2
                break;
            case 3:
                strMst1 = getString(R.string.mst_argos);  //카드패턴 3
                break;
        }

        mTv_named.setText("1. " + strMst1 + "\nB. " + strBoss);
    }

    private void so1() {
        mBtn_prev.setEnabled(true);
        setBtnVisibility(mIsOthers, false);

        mTv_ttsss.setText("소멸의 성소 1");

        String strBoss = getString(R.string.mst_iron); //카드패턴 0

        switch (mCardPatternIndex) {
            case 1:
            case 4:
                strBoss = getString(R.string.mst_argos);  //카드패턴 1, 4
                break;
            case 2:
            case 3:
                strBoss = getString(R.string.mst_ramp);  //카드패턴 2, 3
                break;
        }

        mTv_named.setText("B. " + strBoss);
    }

    private void so2() {
        mBtn_prev.setEnabled(true);
        setBtnVisibility(mIsOthers, false);

        mTv_ttsss.setText("소멸의 성소 2");

        String strMst1 = getString(R.string.mst_ramp);  //카드패턴 0
        String strBoss = getString(R.string.mst_jakel); //카드패턴 0

        switch (mCardPatternIndex) {
            case 1:
                strMst1 = getString(R.string.mst_karina);  //카드패턴 1
                strBoss = getString(R.string.mst_iron);    //카드패턴 1
                break;
            case 2:
                strMst1 = getString(R.string.mst_beki);    //카드패턴 2
                strBoss = getString(R.string.mst_karina);  //카드패턴 2
                break;
            case 3:
                strMst1 = getString(R.string.mst_jakel);  //카드패턴 3
                strBoss = getString(R.string.mst_nerbe);  //카드패턴 3
                break;
            case 4:
                strMst1 = getString(R.string.mst_beki);  //카드패턴 4
                strBoss = getString(R.string.mst_red);   //카드패턴 4
                break;
        }

        mTv_named.setText("1. " + strMst1 + "\nB. " + strBoss);
    }

    private void so3() {
        mBtn_prev.setEnabled(true);
        setBtnVisibility(mIsOthers, true);

        mTv_ttsss.setText("소멸의 성소 3");

        String strBoss = getString(R.string.mst_habub);

        mTv_named.setText("B. " + strBoss);
    }
}
