package com.e.wheretoeat.main.api

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.e.wheretoeat.main.models.ApiRestaurant
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class MainViewModel(private val apiRepository: ApiRepository) : ViewModel() {


    fun getAllRestaurants() {
        viewModelScope.launch {
            try {
                val result = apiRepository.getAllRestaurants()
                result.enqueue(object : Callback<List<ApiRestaurant>> {
                    override fun onFailure(call: Call<List<ApiRestaurant>>, t: Throwable) {
                        Log.d("Helo", "onfailure - restaurantList: ${t.message}")
                    }

                    override fun onResponse(
                        call: Call<List<ApiRestaurant>>,
                        response: Response<List<ApiRestaurant>>
                    ) {
                        Log.d("Helo", "onResponse - restaurantList: $response")
                    }


                })
            } catch (e: Exception) {
                Log.d("Helo", "Failure : $e")
            }

        }


    }


    fun getStats() {
        viewModelScope.launch {
            try {
                val result = apiRepository.getStats()
                result.enqueue(object : Callback<ApiRestaurant> {
                    override fun onFailure(call: Call<ApiRestaurant>, t: Throwable) {
                        Log.d("Helo", "onfailure - one restaurant: ${t.message}")
                    }

                    override fun onResponse(
                        call: Call<ApiRestaurant>,
                        response: Response<ApiRestaurant>
                    ) {
                        Log.d("Helo", "onResponse: ${response.body()!!.address}")
                    }

                })


            } catch (e: Exception) {
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