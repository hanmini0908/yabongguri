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

public class SingleRaidSecond0 extends Activity {
    private TextView mTv_sg;
    private TextView mTv_named;
    private ImageView mIv_sg;
    private Button mBtn_prev;

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

        thisMap();
    }

    private void openNewActivity(boolean isPrev) {
        finish();
        Intent intent = null;

        if (!isPrev)
            intent = new Intent(this, SingleRaidSecond1.class);

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

    private void mapSetting(String strMap, String strNamed, int nDrawableId) {
        mTv_sg.setText(strMap);
        mTv_named.setText(strNamed);
        mIv_sg.setImageResource(nDrawableId);
    }

    private void thisMap() {
        String strMap = getString(R.string.jj_t);
        int nDrawableId = R.drawable.map_sg_sat1;

        String strMst1 = getString(R.string.mst_mistral);
        String strBoss = getString(R.string.mst_losa);
        String strNamed = String.format(Locale.US, "%d. %s\n%s. %s\n", 1, strMst1, "B", strBoss);

        mapSetting(strMap, strNamed, nDrawableId);
    }
}
