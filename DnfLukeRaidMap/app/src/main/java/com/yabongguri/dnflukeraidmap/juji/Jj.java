package com.yabongguri.dnflukeraidmap.juji;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.yabongguri.dnflukeraidmap.Define;
import com.yabongguri.dnflukeraidmap.R;
import com.yabongguri.dnflukeraidmap.juji.Pppt;
import com.yabongguri.dnflukeraidmap.juji.Ttsss;
import com.yabongguri.dnflukeraidmap.juji.Others;

/**
 * Created by BitnaKeum on 2017-11-13.
 */

public class Jj extends Activity implements View.OnClickListener {

    private Button mBtn_pppt;
    private Button mBtn_ttsss;
    private Button mBtn_others;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jj);

        if (Define.IS_SELECT_AD) {     //광고 없는 버전 만들기 위해서 추가해놓음
            AdView adView = (AdView) findViewById(R.id.adView);
            AdRequest adRequest = new AdRequest.Builder().build();
            adView.loadAd(adRequest);
        }

        mBtn_pppt = (Button)findViewById(R.id.btn_pppt);
        mBtn_ttsss = (Button)findViewById(R.id.btn_ttsss);
        mBtn_others = (Button)findViewById(R.id.btn_others);

        mBtn_pppt.setOnClickListener(this);
        mBtn_ttsss.setOnClickListener(this);
        mBtn_others.setOnClickListener(this);
    }

    public void onClick(View v)
    {
        Intent intent = null;

        switch (v.getId())
        {
            case R.id.btn_pppt:
                intent = new Intent(this, Pppt.class);
                break;
            case R.id.btn_ttsss:
                intent = new Intent(this, Ttsss.class);
                break;
            case R.id.btn_others:
                intent = new Intent(this, Others.class);
                break;
        }

        if (intent != null)
            startActivity(intent);
    }
}