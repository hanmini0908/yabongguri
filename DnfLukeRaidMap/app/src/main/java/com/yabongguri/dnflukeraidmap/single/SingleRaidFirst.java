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

public class SingleRaidFirst extends Activity {

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
            tue_tan();
        else if (mMapIndex == 1)
            tue_so();
        else if (mMapIndex == 2)
            tue_lu2();
        else if (mMapIndex == 3)
            tue_lu3();
        else if (mMapIndex == 4)
            tue_lu4();
        else if (mMapIndex == 5)
            tue_sm();
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

    private void tue_tan() {
        String strMap = getString(R.string.jj_t);
        int nDrawableId = R.drawable.map_sg_tue1;

        String strMst1 = getString(R.string.mst_beki);
        String strBoss = getString(R.string.mst_metal);
        String strNamed = String.format(Locale.US, "%d. %s\n%s. %s\n", 1, strMst1, "B", strBoss);

        mapSetting(strMap, strNamed, nDrawableId);
    }

    private void tue_so() {
        String strMap = getString(R.string.jj_s);
        int nDrawableId = R.drawable.map_sg_tue2;

        String strMst1 = getString(R.string.mst_iron);
        String strMst2 = getString(R.string.mst_ramp);
        String strBoss = getString(R.string.mst_habub);

        String strNamed = String.format(Locale.US, "%d. %s\n%d. %s\n%s. %s\n", 1, strMst1, 2, strMst2, "B", strBoss);

        mapSetting(strMap, strNamed, nDrawableId);
    }

    private void tue_lu2() {
        String strMap = getString(R.string.tb_lu2);
        int nDrawableId = R.drawable.map_sg_tue3;

        String strMst1 = getString(R.string.mst_jakel);
        String strBoss = getString(R.string.mst_horus);
        String strNamed = String.format(Locale.US, "%d. %s\n%s. %s\n", 1, strMst1, "B", strBoss);

        mapSetting(strMap, strNamed, nDrawableId);
    }

    private void tue_lu3() {
        String strMap = getString(R.string.tb_lu3);
        int nDrawableId = R.drawable.map_sg_tue4;

        String strMst1 = getString(R.string.mst_beil);
        String strBoss = getString(R.string.mst_prince);
        String strNamed = String.format(Locale.US, "%d. %s\n%s. %s\n", 1, strMst1, "B", strBoss);

        mapSetting(strMap, strNamed, nDrawableId);
    }

    private void tue_lu4() {
        String strMap = getString(R.string.tb_lu4);
        int nDrawableId = R.drawable.map_sg_tue5;

        String strMst1 = getString(R.string.mst_red);
        String strBoss = getString(R.string.mst_aslan);
        String strNamed = String.format(Locale.US, "%d. %s\n%s. %s\n", 1, strMst1, "B", strBoss);

        mapSetting(strMap, strNamed, nDrawableId);
    }

    private void tue_sm() {
        String strMap = getString(R.string.tb_lumen);
        int nDrawableId = R.drawable.map_tb_caligo;

        String strBoss = getString(R.string.mst_luke);
        String strNamed = "B. " + strBoss;

        mapSetting(strMap, strNamed, nDrawableId);
    }
}
