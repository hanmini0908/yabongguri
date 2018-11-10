package com.yabongguri.noodletimer;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

public class MainActivity extends AppCompatActivity {
    private ImageButton mIbLamyun;
    private ImageButton mIbKal;
    private ImageButton mIbJjolmyun;
    private ImageButton mIbWoodong;
    private ImageButton mIbPasta;
    private ImageButton mIbSomyun;
    private ImageButton mIbDangmyun;
    private ImageButton mIbMine;

    private TextView mTvLamyun;
    private TextView mTvKal;
    private TextView mTvJjolmyun;
    private TextView mTvWoodong;
    private TextView mTvPasta;
    private TextView mTvSomyun;
    private TextView mTvDangmyun;
    private TextView mTvMine;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MobileAds.initialize(this, getString(R.string.admob_app_id));

        if (Define.IS_MAIN_AD) {
            AdView adView = (AdView) findViewById(R.id.adView);
            AdRequest adRequest = new AdRequest.Builder().build();
            adView.loadAd(adRequest);
        }

        mIbLamyun = (ImageButton)findViewById(R.id.ib_lamyun);
        mIbKal = (ImageButton)findViewById(R.id.ib_kal);
        mIbJjolmyun = (ImageButton)findViewById(R.id.ib_jjolmyun);
        mIbWoodong = (ImageButton)findViewById(R.id.ib_woodong);
        mIbPasta = (ImageButton)findViewById(R.id.ib_pasta);
        mIbSomyun = (ImageButton)findViewById(R.id.ib_somyun);
        mIbDangmyun = (ImageButton)findViewById(R.id.ib_dangmyun);
        mIbMine = (ImageButton)findViewById(R.id.ib_mine);

        mIbLamyun.setOnClickListener(setClickListener());
        mIbKal.setOnClickListener(setClickListener());
        mIbJjolmyun.setOnClickListener(setClickListener());
        mIbWoodong.setOnClickListener(setClickListener());
        mIbPasta.setOnClickListener(setClickListener());
        mIbSomyun.setOnClickListener(setClickListener());
        mIbDangmyun.setOnClickListener(setClickListener());
        mIbMine.setOnClickListener(setClickListener());

        mTvLamyun = (TextView)findViewById(R.id.tv_lamyun);
        mTvKal = (TextView)findViewById(R.id.tv_kal);
        mTvJjolmyun = (TextView)findViewById(R.id.tv_jjolmyun);
        mTvWoodong = (TextView)findViewById(R.id.tv_woodong);
        mTvPasta = (TextView)findViewById(R.id.tv_pasta);
        mTvSomyun = (TextView)findViewById(R.id.tv_somyun);
        mTvDangmyun = (TextView)findViewById(R.id.tv_dangmyun);
        mTvMine = (TextView)findViewById(R.id.tv_mine);

