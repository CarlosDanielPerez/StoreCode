package com.example.storecode_android.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.storecode_android.MainActivity;
import com.example.storecode_android.R;

public class SplashScreenActivity extends AppCompatActivity {
    ImageView ivLogoStorecode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        ivLogoStorecode = findViewById(R.id.ivLogoStorecode);
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.animacion);
        ivLogoStorecode.startAnimation(animation);

        Intent intent = new Intent(this, MainActivity.class);

        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                startActivity(intent);
                finish();


            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

    }
}