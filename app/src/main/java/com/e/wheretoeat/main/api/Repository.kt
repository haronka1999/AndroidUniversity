package com.e.wheretoeat.main.api

import com.e.wheretoeat.main.models.Restaurant

class Repository {

    suspend fun getRestaurant(): Restaurant{
       return RetrofitInstance.api.getRestaurant()
    }
}