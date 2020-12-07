package com.e.wheretoeat.main.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.e.wheretoeat.main.api.ApiRepository
import com.e.wheretoeat.main.models.ApiRestaurant
import com.e.wheretoeat.main.models.ApiRestaurantResponse
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {

    //viewModel datas
    var apiRestaurantsByCountries: MutableLiveData<MutableList<ApiRestaurant>> = MutableLiveData()
    lateinit var currentApiRestaurant: ApiRestaurant
    private val apiRepository = ApiRepository()


    fun getAllRestaurants() {
        val result = apiRepository.getAllRestaurants()
        val tempListApiRestaurant: MutableList<ApiRestaurant> = mutableListOf()
        result.enqueue(object : Callback<ApiRestaurantResponse> {
            override fun onResponse(
                call: Call<ApiRestaurantResponse>,
                response: Response<ApiRestaurantResponse>
            ) {
                val restaurantSize = response.body()!!.total_entries
                for (i in 0 until restaurantSize) {
                    val apiRestaurant = response.body()!!.restaurants[i]

                    tempListApiRestaurant += apiRestaurant
                }
                apiRestaurantsByCountries.value = tempListApiRestaurant
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
}