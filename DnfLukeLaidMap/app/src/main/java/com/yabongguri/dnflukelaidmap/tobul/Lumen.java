package com.yabongguri.dnflukelaidmap.tobul;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.yabongguri.dnflukelaidmap.R;

/**
 * Created by BitnaKeum on 2017-11-15.
 */

public class Lumen extends Activity {

    private TextView mTv_lumen;
    private TextView mTv_named;
    private ImageView mIv_lumen;
    private Button mBtn_prev;
    private Button mBtn_next;

    private int mMapIndex;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tb_lumen);

        //Activity에 입력이 없어도 화면 꺼지지 않게 하기
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        mTv_lumen = (TextView)findViewById(R.id.tv_lumen);
        mTv_named = (TextView)findViewById(R.id.tv_lumen_named);
        mIv_lumen = (ImageView)findViewById(R.id.iv_lumen);
        mBtn_prev = (Button)findViewById(R.id.btn_lumen_prev);
        mBtn_next = (Button)findViewById(R.id.btn_lumen_next);

        mMapIndex = 0;

        if (mMapIndex == 0)
            map1();
        else if (mMapIndex == 1)
            map2();
        else if (mMapIndex == 2)
            map3();
        else if (mMapIndex == 3)
            map4();
        else if (mMapIndex == 4)
            map5();
    }

    public void onClickPrev(View v) {
        if (mMapIndex > 0)
            mMapIndex--;

        switch (mMapIndex) {
            case 0:
                map1();
                break;
            case 1:
                map2();
                break;
            case 2:
                map3();
                break;
            case 3:
                map4();
                break;
        }
    }

    public void onClickNext(View v) {
        if (mMapIndex < 4)
            mMapIndex++;

        switch (mMapIndex) {
            case 1:
                map2();
                break;
            case 2:
                map3();
                break;
            case 3:
                map4();
                break;
            case 4:
                map5();
                break;
        }
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

    private void map1() {
        mBtn_prev.setEnabled(false);
        mBtn_next.setEnabled(true);

        String strMap = getString(R.string.tb_lu1);
        mTv_lumen.setText(strMap);

        String strMst1 = getString(R.string.mst_karina);
        String strMst2 = getString(R.string.mst_beki);
        String strBoss = getString(R.string.mst_ramp);
        mTv_named.setText("1. " + strMst1 + "\n2. " + strMst2 + "\nB. " + strBoss);

        int nDrawableId = R.drawable.map_tb_lu1;
        mIv_lumen.setImageResource(nDrawableId);
    }

    private void map2() {
        mBtn_prev.setEnabled(true);
        mBtn_next.setEnabled(true);

        String strMap = getString(R.string.tb_lu2);
        mTv_lumen.setText(strMap);

        String strMst1 = getString(R.string.mst_aslan);
        String strBoss = getString(R.string.mst_horus);
        mTv_named.setText("1. " + strMst1 + "\nB. " + strBoss);

        int nDrawableId = R.drawable.map_tb_lu2;
        mIv_lumen.setImageResource(nDrawableId);
    }

    private void map3() {
        mBtn_prev.setEnabled(true);
        mBtn_next.setEnabled(true);

        String strMap = getString(R.string.tb_lu3);
        mTv_lumen.setText(strMap);

        String strMst1 = getString(R.string.mst_beil);
        String strBoss = getString(R.string.mst_prince);
        mTv_named.setText("1. " + strMst1 + "\nB. " + strBoss);

        int nDrawableId = R.drawable.map_tb_lu3;
        mIv_lumen.setImageResource(nDrawableId);
    }

    private void map4() {
        mBtn_prev.setEnabled(true);
        mBtn_next.setEnabled(true);

        String strMap = getString(R.string.tb_lu4);
        mTv_lumen.setText(strMap);

        String strMst1 = getString(R.string.mst_red);
        String strBoss = getString(R.string.mst_losa);
        mTv_named.setText("1. " + strMst1 + "\nB. " + strBoss);

        int nDrawableId = R.drawable.map_tb_lu4;
        mIv_lumen.setImageResource(nDrawableId);
    }

    private void map5() {
        mBtn_prev.setEnabled(true);
        mBtn_next.setEnabled(false);

        String strMap = getString(R.string.tb_lumen);
        mTv_lumen.setText(strMap);

        String strBoss = getString(R.string.mst_luke);
        mTv_named.setText("B. " + strBoss);

        int nDrawableId = R.drawable.map_tb_caligo;
        mIv_lumen.setImageResource(nDrawableId);
    }
}