        getSettingValues();
    }

    @Override
    protected void onResume() {
        getSettingValues();
        super.onResume();
    }

    private void getSettingValues() {
        mTvLamyun.setText(convertTimeValues(RuntimeConfig.getLamyunPreference(this)));
        mTvKal.setText(convertTimeValues(RuntimeConfig.getKalPreference(this)));
        mTvJjolmyun.setText(convertTimeValues(RuntimeConfig.getJjolmyunPreference(this)));
        mTvWoodong.setText(convertTimeValues(RuntimeConfig.getWoodongPreference(this)));
        mTvPasta.setText(convertTimeValues(RuntimeConfig.getPastaPreference(this)));
        mTvSomyun.setText(convertTimeValues(RuntimeConfig.getSomyunPreference(this)));
        mTvDangmyun.setText(convertTimeValues(RuntimeConfig.getDangmyunPreference(this)));
        mTvMine.setText(convertTimeValues(RuntimeConfig.getMinePreference(this)));
    }

    private String convertTimeValues(int time) {
        Log.e("hanmin", time / 60 + " 분 " + time % 60 + " 초");
        return time / 60 + " 분 " + time % 60 + " 초";
    }

    private View.OnClickListener setClickListener () {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String selectItem = "";
                String selectItem_key = "";
                switch (v.getId()) {
                    case R.id.ib_lamyun:
                        selectItem = RuntimeConfig.PREFERENCE_LAMYUN;
                        selectItem_key = RuntimeConfig.PREFERENCE_LAMYUN_SET_TIME;
                        //RuntimeConfig.setLamyunPreference(getApplicationContext(), RuntimeConfig.PREFERENCE_LAMYUN_DEFAULT);
                        break;
                    case R.id.ib_kal:
                        selectItem = RuntimeConfig.PREFERENCE_KAL;
                        selectItem_key = RuntimeConfig.PREFERENCE_KAL_SET_TIME;
                        //RuntimeConfig.setKalPreference(getApplicationContext(), RuntimeConfig.PREFERENCE_KAL_DEFAULT);
                        break;
                    case R.id.ib_jjolmyun:
                        selectItem = RuntimeConfig.PREFERENCE_JJOLMYUN;
                        selectItem_key = RuntimeConfig.PREFERENCE_JJOLMYUN_SET_TIME;
                        //RuntimeConfig.setJjolmyunPreference(getApplicationContext(), RuntimeConfig.PREFERENCE_JJOLMYUN_DEFAULT);
                        break;
                    case R.id.ib_woodong:
                        selectItem = RuntimeConfig.PREFERENCE_WOODONG;
                        selectItem_key = RuntimeConfig.PREFERENCE_WOODONG_SET_TIME;
                        //RuntimeConfig.setWoodongPreference(getApplicationContext(), RuntimeConfig.PREFERENCE_WOODONG_DEFAULT);
                        break;
                    case R.id.ib_pasta:
                        selectItem = RuntimeConfig.PREFERENCE_PASTA;
                        selectItem_key = RuntimeConfig.PREFERENCE_PASTA_SET_TIME;
                        //RuntimeConfig.setPastaPreference(getApplicationContext(), RuntimeConfig.PREFERENCE_PASTA_DEFAULT);
                        break;
                    case R.id.ib_somyun:
                        selectItem = RuntimeConfig.PREFERENCE_SOMYUN;
                        selectItem_key = RuntimeConfig.PREFERENCE_SOMYUN_SET_TIME;
                        //RuntimeConfig.setSomyunPreference(getApplicationContext(), RuntimeConfig.PREFERENCE_SOMYUN_DEFAULT);
                        break;
                    case R.id.ib_dangmyun:
                        selectItem = RuntimeConfig.PREFERENCE_DANGMYUN;
                        selectItem_key = RuntimeConfig.PREFERENCE_DANGMYUN_SET_TIME;
                        //RuntimeConfig.setDangmyunPreference(getApplicationContext(), RuntimeConfig.PREFERENCE_DANGMYUN_DEFAULT);
                        break;
                    case R.id.ib_mine:
                        selectItem = RuntimeConfig.PREFERENCE_MINE;
                        selectItem_key = RuntimeConfig.PREFERENCE_MINE_SET_TIME;
                        //RuntimeConfig.setMinePreference(getApplicationContext(), RuntimeConfig.PREFERENCE_MINE_DEFAULT);
                        break;
                    default:
                        break;
                }
                Intent intent = new Intent(getApplicationContext(), TimeSetActivity.class);
                intent.putExtra("select_item", selectItem);
                intent.putExtra("select_item_key", selectItem_key);
                startActivity(intent);
            }
        };
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder alertdialog = new AlertDialog.Builder(this);
        alertdialog.setMessage(getString(R.string.exit_message));

        alertdialog.setPositiveButton(getString(R.string.confirm), new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finishApp();
            }
        });

        alertdialog.setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        AlertDialog alert = alertdialog.create();
        alert.setTitle(getString(R.string.exit));
        alert.show();
    }

    private void finishApp() {
        ActivityCompat.finishAffinity(this);
    }
}
