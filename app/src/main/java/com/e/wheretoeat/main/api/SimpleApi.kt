package com.e.wheretoeat.main.api

import com.e.wheretoeat.main.models.ApiRestaurant
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

//sending get request

interface SimpleApi {

    //this function wll return restaurants in a city
    @GET("restaurants?city=Chicago")
    fun getAllRestaurants(): Call<List<ApiRestaurant>>

    @GET("restaurants/107257")
    fun getStats(): Call<ApiRestaurant>

}
