package com.yabongguri.dnflukeraidmap.illuke;

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
import com.yabongguri.dnflukeraidmap.tobul.Success;

import java.util.Locale;

/**
 * Created by BitnaKeum on 2017-11-21.
 */

public class IllukeMap6 extends Activity {

    private TextView mTv_il;
    private TextView mTv_named;
    private ImageView mIv_il;
    private Button mBtn_prev;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_il_map);

        if (Define.IS_MAP_AD) {     //광고 없는 버전 만들기 위해서 추가해놓음
            AdView adView = (AdView) findViewById(R.id.adView);
            AdRequest adRequest = new AdRequest.Builder().build();
            adView.loadAd(adRequest);
        }

        //Activity에 입력이 없어도 화면 꺼지지 않게 하기
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        mTv_il = (TextView)findViewById(R.id.tv_il);
        mTv_named = (TextView)findViewById(R.id.tv_il_named);
        mIv_il = (ImageView)findViewById(R.id.iv_il);
        mBtn_prev = (Button)findViewById(R.id.btn_il_prev);

        thisMap();
    }

    private void openNewActivity(boolean isPrev) {
        finish();
        Intent intent = null;

        if (isPrev) {
            intent = new Intent(this, IllukeMap5.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        }
        else {
            intent = new Intent(this, Success.class);
            intent.putExtra("nRaidType", 0);
        }

        if (intent != null)
            startActivity(intent);
    }

    public void onClickPrev(View v) {
        openNewActivity(true);
    }

    public void onClickNext(View v) {
        openNewActivity(false);
    }

    private void thisMap() {
        String strMap = getString(R.string.il_sm);
        mTv_il.setText(strMap);

        String strBoss = getString(R.string.mst_luke);
        mTv_named.setText("B. " + strBoss);

        int nDrawableId = R.drawable.map_il_luke;
        mIv_il.setImageResource(nDrawableId);
    }
}