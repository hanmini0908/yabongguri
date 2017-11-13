package com.yabongguri.dnflukelaidmap;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;

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
        int nId = RuntimeConfig.getCardPreference(this);
        if (nId != 0)
            mRg_tan.check(nId);

        mBtn_card_save = (Button)findViewById(R.id.btn_card_save);
        mBtn_card_save.setOnClickListener(this);
    }

    public void onClick(View v) {
        if (v.getId() == R.id.btn_card_save) {
            int nId = mRg_tan.getCheckedRadioButtonId();
            RuntimeConfig.setCardPreference(this, nId);
            finish();
        }
    }
}
