package com.e.wheretoeat.main.api

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.e.wheretoeat.main.models.ApiRestaurant
import com.google.android.play.core.internal.e
import kotlinx.coroutines.launch
import java.lang.Exception

class MainViewModel(private val apiRepository: ApiRepository) : ViewModel() {
    private val myResponse: MutableLiveData<List<ApiRestaurant>> = MutableLiveData()

    val response: MutableLiveData<List<ApiRestaurant>>
        get() =  myResponse

    init {
       getAllRestaurants("Dallas")

    }


    fun getAllRestaurants(city: String) {
        viewModelScope.launch {
            try{
                val listResult = apiRepository.getAllRestaurants(city)
                myResponse.value = listResult
            }catch (e : Exception){
                Log.d("Helo", "Failure : $e")
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