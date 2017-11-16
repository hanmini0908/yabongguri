package com.yabongguri.dnflukelaidmap;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.yabongguri.dnflukelaidmap.tobul.Lumen;
import com.yabongguri.dnflukelaidmap.tobul.Caligo;

/**
 * Created by BitnaKeum on 2017-11-13.
 */

public class Tb extends Activity implements View.OnClickListener {

    private Button mBtn_lumen;
    private Button mBtn_caligo;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tb);

        mBtn_lumen = (Button)findViewById(R.id.btn_lumen);
        mBtn_caligo = (Button)findViewById(R.id.btn_caligo);

        mBtn_lumen.setOnClickListener(this);
        mBtn_caligo.setOnClickListener(this);
    }

    public void onClick(View v)
    {
        Intent intent = null;

        switch (v.getId())
        {
            case R.id.btn_lumen:
                intent = new Intent(this, Lumen.class);
                break;
            case R.id.btn_caligo:
                intent = new Intent(this, Caligo.class);
                break;
        }

        if (intent != null)
            startActivity(intent);
    }
}