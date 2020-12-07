package com.e.wheretoeat.main.api

import com.e.wheretoeat.main.models.ApiRestaurant
import com.e.wheretoeat.main.models.ApiRestaurantResponse
import retrofit2.Call

class ApiRepository {

    fun getStats(): Call<ApiRestaurant> {
        return RetrofitInstance.api.getStats()
    }

    fun getAllRestaurants() : Call<ApiRestaurantResponse>{
        return RetrofitInstance.api.getAllRestaurants()
    }
}