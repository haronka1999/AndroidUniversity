package com.e.wheretoeat.main.api

import com.e.wheretoeat.main.models.ApiRestaurant
import retrofit2.Call

class ApiRepository {

    fun getStats(): Call<ApiRestaurant> {
        return RetrofitInstance.api.getStats()
    }

    fun getAllRestaurants() : Call<List<ApiRestaurant>>{
        return RetrofitInstance.api.getAllRestaurants()
    }
}