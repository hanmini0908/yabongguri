package com.yabongguri.dnflukeraidmap.single;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;

import com.yabongguri.dnflukeraidmap.R;

/**
 * Created by BitnaKeum on 2017-11-30.
 */

public class SingleSelect extends Activity {

    private Intent mIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_select);
    }

    public void onClickTue(View v) {
        mIntent = new Intent(this, SingleRaidTue.class);
        startActivity(mIntent);
    }

    public void onClickSat(View v) {
        mIntent = new Intent(this, SingleRaidSat.class);
        startActivity(mIntent);
    }
}
