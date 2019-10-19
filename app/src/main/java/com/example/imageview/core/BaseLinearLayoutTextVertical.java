package com.example.imageview.core;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.LayoutRes;
import androidx.appcompat.widget.LinearLayoutCompat;

import com.example.imageview.R;
import com.example.imageview.utils.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Author       : Arvindo Mondal
 * Created on   : 18-10-2019
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
public class BaseLinearLayoutTextVertical extends LinearLayoutCompat {
    @LayoutRes
    private int layout;
    @LayoutRes
    private int layoutText;
    float rightMargin = 20f;

    private ArrayList<String> list;

    public BaseLinearLayoutTextVertical(Context context) {
        super(context);
        list = new ArrayList<>();
        init(context, null);
    }

    public BaseLinearLayoutTextVertical(Context context, AttributeSet attrs) {
        super(context, attrs);
        getAttributes(attrs);
        list = new ArrayList<>();
        init(context, attrs);
    }

    public BaseLinearLayoutTextVertical(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        getAttributes(attrs);
        list = new ArrayList<>();
        init(context, attrs);
    }

    public void addItems(List<String> model) {
        list.clear();
        list.addAll(model);
        onItemUpdates();
    }

    public void clearItems() {
        list.clear();
    }

    private void getAttributes(AttributeSet attrs) {
        TypedArray type = getContext().getTheme().obtainStyledAttributes(attrs, R.styleable.BaseLinearLayoutTextVertical,
                0, 0);

        try {
            int textBackground = type.getColor(R.styleable.BaseLinearLayoutTextVertical_TextBackground,
                    Color.parseColor("#FFFFFF"));
            int textColor = type.getColor(R.styleable.BaseLinearLayoutTextVertical_TextColor,
                    Color.parseColor("#FFFFFF"));
            layout = type.getResourceId(R.styleable.BaseLinearLayoutTextVertical_Layout, 0);
            layoutText = type.getResourceId(R.styleable.BaseLinearLayoutTextVertical_LayoutText,0);
            rightMargin = type.getDimension(R.styleable.BaseLinearLayoutTextVertical_RightMargin,0);
        } finally {
            type.recycle();
        }
    }

    private void init(Context context, AttributeSet attrs) {
        list.add("kd");
        list.add("djhfjdsf");
        list.add("sdkjf kjd");
        list.add("kjdf");
        list.add("kdsfk jkmk ");
        list.add("lkjsdklf jsdkljf klsdjf");
        list.add("sdkjfk sn");
        list.add("jddn");
        list.add("kjdf");
        list.add("lksdjflkjsdf lkjdflk mdsfkmsdf kmdskldf");
        list.add("kdjfkjdf");
        list.add(" ksdmfkmdsf;mksf;kmdf");

        onItemUpdates();
    }

    private void onItemUpdates() {
        for(int i=0;i < list.size();i++){
            LinearLayout mainView = (LinearLayout)
                    LayoutInflater.from(getContext()).inflate(layout,null);
            boolean ok = true;
            View lastView = null;
            float viewWidth = 0;
            while(ok && i < list.size()) {
                TextView view = (TextView) LayoutInflater.from(getContext()).inflate(layoutText, null);
                view.setText(list.get(i) + i);

                LinearLayout.LayoutParams params =
                        new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                                LinearLayout.LayoutParams.WRAP_CONTENT);
                params.setMargins(0,0, (int) rightMargin,0);
                view.setLayoutParams(params);

                if(lastView == null){
                    lastView = view;
                    mainView.addView(view);
                    viewWidth = Utils.getTextViewWeight(view) + rightMargin;
                    i++;
                }
                else {
                    viewWidth = viewWidth + Utils.getTextViewWeight(view) + rightMargin;
                    int screenWidth = Utils.getScreenWidth(getContext());

                    if (viewWidth < screenWidth) {
                        mainView.addView(view);
                        lastView = view;
                        i++;
                    } else {
                        ok = false;
                        i--;
                    }
                }
            }
            this.addView(mainView);
        }
    }
}
