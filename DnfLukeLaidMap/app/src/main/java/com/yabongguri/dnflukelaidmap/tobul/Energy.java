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

public class Energy extends Activity {
    private TextView mTv_energy;
    private TextView mTv_named;
    private ImageView mIv_energy;
    private Button mBtn_back;

    private int mMapIndex;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tb_energy);

        //Activity에 입력이 없어도 화면 꺼지지 않게 하기
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        mTv_energy = (TextView)findViewById(R.id.tv_energy);
        mTv_named = (TextView)findViewById(R.id.tv_energy_named);
        mIv_energy = (ImageView)findViewById(R.id.iv_energy);
        mBtn_back = (Button)findViewById(R.id.btn_back);

        mMapIndex = 0;
        Intent intent = getIntent();
        mMapIndex = intent.getIntExtra("mapIndex", 0);

        if (mMapIndex == 0)
            mapC();
        else if (mMapIndex == 1)
            mapJ();
    }

    public void onClickBack(View v) {
        finish();
    }

    private void mapC() {
        String strMap = getString(R.string.tb_en_c);
        mTv_energy.setText(strMap);

        String strMst1 = getString(R.string.mst_iron);
        String strMst2 = getString(R.string.mst_metal);
        mTv_named.setText("좌. " + strMst1 + "\n우. " + strMst2);

        int nDrawableId = R.drawable.map_tb_en_c;
        mIv_energy.setImageResource(nDrawableId);
    }

    private void mapJ() {
        String strMap = getString(R.string.tb_en_j);
        mTv_energy.setText(strMap);

        String strMst1 = getString(R.string.mst_yasin);
        String strMst2 = getString(R.string.mst_beara);
        mTv_named.setText("좌. " + strMst1 + "\n우. " + strMst2);

        int nDrawableId = R.drawable.map_tb_en_j;
        mIv_energy.setImageResource(nDrawableId);
    }
}
