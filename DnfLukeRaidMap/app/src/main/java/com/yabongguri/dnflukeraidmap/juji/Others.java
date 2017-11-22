package com.yabongguri.dnflukeraidmap.juji;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.yabongguri.dnflukeraidmap.tobul.Tb;

import com.yabongguri.dnflukeraidmap.R;

/**
 * Created by BitnaKeum on 2017-11-14.
 */

public class Others extends Activity implements View.OnClickListener {

    private Button mBtn_tan1;
    private Button mBtn_tan2;
    private Button mBtn_tan3;
    private Button mBtn_pa1;
    private Button mBtn_pa2;
    private Button mBtn_pa3;
    private Button mBtn_so1;
    private Button mBtn_so2;
    private Button mBtn_so3;
    private Button mBtn_tb;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jj_others);

        mBtn_tan1 = (Button)findViewById(R.id.btn_others_tan1);
        mBtn_tan2 = (Button)findViewById(R.id.btn_others_tan2);
        mBtn_tan3 = (Button)findViewById(R.id.btn_others_tan3);
        mBtn_pa1 = (Button)findViewById(R.id.btn_others_pa1);
        mBtn_pa2 = (Button)findViewById(R.id.btn_others_pa2);
        mBtn_pa3 = (Button)findViewById(R.id.btn_others_pa3);
        mBtn_so1 = (Button)findViewById(R.id.btn_others_so1);
        mBtn_so2 = (Button)findViewById(R.id.btn_others_so2);
        mBtn_so3 = (Button)findViewById(R.id.btn_others_so3);
        mBtn_tb = (Button)findViewById(R.id.btn_others_tb);

        mBtn_tan1.setOnClickListener(this);
        mBtn_tan2.setOnClickListener(this);
        mBtn_tan3.setOnClickListener(this);
        mBtn_pa1.setOnClickListener(this);
        mBtn_pa2.setOnClickListener(this);
        mBtn_pa3.setOnClickListener(this);
        mBtn_so1.setOnClickListener(this);
        mBtn_so2.setOnClickListener(this);
        mBtn_so3.setOnClickListener(this);
        mBtn_tb.setOnClickListener(this);
    }

    public void onClick(View v)
    {
        finish();
        Intent intent = null;

        if ((v.getId() == R.id.btn_others_pa1) ||
                (v.getId() == R.id.btn_others_pa2) ||
                (v.getId() == R.id.btn_others_pa3) ||
                (v.getId() == R.id.btn_others_tan3))
            intent = new Intent(this, Pppt.class);
        else if ((v.getId() == R.id.btn_others_tan1) ||
                (v.getId() == R.id.btn_others_tan2) ||
                (v.getId() == R.id.btn_others_so1) ||
                (v.getId() == R.id.btn_others_so2) ||
                (v.getId() == R.id.btn_others_so3))
            intent = new Intent(this, Ttsss.class);
        else if (v.getId() == R.id.btn_others_tb)
            intent = new Intent(this, Tb.class);

        if ((v.getId() == R.id.btn_others_pa1) || (v.getId() == R.id.btn_others_tan1))
            intent.putExtra("mapIndex", 0);
        else if ((v.getId() == R.id.btn_others_pa2) || (v.getId() == R.id.btn_others_tan2))
            intent.putExtra("mapIndex", 1);
        else if ((v.getId() == R.id.btn_others_pa3) || (v.getId() == R.id.btn_others_so1))
            intent.putExtra("mapIndex", 2);
        else if ((v.getId() == R.id.btn_others_tan3) || (v.getId() == R.id.btn_others_so2))
            intent.putExtra("mapIndex", 3);
        else if (v.getId() == R.id.btn_others_so3)
            intent.putExtra("mapIndex", 4);

        if (v.getId() != R.id.btn_others_tb)
            intent.putExtra("isOthers", true);

        if (intent != null)
            startActivity(intent);
    }
}