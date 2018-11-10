package com.yabongguri.dnflukeraidmap.juji;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.yabongguri.dnflukeraidmap.Define;
import com.yabongguri.dnflukeraidmap.R;
import com.yabongguri.dnflukeraidmap.RuntimeConfig;
import com.yabongguri.dnflukeraidmap.tobul.Tb;

/**
 * Created by BitnaKeum on 2017-11-13.
 */

public class TtsssSo3 extends Activity {

    private TextView mTv_ttsss;
    private TextView mTv_named;
    private ImageView mIv_ttsss;
    private Button mBtn_prev;
    private Button mBtn_next;
    private Button mBtn_others;
    private Button mBtn_tb;

    private int mCardPatternIndex;
    private boolean mIsOthers;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jj_ttsss);

        if (Define.IS_MAP_AD) {     //광고 없는 버전 만들기 위해서 추가해놓음
            AdView adView = (AdView) findViewById(R.id.adView);
            AdRequest adRequest = new AdRequest.Builder().build();
            adView.loadAd(adRequest);
        }

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
            mIsOthers = false;

            Intent intent = getIntent();
            if (intent != null)
                mIsOthers = intent.getBooleanExtra("isOthers", false);
        }
        else {
            if (savedInstanceState.getBoolean("isOthers"))
                mIsOthers = true;
        }

        thisMap();
    }

    public void onSaveInstanceState(Bundle outState) {
        outState.putBoolean("isOthers", mIsOthers);
        super.onSaveInstanceState(outState);
    }

    private void openNewActivity(boolean isPrev) {
        finish();
        Intent intent = null;

        if (isPrev)
            intent = new Intent(this, TtsssSo2.class);

        if (intent != null) {
            intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent);
        }
    }

    public void onClickPrev(View v) {
        openNewActivity(true);
    }

    public void onClickNext(View v) {
        openNewActivity(false);
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

    private void setBtnVisibility() {
        if (!mIsOthers) {
            mBtn_prev.setVisibility(View.VISIBLE);
            mBtn_next.setVisibility(View.GONE);
            mBtn_others.setVisibility(View.VISIBLE);
            mBtn_tb.setVisibility(View.VISIBLE);
        }
        else
        {
            mBtn_prev.setVisibility(View.INVISIBLE);
            mBtn_next.setVisibility(View.GONE);
            mBtn_others.setVisibility(View.VISIBLE);
            mBtn_tb.setVisibility(View.VISIBLE);
        }
    }

    private void thisMap() {
        mBtn_prev.setEnabled(true);
        setBtnVisibility();

        String strMap = getString(R.string.jj_s);
        mTv_ttsss.setText(strMap + " 3");

        String strBoss = getString(R.string.mst_habub);
        int nDrawableId = R.drawable.map_so3;

        mTv_named.setText("B. " + strBoss);
        mIv_ttsss.setImageResource(nDrawableId);
    }
}