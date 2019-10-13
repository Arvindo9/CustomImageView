package com.example.imageview;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;

import com.example.imageview.utils.Utils;

/**
 * Author       : Arvindo Mondal
 * Created on   : 13-10-2019
 * Email        : arvindo@aiprog.in
 * Company      : AIPROG
 * Designation  : Programmer
 * About        : I am a human can only think, I can't be a person like machine which have lots of memory and knowledge.
 * Quote        : No one can measure limit of stupidity but stupid things bring revolutions
 * Strength     : Never give up
 * Motto        : To be known as great Mathematician
 * Skills       : Algorithms and logic
 * Website      : www.aiprog.in
 */
public class HeightRatioViewActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.height_ration_activity);

//        View view = findViewById(R.id.layout);
        LinearLayout mainLayout = findViewById(R.id.layout);
//        RelativeLayout layout = findViewById(R.id.imageLayout);
        LinearLayout.LayoutParams layoutParams = new
                LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                Utils.getScreenHeight(this)/2);
        mainLayout.setLayoutParams(layoutParams);
    }
}
