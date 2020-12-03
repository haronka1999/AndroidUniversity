package com.e.wheretoeat.main.api

import com.e.wheretoeat.main.models.ApiRestaurant
import retrofit2.http.GET
import retrofit2.http.Query

//sending get request

interface SimpleApi {

    //this function wll return restaurants in a city
    @GET("restaurants")
    suspend fun getAllRestaurants(@Query("city" ) city : String) : List<ApiRestaurant>

}
