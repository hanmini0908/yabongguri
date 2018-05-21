package com.yabongguri.dnflukeraidmap.single;

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
 * Created by BitnaKeum on 2017-11-30.
 */

public class SingleRaidSat extends Activity {
    private TextView mTv_sg;
    private TextView mTv_named;
    private ImageView mIv_sg;
    private Button mBtn_prev;

    private int mMapIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singleraid);

        if (Define.IS_MAP_AD) {     //광고 없는 버전 만들기 위해서 추가해놓음
            AdView adView = (AdView) findViewById(R.id.adView);
            AdRequest adRequest = new AdRequest.Builder().build();
            adView.loadAd(adRequest);
        }

        //Activity에 입력이 없어도 화면 꺼지지 않게 하기
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        mTv_sg = (TextView)findViewById(R.id.tv_sg);
        mTv_named = (TextView)findViewById(R.id.tv_sg_named);
        mIv_sg = (ImageView)findViewById(R.id.iv_sg);
        mBtn_prev = (Button)findViewById(R.id.btn_sg_prev);

        if (savedInstanceState == null) {
            mMapIndex = 0;
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
            sat_tan();
        else if (mMapIndex == 1)
            sat_pa();
        else if (mMapIndex == 2)
            sat_cal2();
        else if (mMapIndex == 3)
            sat_cal3();
        else if (mMapIndex == 4)
            sat_cal4();
        else if (mMapIndex == 5)
            sat_sm();
    }

    public void onClickPrev(View v) {
        if (mMapIndex > 0) {
            mMapIndex--;
            viewMapIndex();
        }
        else if (mMapIndex == 0) {
            finish();
        }
    }

    public void onClickNext(View v) {
        if (mMapIndex < 5) {
            mMapIndex++;
            viewMapIndex();
        }
        else if (mMapIndex == 5) {
            Intent intent = new Intent(this, Success.class);
            intent.putExtra("nRaidType", 2);
            startActivity(intent);
        }
    }

    private void mapSetting(String strMap, String strNamed, int nDrawableId) {
        mTv_sg.setText(strMap);
        mTv_named.setText(strNamed);
        mIv_sg.setImageResource(nDrawableId);
    }

    private void sat_tan() {
        String strMap = getString(R.string.jj_t);
        int nDrawableId = R.drawable.map_sg_sat1;

        String strMst1 = getString(R.string.mst_mistral);
        String strBoss = getString(R.string.mst_losa);
        String strNamed = String.format(Locale.US, "%d. %s\n%s. %s\n", 1, strMst1, "B", strBoss);

        mapSetting(strMap, strNamed, nDrawableId);
    }

    private void sat_pa() {
        String strMap = getString(R.string.jj_p);
        int nDrawableId = R.drawable.map_sg_sat2;

        String strMst1 = getString(R.string.mst_argos);
        String strMst2 = getString(R.string.mst_karina);
        String strBoss = getString(R.string.mst_bupon);

        String strNamed = String.format(Locale.US, "%d. %s\n%d. %s\n%s. %s\n", 1, strMst1, 2, strMst2, "B", strBoss);

        mapSetting(strMap, strNamed, nDrawableId);
    }

    private void sat_cal2() {
        String strMap = getString(R.string.tb_cal2);
        int nDrawableId = R.drawable.map_sg_sat3;

        String strMst1 = getString(R.string.mst_yasin);
        String strBoss = getString(R.string.mst_anubis);
        String strNamed = String.format(Locale.US, "%d. %s\n%s. %s\n", 1, strMst1, "B", strBoss);

        mapSetting(strMap, strNamed, nDrawableId);
    }

    private void sat_cal3() {
        String strMap = getString(R.string.tb_cal3);
        int nDrawableId = R.drawable.map_sg_sat4;

        String strMst1 = getString(R.string.mst_snaider);
        String strBoss = getString(R.string.mst_beara);
        String strNamed = String.format(Locale.US, "%d. %s\n%s. %s\n", 1, strMst1, "B", strBoss);

        mapSetting(strMap, strNamed, nDrawableId);
    }

    private void sat_cal4() {
        String strMap = getString(R.string.tb_cal4);
        int nDrawableId = R.drawable.map_sg_sat5;

        String strMst1 = getString(R.string.mst_mark);
        String strBoss = getString(R.string.mst_nerbe);
        String strNamed = String.format(Locale.US, "%d. %s\n%s. %s\n", 1, strMst1, "B", strBoss);

        mapSetting(strMap, strNamed, nDrawableId);
    }

    private void sat_sm() {
        String strMap = getString(R.string.tb_caligo);
        int nDrawableId = R.drawable.map_tb_caligo;

        String strBoss = getString(R.string.mst_luke);
        String strNamed = "B. " + strBoss;

        mapSetting(strMap, strNamed, nDrawableId);
    }
}
