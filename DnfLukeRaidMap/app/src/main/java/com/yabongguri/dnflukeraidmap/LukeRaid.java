package com.yabongguri.dnflukeraidmap;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.yabongguri.dnflukeraidmap.juji.CardPattern;
import com.yabongguri.dnflukeraidmap.tobul.Tb;

/**
 * Created by BitnaKeum on 2017-11-22.
 */

public class LukeRaid extends Activity implements View.OnClickListener {

    private Button mBtn_jj;
    private Button mBtn_tb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lukeraid);

        if (Define.IS_SELECT_AD) {     //광고 없는 버전 만들기 위해서 추가해놓음
            AdView adView = (AdView) findViewById(R.id.adView);
            AdRequest adRequest = new AdRequest.Builder().build();
            adView.loadAd(adRequest);
        }

        mBtn_jj = (Button)findViewById(R.id.btn_jj);
        mBtn_tb = (Button)findViewById(R.id.btn_tb);

        mBtn_jj.setOnClickListener(this);
        mBtn_tb.setOnClickListener(this);
    }

    public void onClick(View v)
    {
        Intent intent = null;

        switch (v.getId())
        {
            case R.id.btn_jj:
                intent = new Intent(this, CardPattern.class);
                break;
            case R.id.btn_tb:
                intent = new Intent(this, Tb.class);
                break;
        }

        if (intent != null)
            startActivity(intent);
    }
}
