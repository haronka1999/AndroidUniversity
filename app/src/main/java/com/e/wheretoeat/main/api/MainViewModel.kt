package com.e.wheretoeat.main.api

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.e.wheretoeat.main.models.Restaurant
import kotlinx.coroutines.launch

class MainViewModel(private val apiRepository: ApiRepository) : ViewModel() {
    val myResponse: MutableLiveData<ApiRestaurant> = MutableLiveData()

    fun getRestaurant(id: Int) {
        viewModelScope.launch {
            val response = apiRepository.getRestaurant(id)
            myResponse.value = response
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