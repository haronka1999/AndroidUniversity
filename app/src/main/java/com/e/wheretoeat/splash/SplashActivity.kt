package com.e.wheretoeat.splash

import android.animation.Animator
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.airbnb.lottie.LottieAnimationView
import com.e.wheretoeat.R
import com.e.wheretoeat.main.MainActivity


@Suppress("DEPRECATION")
class SplashActivity : AppCompatActivity() {

    private lateinit var appName: ImageView
    private lateinit var splashImg: ImageView
    private lateinit var lottieAnimation: LottieAnimationView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)


        appName = findViewById(R.id.appName)
        splashImg = findViewById(R.id.splashImage)
        lottieAnimation = findViewById(R.id.lottie)

        splashImg.animate().translationY((-2000).toFloat()).setDuration(1000).startDelay = 4000
        appName.animate().translationY((1800).toFloat()).setDuration(1000).startDelay = 4000
        lottieAnimation.animate().translationY((1800).toFloat()).setDuration(1000).startDelay = 4000

       // lottieAnimation.addAnimatorListener(Animator.AnimatorListener)
        if ( ! lottieAnimation.isAnimating){
            startActivity(Intent(this, MainActivity::class.java))
        }
//        Handler().postDelayed({
//
//            startActivity(Intent(this, MainActivity::class.java))
//            finish()
//        }, 1000)
//

    }
}