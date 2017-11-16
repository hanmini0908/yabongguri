package com.yabongguri.dnflukelaidmap;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;

/**
 * Created by JoHanmin on 2017-11-15.
 */

public class HellChannel extends Activity {

    ImageView img;
    AnimationDrawable ani;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hell_channel);

        img=(ImageView)findViewById(R.id.img);
        ani=(AnimationDrawable)img.getDrawable();

        ani.start();
    }
}
