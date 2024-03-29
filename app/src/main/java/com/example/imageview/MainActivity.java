package com.example.imageview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.imageview.core.AvatarView;
import com.example.imageview.model.User;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUp();
    }

    private void setUp() {
        User user = new User();
        user.setColor(getResources().getColor(R.color.text));
        user.setAvatarUrl("https://i.pinimg.com/originals/0b/b3/70/0bb3704734e0a535ec846772f7d28be7.jpg");

        AvatarView avatarView = (AvatarView) findViewById(R.id.avatar_view);
        avatarView.setUser(user);
    }

    public void onClick(View view) {
        if(view.getId() == R.id.button)
            startActivity(new Intent(MainActivity.this, HeightRatioViewActivity.class));
        else if(view.getId() == R.id.addviews)
            startActivity(new Intent(MainActivity.this, TextActivity.class));
        else if(view.getId() == R.id.textViewVertical)
            startActivity(new Intent(MainActivity.this, TextVerticalActivity.class));
        else if(view.getId() == R.id.addviewsBase)
            startActivity(new Intent(MainActivity.this, TextActivityBase.class));
    }
}
