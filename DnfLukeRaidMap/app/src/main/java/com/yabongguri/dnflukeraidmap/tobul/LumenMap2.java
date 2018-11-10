package com.yabongguri.dnflukeraidmap.tobul;

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

/**
 * Created by BitnaKeum on 2017-11-15.
 */

public class LumenMap2 extends Activity {

    private TextView mTv_lumen;
    private TextView mTv_named;
    private ImageView mIv_lumen;
    private Button mBtn_prev;
    private Button mBtn_next;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tb_lumen);

        if (Define.IS_MAP_AD) {     //광고 없는 버전 만들기 위해서 추가해놓음
            AdView adView = (AdView) findViewById(R.id.adView);
            AdRequest adRequest = new AdRequest.Builder().build();
            adView.loadAd(adRequest);
        }

        //Activity에 입력이 없어도 화면 꺼지지 않게 하기
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        mTv_lumen = (TextView)findViewById(R.id.tv_lumen);
        mTv_named = (TextView)findViewById(R.id.tv_lumen_named);
        mIv_lumen = (ImageView)findViewById(R.id.iv_lumen);
        mBtn_prev = (Button)findViewById(R.id.btn_lumen_prev);
        mBtn_next = (Button)findViewById(R.id.btn_lumen_next);

        thisMap();
    }

    private void openNewActivity(boolean isPrev) {
        finish();
        Intent intent = null;

        if (isPrev)
            intent = new Intent(this, LumenMap1.class);
        else
            intent = new Intent(this, LumenMap3.class);

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

    public void onClickEnergy(View v) {
        Intent intent = new Intent(this, Energy.class);

        switch (v.getId()) {
            case R.id.btn_energy_c:
                intent.putExtra("mapIndex", 0); //제어실
                break;
            case R.id.btn_energy_j:
                intent.putExtra("mapIndex", 1); //저장소
                break;
        }

        startActivity(intent);
    }

    private void thisMap() {
        mBtn_prev.setEnabled(true);

        String strMap = getString(R.string.tb_lu2);
        mTv_lumen.setText(strMap);

        String strMst1 = getString(R.string.mst_aslan);
        String strBoss = getString(R.string.mst_horus);
        mTv_named.setText("1. " + strMst1 + "\nB. " + strBoss);

        int nDrawableId = R.drawable.map_tb_lu2;
        mIv_lumen.setImageResource(nDrawableId);
    }
}
