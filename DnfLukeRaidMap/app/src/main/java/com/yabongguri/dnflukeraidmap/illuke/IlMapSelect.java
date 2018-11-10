package com.yabongguri.dnflukeraidmap.illuke;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import com.yabongguri.dnflukeraidmap.Define;
import com.yabongguri.dnflukeraidmap.R;

/**
 * Created by BitnaKeum on 2017-11-21.
 */

public class IlMapSelect extends Activity implements View.OnTouchListener {

    private ImageView mIv_il_select;
    private boolean mIsPortrait = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_il_map_select);

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE)
            mIsPortrait = false;

        mIv_il_select = (ImageView)findViewById(R.id.iv_il_select);
        mIv_il_select.setOnTouchListener(this);

        //디스플레이 크기 구하는 코드
//        Display display = getWindowManager().getDefaultDisplay();
//        Point size = new Point();
//        display.getSize(size);
//        int nDsplayWidth = size.x;
//        int nDisplayHeight = size.y;
    }

    private int mIntPortMapPoint = -1;
    private int mIntLandMapPoint = -1;

    private void buttonMapPoint(View v, MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();
        int w = v.getWidth();
        int h = v.getHeight();
        int l = v.getLeft();
        int r = v.getRight();
        int t = v.getTop();

        if (mIsPortrait) {
            boolean b05x = ((x > (w * 0.32 + l)) && (x < (w * 0.72 + l)));
            boolean b0y = ((y > (h * 0.8 + t)) && (y < (h * 0.95 + t)));
            boolean b13x = ((x > l) && (x < (w * 0.4 + l)));
            boolean b12y = ((y > (h * 0.64 + t)) && (y < (h * 0.78 + t)));
            boolean b24x = ((x > (w * 0.6 + l)) && (x < r));
            boolean b34y = ((y > (h * 0.22 + t)) && (y < (h * 0.36 + t)));
            boolean b5y = ((y > (h * 0.064 + t)) && (y < (h * 0.2 + t)));

            if (b05x && b0y)
                mIntPortMapPoint = 0;
            else if (b13x && b12y)
                mIntPortMapPoint = 1;
            else if (b24x && b12y)
                mIntPortMapPoint = 2;
            else if (b13x && b34y)
                mIntPortMapPoint = 3;
            else if (b24x && b34y)
                mIntPortMapPoint = 4;
            else if (b05x && b5y)
                mIntPortMapPoint = 5;
        } else {    //LandScape
            boolean b05x = ((x > (w * 0.39 + l)) && (x < (w * 0.63 + l)));
            boolean b0y = ((y > (h * 0.67 + t)) && (y < (h * 0.91 + t)));
            boolean b13x = ((x > (w * 0.06 + l)) && (x < (w * 0.3 + l)));
            boolean b12y = ((y > (h * 0.63 + t)) && (y < (h * 0.87 + t)));
            boolean b24x = ((x > (w * 0.7 + l)) && (x < (w * 0.94 + l)));
            boolean b34y = ((y > (h * 0.1 + t)) && (y < (h * 0.33 + t)));
            boolean b5y = ((y > (h * 0.08 + t)) && (y < (h * 0.31 + t)));

            if (b05x && b0y)
                mIntLandMapPoint = 0;
            else if (b13x && b12y)
                mIntLandMapPoint = 1;
            else if (b24x && b12y)
                mIntLandMapPoint = 2;
            else if (b13x && b34y)
                mIntLandMapPoint = 3;
            else if (b24x && b34y)
                mIntLandMapPoint = 4;
            else if (b05x && b5y)
                mIntLandMapPoint = 5;
        }
    }

    public boolean onTouch(View v, MotionEvent event)
    {
        buttonMapPoint(v, event);

        if (!Define.IS_REFRESH_ACTIVITY) {
            Intent intent = null;
            int mapIndex = -1;
//                      0: 탄생의 성소
//                      1: 소멸의 성소
//                      2: 파급의 성소
//                      3: 빛의 제단
//                      4: 어둠의 제단
//                      5: 솔리움 마키나

            if (v.getId() == R.id.iv_il_select) {
                intent = new Intent(this, IllukeMap.class);

                if (mIsPortrait) {
                    mapIndex = mIntPortMapPoint;
                } else {    //LandScape
                    mapIndex = mIntLandMapPoint;
                }
                intent.putExtra("mapIndex", mapIndex);
            }

            if ((mapIndex >= 0) && (event.getAction() == MotionEvent.ACTION_UP) && (intent != null)) {
                startActivity(intent);
            }
        }
        else {
            Intent intent = null;

            if (v.getId() == R.id.iv_il_select) {
                if (mIsPortrait) {
                    if (mIntPortMapPoint == 0)
                        intent = new Intent(this, IllukeMap1.class);
                    else if (mIntPortMapPoint == 1)
                        intent = new Intent(this, IllukeMap2.class);
                    else if (mIntPortMapPoint == 2)
                        intent = new Intent(this, IllukeMap3.class);
                    else if (mIntPortMapPoint == 3)
                        intent = new Intent(this, IllukeMap4.class);
                    else if (mIntPortMapPoint == 4)
                        intent = new Intent(this, IllukeMap5.class);
                    else if (mIntPortMapPoint == 5)
                        intent = new Intent(this, IllukeMap6.class);
                } else {    //LandScape
                    if (mIntLandMapPoint == 0)
                        intent = new Intent(this, IllukeMap1.class);
                    else if (mIntLandMapPoint == 1)
                        intent = new Intent(this, IllukeMap2.class);
                    else if (mIntLandMapPoint == 2)
                        intent = new Intent(this, IllukeMap3.class);
                    else if (mIntLandMapPoint == 3)
                        intent = new Intent(this, IllukeMap4.class);
                    else if (mIntLandMapPoint == 4)
                        intent = new Intent(this, IllukeMap5.class);
                    else if (mIntLandMapPoint == 5)
                        intent = new Intent(this, IllukeMap6.class);
                }
            }

            if ((event.getAction() == MotionEvent.ACTION_UP) && (intent != null)) {
                startActivity(intent);
            }
        }
            return true;
    }
}
