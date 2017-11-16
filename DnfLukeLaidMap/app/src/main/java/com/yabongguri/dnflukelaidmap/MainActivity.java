package com.yabongguri.dnflukelaidmap;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity implements View.OnClickListener {

    private Button mBtn_card;
    private Button mBtn_jj;
    private Button mBtn_tb;
    private Button mBtn_close;
    private Button mBtn_hell_channel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBtn_card = (Button)findViewById(R.id.btn_select_card_pattern);
        mBtn_jj = (Button)findViewById(R.id.btn_jj);
        mBtn_tb = (Button)findViewById(R.id.btn_tb);
        mBtn_close = (Button)findViewById(R.id.btn_close);
        mBtn_hell_channel = (Button)findViewById(R.id.btn_hell_channel);

        mBtn_card.setOnClickListener(this);
        mBtn_jj.setOnClickListener(this);
        mBtn_tb.setOnClickListener(this);
        mBtn_close.setOnClickListener(this);
        mBtn_hell_channel.setOnClickListener(this);
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
            case R.id.btn_hell_channel:
                intent = new Intent(this, HellChannel.class);
                break;
            case R.id.btn_close:
                finish();
                break;
        }

        if (intent != null)
            startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder alertdialog = new AlertDialog.Builder(this);
        alertdialog.setMessage(getString(R.string.exit_message));

        alertdialog.setPositiveButton(getString(R.string.confirm), new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
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
}
