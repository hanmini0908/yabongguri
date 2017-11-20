package com.yabongguri.dnflukeraidmap.tobul;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.yabongguri.dnflukeraidmap.R;

/**
 * Created by BitnaKeum on 2017-11-15.
 */

public class Caligo extends Activity {

    private TextView mTv_caligo;
    private TextView mTv_named;
    private ImageView mIv_caligo;
    private Button mBtn_prev;
    private Button mBtn_next;

    private int mMapIndex;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tb_caligo);

        //Activity에 입력이 없어도 화면 꺼지지 않게 하기
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        mTv_caligo = (TextView)findViewById(R.id.tv_caligo);
        mTv_named = (TextView)findViewById(R.id.tv_caligo_named);
        mIv_caligo = (ImageView)findViewById(R.id.iv_caligo);
        mBtn_prev = (Button)findViewById(R.id.btn_caligo_prev);
        mBtn_next = (Button)findViewById(R.id.btn_caligo_next);

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

        viewMapIndex();
    }

    public void onClickNext(View v) {
        if (mMapIndex < 4) {
            mMapIndex++;
            viewMapIndex();
        }
        else if (mMapIndex == 4) {
            Intent intent = new Intent(this, Success.class);
            startActivity(intent);
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

        String strMap = getString(R.string.tb_cal1);
        mTv_caligo.setText(strMap);

        String strMst1 = getString(R.string.mst_jakel);
        String strMst2 = getString(R.string.mst_nerbe);
        String strBoss = getString(R.string.mst_argos);
        mTv_named.setText("1. " + strMst1 + "\n2. " + strMst2 + "\nB. " + strBoss);

        int nDrawableId = R.drawable.map_tb_cal1;
        mIv_caligo.setImageResource(nDrawableId);
    }

    private void map2() {
        mBtn_prev.setEnabled(true);

        String strMap = getString(R.string.tb_cal2);
        mTv_caligo.setText(strMap);

        String strMst1 = getString(R.string.mst_snaider);
        String strBoss = getString(R.string.mst_yasin);
        mTv_named.setText("1. " + strMst1 + "\nB. " + strBoss);

        int nDrawableId = R.drawable.map_tb_cal2;
        mIv_caligo.setImageResource(nDrawableId);
    }

    private void map3() {
        mBtn_prev.setEnabled(true);

        String strMap = getString(R.string.tb_cal3);
        mTv_caligo.setText(strMap);

        String strMst1 = getString(R.string.mst_anubis);
        String strBoss = getString(R.string.mst_beara);
        mTv_named.setText("1. " + strMst1 + "\nB. " + strBoss);

        int nDrawableId = R.drawable.map_tb_cal3;
        mIv_caligo.setImageResource(nDrawableId);
    }

    private void map4() {
        mBtn_prev.setEnabled(true);

        String strMap = getString(R.string.tb_cal4);
        mTv_caligo.setText(strMap);

        String strMst1 = getString(R.string.mst_mark);
        String strBoss = getString(R.string.mst_bupon);
        mTv_named.setText("1. " + strMst1 + "\nB. " + strBoss);

        int nDrawableId = R.drawable.map_tb_cal4;
        mIv_caligo.setImageResource(nDrawableId);
    }

    private void map5() {
        mBtn_prev.setEnabled(true);

        String strMap = getString(R.string.tb_caligo);
        mTv_caligo.setText(strMap);

        String strBoss = getString(R.string.mst_luke);
        mTv_named.setText("B. " + strBoss);

        int nDrawableId = R.drawable.map_tb_caligo;
        mIv_caligo.setImageResource(nDrawableId);
    }
}