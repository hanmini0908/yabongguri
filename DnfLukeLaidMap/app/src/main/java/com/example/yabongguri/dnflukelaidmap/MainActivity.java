package com.example.yabongguri.dnflukelaidmap;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.yabogguri.dnflukelaidmap.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mBtn_card;
    private Button mBtn_jj;
    private Button mBtn_tb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBtn_card = (Button)findViewById(R.id.btn_select_card_pattern);
        mBtn_jj = (Button)findViewById(R.id.btn_jj);
        mBtn_tb = (Button)findViewById(R.id.btn_tb);

        mBtn_card.setOnClickListener(this);
        mBtn_jj.setOnClickListener(this);
        mBtn_tb.setOnClickListener(this);
    }

    public void onClick(View v)
    {
        Intent intent = null;

        switch (v.getId())
        {
            case R.id.btn_select_card_pattern:
                intent = new Intent(this, CardPattern.class);
                break;
            case R.id.btn_jj:
                intent = new Intent(this, Jj.class);
                break;
            case R.id.btn_tb:
                intent = new Intent(this, Tb.class);
                break;
        }

        if (intent != null)
            startActivity(intent);
    }
}
