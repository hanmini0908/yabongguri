package com.yabongguri.dnflukeraidmap;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.app.Activity;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.yabongguri.dnflukeraidmap.illuke.IlMapSelect;

import org.w3c.dom.Text;

public class MainActivity extends Activity implements View.OnClickListener {

    private Button mBtn_il;
    private Button mBtn_lr;
    private Button mBtn_close;
    private Button mBtn_hell_channel;
    private ImageView mImg_email;
    private TextView mTv_version;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (Define.IS_MAIN_AD) {     //광고 없는 버전 만들기 위해서 추가해놓음
            AdView adView = (AdView) findViewById(R.id.adView);
            AdRequest adRequest = new AdRequest.Builder().build();
            adView.loadAd(adRequest);
        }

        mBtn_il = (Button)findViewById(R.id.btn_il);
        mBtn_lr = (Button)findViewById(R.id.btn_lr);
        mBtn_close = (Button)findViewById(R.id.btn_close);
        mBtn_hell_channel = (Button)findViewById(R.id.btn_hell_channel);
        mImg_email = (ImageView)findViewById(R.id.img_email);
        mTv_version = (TextView)findViewById(R.id.tv_version);

        mBtn_il.setOnClickListener(this);
        mBtn_lr.setOnClickListener(this);
        mBtn_close.setOnClickListener(this);
        mBtn_hell_channel.setOnClickListener(this);
        mImg_email.setOnClickListener(this);

        //version name 가져오는 코드
        PackageInfo pInfo = null;
        try {
            pInfo = getPackageManager().getPackageInfo(this.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        if (pInfo != null) {
            String strVersion = pInfo.versionName;
            mTv_version.setText(" " + strVersion);
        }
    }

    public void onClick(View v)
    {
        Intent intent = null;

        switch (v.getId())
        {
            case R.id.btn_il:
                intent = new Intent(this, IlMapSelect.class);
                break;
            case R.id.btn_lr:
                intent = new Intent(this, CardPattern.class);
                break;
            case R.id.btn_hell_channel:
                intent = new Intent(this, HellChannel.class);
                break;
            case R.id.btn_close:
                finishApp();
                break;
            case R.id.img_email:
                sendEmail("yabongguri@gmail.com");
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

    private void sendEmail(String email){
        try{
            Intent emailIntent = new Intent(Intent.ACTION_SEND);
            emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{email});
            emailIntent.setType("plain/text");
            startActivity(Intent.createChooser(emailIntent, getString(R.string.email_chooser)));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void finishApp() {
        ActivityCompat.finishAffinity(this);
    }
}
