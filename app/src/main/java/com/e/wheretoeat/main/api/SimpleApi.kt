package com.e.wheretoeat.main.api

import com.e.wheretoeat.main.models.ApiRestaurant
import retrofit2.http.GET
import retrofit2.http.Query

//sending get request

interface SimpleApi {

    //this function wll return a restaurant
    @GET("api/restaurants?city=Dallas")
    suspend fun getRestaurant(@Query("id" ) id : Int) : ApiRestaurant

}
