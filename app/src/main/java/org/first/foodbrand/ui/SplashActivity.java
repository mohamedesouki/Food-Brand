package org.first.foodbrand.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;

import org.first.foodbrand.R;

public class SplashActivity extends AppCompatActivity {
        TextView textViewSplash;

        ImageView imageViewSplash;
    LottieAnimationView heart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        textViewSplash = findViewById(R.id.textViewSplash);
        imageViewSplash = findViewById(R.id.imageViewSplash);
        heart = findViewById(R.id.lottie);
        heart.animate().setDuration(3100);
        textViewSplash.animate().translationY(-600).setDuration(3000);
        //imageViewSplash.animate().translationY(-2100).setDuration(1000).setStartDelay(4000);


       Thread thread = new Thread(){

            @Override
            public void run() {
                try {
                    sleep(4500);






                }catch (Exception e){
                    e.printStackTrace();
                }finally {
                    startActivity(new Intent(SplashActivity.this,MainActivity.class));
                }
            }
        };

        thread.start();



    }
    }
