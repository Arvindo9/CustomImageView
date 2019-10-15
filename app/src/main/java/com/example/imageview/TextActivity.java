package com.example.imageview;

import android.app.Activity;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.imageview.utils.Utils;

/**
 * Author       : Arvindo Mondal
 * Created on   : 14-10-2019
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
public class TextActivity extends Activity {

    private LinearLayout mainLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.text_activity);


        mainLayout = findViewById(R.id.textActivity);
        setUp();
//        setUp1();
    }

    @Override
    public void onWindowFocusChanged (boolean hasFocus) {
//        if(hasFocus)
//            setUp();
    }

    private void setUp() {
        String[] array = {"kd", "djhfjdsf", "sdkjf kjd", "kjdf", "kdsfk jkmk ", "lkjsdklf jsdkljf klsdjf",
                            "sdkjfk sn", "jddn", "kjdf", "kmdskldf", "lksdjflkjsdf lkjdflk mdsfkmsdf kdjfkjdf",
        "kmdf", " ksdmfkmdsf;mksf;kd"};
        int rightMargin = 20;
        for(int i=0;i < array.length;i++){
            LinearLayout mainView = (LinearLayout)
                    LayoutInflater.from(this).inflate(R.layout.text_activity_layout,null);
            boolean ok = true;
            View lastView = null;
            int viewWidth = 0;
            while(ok && i < array.length) {
                TextView view = (TextView) LayoutInflater.from(this).inflate(R.layout.text_activity_text, null);
                view.setText(array[i] + i);

                LinearLayout.LayoutParams params =
                        new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                                LinearLayout.LayoutParams.WRAP_CONTENT);
                params.setMargins(0,0,rightMargin,0);
                view.setLayoutParams(params);

                if(lastView == null){
                    lastView = view;
                    mainView.addView(view);
                    viewWidth = Utils.getTextViewWeight(view) + rightMargin;
                    i++;
                }
                else {
                    viewWidth = viewWidth + Utils.getTextViewWeight(view) + rightMargin;
                    int screenWidth = Utils.getScreenWidth(TextActivity.this);

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
            mainLayout.addView(mainView);
        }
    }

    /*
    It's too early to check for getLocationOnScreen() in onCreate().
    The better place is at view level (if you are using custom views), is at onLayout().
    Only here, the view's size and position are calculated. In case, you aren't creating custom views,
    you could obtain it in activity level at onWindowFocusChanged() (with the activity having the focus).
     */
    private void setUpT2() {
        String[] array = {"kd", "djhfjdsf", "sdkjf kjd", "kjdf", "kdsfk jkmk ", "lkjsdklf jsdkljf klsdjf",
                            "sdkjfk sn", "jddn", "kjdf", "kmdskldf", "lksdjflkjsdf lkjdflk mdsfkmsdf kdjfkjdf",
        "kmdf", " ksdmfkmdsf;mksf;kd"};
        for(int i=1;i < array.length;i++){
            LinearLayout mainView = (LinearLayout)
                    LayoutInflater.from(this).inflate(R.layout.text_activity_layout,null);
            boolean ok = true;
            View lastView = null;
            int viewWidth = 0;
            while(ok) {
                TextView view = (TextView) LayoutInflater.from(this).inflate(R.layout.text_activity_text, null);
                view.setText(array[i] + i);

                if(lastView == null){
                    lastView = view;
                    mainView.addView(view);
                    viewWidth = Utils.getTextViewWeight(view);
                }
                else {
                    viewWidth = viewWidth + Utils.getTextViewWeight(view);
                    int screenWidth = Utils.getScreenWidth(TextActivity.this);

                    if (viewWidth < screenWidth) {
                        mainView.addView(view);
                        lastView = view;
                    } else {
                        ok = false;
                    }
                }
            }
            mainLayout.addView(mainView);
        }
    }

    private int sd(View view, LinearLayout v){
        Rect offsetViewBounds = new Rect();
//returns the visible bounds
        view.getDrawingRect(offsetViewBounds);
// calculates the relative coordinates to the parent
        v.offsetDescendantRectToMyCoords(view, offsetViewBounds);

        int relativeTop = offsetViewBounds.top;
        int relativeLeft = offsetViewBounds.left;
        return offsetViewBounds.right;
    }

    private void setUpT1() {
        for(int i=1;i<=20;i++){
            LinearLayout mainView = (LinearLayout)
                    LayoutInflater.from(this).inflate(R.layout.text_activity_layout,null);
            boolean ok = true;
            View lastView = null;
            while(ok) {
                TextView view = (TextView) LayoutInflater.from(this).inflate(R.layout.text_activity_text, null);
                view.setText("HELLO " + i);

                if(lastView == null){
                    lastView = view;
                    mainView.addView(view);
                }
                else {
                    int viewWidth = Utils.getTextViewWeight(view);
                    float x = lastView.getY();
                    //last view
                    int location = Utils.getDistance(TextActivity.this, lastView);
                    int sereenWidth = Utils.getScreenWidth(TextActivity.this);
                    if (sereenWidth - x - location > 0) {
                        mainView.addView(view);
                        lastView = view;
                    } else {
                        ok = false;
                    }
                }
            }
            mainLayout.addView(mainView);
        }
    }

    private void setUp1() {
        for(int j=1;j<=3;j++) {
            LinearLayout mainView = (LinearLayout)
                    LayoutInflater.from(this).inflate(R.layout.text_activity_layout, null);
            for (int i = 1; i <= 3; i++) {
                TextView view = (TextView) LayoutInflater.from(this).inflate(R.layout.text_activity_text, null);
                view.setText("HELLO " + i);
                mainView.addView(view);
            }
            mainLayout.addView(mainView);
        }
    }
}
