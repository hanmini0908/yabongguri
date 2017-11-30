package com.yabongguri.dnflukeraidmap.juji;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.yabongguri.dnflukeraidmap.R;
import com.yabongguri.dnflukeraidmap.RuntimeConfig;
import com.yabongguri.dnflukeraidmap.tobul.Tb;

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
        setContentView(R.layout.activity_jj_ttsss);

        //Activity에 입력이 없어도 화면 꺼지지 않게 하기
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        mTv_ttsss = (TextView)findViewById(R.id.tv_ttsss);
        mTv_named = (TextView)findViewById(R.id.tv_ttsss_named);
        mIv_ttsss = (ImageView)findViewById(R.id.iv_ttsss);
        mBtn_prev = (Button)findViewById(R.id.btn_ttsss_prev);
        mBtn_next = (Button)findViewById(R.id.btn_ttsss_next);
        mBtn_others = (Button)findViewById(R.id.btn_ttsss_others);
        mBtn_tb = (Button)findViewById(R.id.btn_ttsss_tb);

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

        viewMapIndex();
    }

    public void onClickNext(View v) {
        if (mMapIndex < 4)
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

    private void tan1() {
        mBtn_prev.setEnabled(false);
        setBtnVisibility(false);

        String strMap = getString(R.string.jj_t);
        mTv_ttsss.setText(strMap + " 1");

        String strBoss = getString(R.string.mst_metal);
        int nDrawableId = R.drawable.map_tan1;

        mTv_named.setText("B. " + strBoss);
        mIv_ttsss.setImageResource(nDrawableId);
    }

    private void tan2() {
        mBtn_prev.setEnabled(true);
        setBtnVisibility(false);

        String strMap = getString(R.string.jj_t);
        mTv_ttsss.setText(strMap + " 2");

        String strMst1 = getString(R.string.mst_karina);  //카드패턴 0, 4
        String strBoss = getString(R.string.mst_mistral);
        int nDrawableId = R.drawable.map_tan2_0;  //카드패턴 0, 4

        switch (mCardPatternIndex) {
            case 1:
                strMst1 = getString(R.string.mst_red);  //카드패턴 1
                nDrawableId = R.drawable.map_tan2_1;  //카드패턴 1
                break;
            case 2:
                strMst1 = getString(R.string.mst_iron);  //카드패턴 2
                nDrawableId = R.drawable.map_tan2_2;  //카드패턴 2
                break;
            case 3:
                strMst1 = getString(R.string.mst_argos);  //카드패턴 3
                nDrawableId = R.drawable.map_tan2_3;  //카드패턴 3
                break;
        }

        mTv_named.setText("1. " + strMst1 + "\nB. " + strBoss);
        mIv_ttsss.setImageResource(nDrawableId);
    }

    private void so1() {
        mBtn_prev.setEnabled(true);
        setBtnVisibility(false);

        String strMap = getString(R.string.jj_s);
        mTv_ttsss.setText(strMap + " 1");

        String strBoss = getString(R.string.mst_iron); //카드패턴 0
        int nDrawableId = R.drawable.map_so1_0;    //카드패턴 0

        switch (mCardPatternIndex) {
            case 1:
            case 4:
                strBoss = getString(R.string.mst_argos);  //카드패턴 1, 4
                nDrawableId = R.drawable.map_so1_1;    //카드패턴 1, 4
                break;
            case 2:
            case 3:
                strBoss = getString(R.string.mst_ramp);  //카드패턴 2, 3
                nDrawableId = R.drawable.map_so1_2;    //카드패턴 2, 3
                break;
        }

        mTv_named.setText("B. " + strBoss);
        mIv_ttsss.setImageResource(nDrawableId);
    }

    private void so2() {
        mBtn_prev.setEnabled(true);
        setBtnVisibility(false);

        String strMap = getString(R.string.jj_s);
        mTv_ttsss.setText(strMap + " 2");

        String strMst1 = getString(R.string.mst_ramp);  //카드패턴 0
        String strBoss = getString(R.string.mst_jakel); //카드패턴 0
        int nDrawableId = R.drawable.map_so2_0;    //카드패턴 0

        switch (mCardPatternIndex) {
            case 1:
                strMst1 = getString(R.string.mst_karina);  //카드패턴 1
                strBoss = getString(R.string.mst_iron);    //카드패턴 1
                nDrawableId = R.drawable.map_so2_1;    //카드패턴 1
                break;
            case 2:
                strMst1 = getString(R.string.mst_beki);    //카드패턴 2
                strBoss = getString(R.string.mst_karina);  //카드패턴 2
                nDrawableId = R.drawable.map_so2_2;    //카드패턴 2
                break;
            case 3:
                strMst1 = getString(R.string.mst_jakel);  //카드패턴 3
                strBoss = getString(R.string.mst_nerbe);  //카드패턴 3
                nDrawableId = R.drawable.map_so2_3;    //카드패턴 3
                break;
            case 4:
                strMst1 = getString(R.string.mst_beki);  //카드패턴 4
                strBoss = getString(R.string.mst_red);   //카드패턴 4
                nDrawableId = R.drawable.map_so2_4;    //카드패턴 4
                break;
        }

        mTv_named.setText("1. " + strMst1 + "\nB. " + strBoss);
        mIv_ttsss.setImageResource(nDrawableId);
    }

    private void so3() {
        mBtn_prev.setEnabled(true);
        setBtnVisibility(true);

        String strMap = getString(R.string.jj_s);
        mTv_ttsss.setText(strMap + " 3");

        String strBoss = getString(R.string.mst_habub);
        int nDrawableId = R.drawable.map_so3;

        mTv_named.setText("B. " + strBoss);
        mIv_ttsss.setImageResource(nDrawableId);
    }
}