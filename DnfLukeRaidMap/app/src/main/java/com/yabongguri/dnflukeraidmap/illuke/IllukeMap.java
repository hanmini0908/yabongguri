package com.yabongguri.dnflukeraidmap.illuke;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.yabongguri.dnflukeraidmap.R;
import com.yabongguri.dnflukeraidmap.tobul.Success;

import java.util.Locale;

/**
 * Created by BitnaKeum on 2017-11-21.
 */

public class IllukeMap extends Activity {

    private TextView mTv_il;
    private TextView mTv_named;
    private ImageView mIv_il;
    private Button mBtn_prev;

    private int mMapIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_il_map);

        //Activity에 입력이 없어도 화면 꺼지지 않게 하기
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        mTv_il = (TextView)findViewById(R.id.tv_il);
        mTv_named = (TextView)findViewById(R.id.tv_il_named);
        mIv_il = (ImageView)findViewById(R.id.iv_il);
        mBtn_prev = (Button)findViewById(R.id.btn_il_prev);

        if (savedInstanceState == null) {
            mMapIndex = 0;
            Intent intent = getIntent();
            if (intent != null) {
                mMapIndex = intent.getIntExtra("mapIndex", 0);
            }
        } else {
            mMapIndex = savedInstanceState.getInt("MapIndex");
        }

        viewMapIndex();
    }

    public void onSaveInstanceState(Bundle outState) {
        outState.putInt("MapIndex", mMapIndex);
        super.onSaveInstanceState(outState);
    }

    private void viewMapIndex() {
        if (mMapIndex == 0)
            tan();
        else if (mMapIndex == 1)
            so();
        else if (mMapIndex == 2)
            pa();
        else if (mMapIndex == 3)
            lu();
        else if (mMapIndex == 4)
            cal();
        else if (mMapIndex == 5)
            luke();
    }

    public void onClickPrev(View v) {
        if (mMapIndex > 0) {
            mMapIndex--;
            viewMapIndex();
        }
        else if (mMapIndex == 0) {
            finish();
        }
    }

    public void onClickNext(View v) {
        if (mMapIndex < 5) {
            mMapIndex++;
            viewMapIndex();
        }
        else if (mMapIndex == 5) {
            Intent intent = new Intent(this, Success.class);
            intent.putExtra("isRaid", false);
            startActivity(intent);
        }
    }

    private void tan() {
        String strMap = getString(R.string.jj_t);
        mTv_il.setText(strMap);

        String strMst1 = getString(R.string.mst_metal);
        String strMst2 = getString(R.string.mst_beki);
        String strMst3 = getString(R.string.mst_karina);
        String strMst4 = getString(R.string.mst_the7);
        String strBoss = getString(R.string.mst_losa);
        int nDrawableId = R.drawable.map_il_tan;

        String strNamed = String.format(Locale.US, "%d. %s\n%d. %s\n%d. %s\n%d. %s\n%s. %s\n", 1, strMst1, 2, strMst2, 3, strMst3, 4, strMst4, "B", strBoss);
        mTv_named.setText(strNamed);
        mIv_il.setImageResource(nDrawableId);
    }
    private void so() {
        String strMap = getString(R.string.jj_s);
        mTv_il.setText(strMap);

        String strMst1 = getString(R.string.mst_iron);
        String strMst2 = getString(R.string.mst_ramp);
        String strMst3 = getString(R.string.mst_jakel);
        String strBoss = getString(R.string.mst_habub);
        int nDrawableId = R.drawable.map_il_so;

        mTv_named.setText("1. " + strMst1 +
                        "\n2. " + strMst2 +
                        "\n3. " + strMst3 +
                        "\nB. " + strBoss);
        mIv_il.setImageResource(nDrawableId);
    }

    private void pa() {
        String strMap = getString(R.string.jj_p);
        mTv_il.setText(strMap);

        String strMst1 = getString(R.string.mst_red);
        String strMst2 = getString(R.string.mst_nerbe);
        String strMst3 = getString(R.string.mst_argos);
        String strMst4 = getString(R.string.mst_mark);
        String strBoss = getString(R.string.mst_bupon);
        int nDrawableId = R.drawable.map_il_pa;

        mTv_named.setText("1. " + strMst1 +
                        "\n2. " + strMst2 +
                        "\n3. " + strMst3 +
                        "\n4. " + strMst4 +
                        "\nB. " + strBoss);
        mIv_il.setImageResource(nDrawableId);
    }

    private void lu() {
        String strMap = getString(R.string.tb_lu2);
        mTv_il.setText(strMap);

        String strMst1 = getString(R.string.mst_aslan);
        String strMst2 = getString(R.string.mst_beil);
        String strMst3 = getString(R.string.mst_horus);
        String strBoss = getString(R.string.mst_prince);
        int nDrawableId = R.drawable.map_il_lu;

        mTv_named.setText("1. " + strMst1 +
                        "\n2. " + strMst2 +
                        "\n3. " + strMst3 +
                        "\nB. " + strBoss);
        mIv_il.setImageResource(nDrawableId);
    }

    private void cal() {
        String strMap = getString(R.string.tb_cal2);
        mTv_il.setText(strMap);

        String strMst1 = getString(R.string.mst_snaider);
        String strMst2 = getString(R.string.mst_yasin);
        String strMst3 = getString(R.string.mst_anubis);
        String strBoss = getString(R.string.mst_beara);
        int nDrawableId = R.drawable.map_il_cal;

        mTv_named.setText("1. " + strMst1 +
                        "\n2. " + strMst2 +
                        "\n3. " + strMst3 +
                        "\nB. " + strBoss);
        mIv_il.setImageResource(nDrawableId);
    }

    private void luke() {
        String strMap = getString(R.string.il_luke);
        mTv_il.setText(strMap);

        String strBoss = getString(R.string.mst_luke);
        mTv_named.setText("B. " + strBoss);

        int nDrawableId = R.drawable.map_il_luke;
        mIv_il.setImageResource(nDrawableId);
    }
}
