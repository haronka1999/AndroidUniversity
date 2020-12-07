package com.e.wheretoeat.main.api

import com.e.wheretoeat.main.models.ApiRestaurant
import com.e.wheretoeat.main.models.ApiRestaurantResponse
import retrofit2.Call
import retrofit2.http.GET

interface SimpleApi {

    //this function wll return restaurants in a city
    @GET("restaurants?country=AW")
    fun getAllRestaurants(): Call<ApiRestaurantResponse>

    @GET("restaurants/107257")
    fun getStats(): Call<ApiRestaurant>

}
