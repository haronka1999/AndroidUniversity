package com.e.wheretoeat.main.api

import com.e.wheretoeat.main.models.Restaurant
import retrofit2.http.GET

//sending get request

interface SimpleApi {

    //this function wll return a restaurant
    @GET("api/restaurants/4")
    suspend fun getRestaurant() : Restaurant
}
