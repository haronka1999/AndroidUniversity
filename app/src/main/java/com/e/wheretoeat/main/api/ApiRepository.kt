package com.e.wheretoeat.main.api

import com.e.wheretoeat.main.models.ApiRestaurant

class ApiRepository {


    suspend fun getAllRestaurants(city : String): List<ApiRestaurant> {
       return RetrofitInstance.api.getAllRestaurants(city)
    }

}