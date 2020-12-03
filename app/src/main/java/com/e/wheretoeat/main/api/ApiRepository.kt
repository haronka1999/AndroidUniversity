package com.e.wheretoeat.main.api

import com.e.wheretoeat.main.models.Restaurant

class ApiRepository {
    suspend fun getRestaurant(id : Int): ApiRestaurant{
       return RetrofitInstance.api.getRestaurant(id)
    }
}