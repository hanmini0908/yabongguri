package com.yabongguri.dnflukeraidmap.juji;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.yabongguri.dnflukeraidmap.R;
import com.yabongguri.dnflukeraidmap.RuntimeConfig;

/**
 * Created by BitnaKeum on 2017-11-11.
 */

public class CardPattern extends Activity implements View.OnClickListener {

    private Button mBtn_card_save;
    private RadioGroup mRg_tan;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cardpattern);

        mRg_tan = (RadioGroup)findViewById(R.id.rg_tan);
        int nIndex = RuntimeConfig.getCardPreference(this);
        RadioButton rb = (RadioButton)mRg_tan.getChildAt(nIndex);

        if ((nIndex >= 0) && (rb != null))
            rb.setChecked(true);

        mBtn_card_save = (Button)findViewById(R.id.btn_card_save);
        mBtn_card_save.setOnClickListener(this);
    }

    public void onClick(View v) {
        if (v.getId() == R.id.btn_card_save) {
            int nId = mRg_tan.getCheckedRadioButtonId();
            View radioButton = mRg_tan.findViewById(nId);
            int nIndex = mRg_tan.indexOfChild(radioButton);

            RuntimeConfig.setCardPreference(this, nIndex);
            Intent intent = new Intent(this, Jj.class);
            startActivity(intent);
        }
    }
}