package com.cikarastudio.cikaramympasi.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.cikarastudio.cikaramympasi.R;


public class SplashActivity extends AppCompatActivity {

    private ImageView logo;
    private int loadingTime = 4000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        logo = findViewById(R.id.logo_cikara);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //setelah loading maka akan langsung berpindah ke intro activity
                Intent home = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(home);
                finish();

            }
        }, loadingTime);

        Animation myanim = AnimationUtils.loadAnimation(SplashActivity.this, R.anim.splashanimation);
        logo.startAnimation(myanim);

    }
}
