package com.e.wheretoeat.main.api

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.e.wheretoeat.main.models.ApiRestaurant
import com.e.wheretoeat.main.models.ApiRestaurantResponse
import com.e.wheretoeat.main.models.Restaurant
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {

    private val apiRepository = ApiRepository()


    var restaurantsByCountries: MutableLiveData<MutableList<Restaurant>> = MutableLiveData()

    fun getAllRestaurants() {
        val result = apiRepository.getAllRestaurants()
        val tempList : MutableList<Restaurant> =  mutableListOf()
        result.enqueue(object : Callback<ApiRestaurantResponse> {
            override fun onResponse(
                call: Call<ApiRestaurantResponse>,
                response: Response<ApiRestaurantResponse>
            ) {
                val restaurantSize = response.body()!!.total_entries
                for (i in 0 until restaurantSize) {
                    val restaurant = convertTORestaurant(response.body()!!.restaurants[i])
                    tempList+=restaurant
                }
                restaurantsByCountries.value = tempList
            }

            override fun onFailure(call: Call<ApiRestaurantResponse>, t: Throwable) {
                Log.d("Helo", "onfailure - all restaurant: ${t.message}")
            }
        })

    }


    fun getStats() {
        viewModelScope.launch {
            val result = apiRepository.getStats()
            result.enqueue(object : Callback<ApiRestaurant> {
                override fun onFailure(call: Call<ApiRestaurant>, t: Throwable) {
                    Log.d("Helo", "onfailure - one restaurant: ${t.message}")
                }

                override fun onResponse(
                    call: Call<ApiRestaurant>,
                    response: Response<ApiRestaurant>
                ) {
                    Log.d("Helo", "onResponse: ${response.body()}")
                }
            })
        }
    }

    //this will need to display data to the adapter
    fun convertTORestaurant(apiRestaurant: ApiRestaurant) = Restaurant(
        0,
        apiRestaurant.name,
        apiRestaurant.address,
        apiRestaurant.price,
        apiRestaurant.country
    )


}