package com.e.wheretoeat.main.activities

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.e.wheretoeat.R
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET


class SplashActivity : AppCompatActivity() {
    private val BASE_URL =
        "http://opentable.herokuapp.com/api/"

       val retrofit = Retrofit.Builder()
        .addConverterFactory(ScalarsConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()

    interface RestaurantAPIService {
        @GET("GET /api/stats")
        fun getProperties():
                Call<String>
    }

//    object RestaurantApi {
//        val retrofitService: RestaurantAPIService by lazy {
//            retrofit.create(RestaurantAPIService::class.java)
//        }
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)


        val handler = Handler()
        handler.postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }, 1500)
    }

}