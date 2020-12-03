package com.e.wheretoeat.main.api

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.e.wheretoeat.main.models.ApiRestaurant
import com.google.android.play.core.internal.e
import kotlinx.coroutines.launch
import java.lang.Exception

class MainViewModel(private val apiRepository: ApiRepository) : ViewModel() {
    val myResponse: MutableLiveData<List<ApiRestaurant>> = MutableLiveData()


    fun getAllRestaurants(city: String) {
        viewModelScope.launch {
            try{
                val response = apiRepository.getAllRestaurants(city)
                myResponse.value = response
            }catch (e : Exception){
                Log.d("Helo", "Failure  $e")
            }
        }
    }

//    fun convertToEntity(apiRestaurant: ApiRestaurant): Restaurant {
//        val restaurant = Restaurant(
//            0,
//            apiRestaurant.name,
//            apiRestaurant.address,
//            apiRestaurant.price,
//            apiRestaurant.country
//        )
//    }
}