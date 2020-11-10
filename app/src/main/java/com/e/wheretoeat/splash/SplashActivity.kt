package com.e.wheretoeat.splash

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.airbnb.lottie.LottieAnimationView
import com.e.wheretoeat.R

class SplashActivity : AppCompatActivity() {

    private lateinit var logo : ImageView
    private lateinit var appName : ImageView
    private lateinit var splashImg : ImageView
    private lateinit var lottieAnimation : LottieAnimationView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        logo = findViewById(R.id.logo);
        appName = findViewById(R.id.appName);
        splashImg = findViewById(R.id.splashImage);
        lottieAnimation = findViewById(R.id.lottie);

        splashImg.animate().translationY((-1600).toFloat()).setDuration(1000).startDelay = 4000
        logo.animate().translationY((1400).toFloat()).setDuration(1000).startDelay = 4000
        appName.animate().translationY((1400).toFloat()).setDuration(1000).startDelay = 4000
        lottieAnimation.animate().translationY((1400).toFloat()).setDuration(1000).startDelay = 4000

    }
}