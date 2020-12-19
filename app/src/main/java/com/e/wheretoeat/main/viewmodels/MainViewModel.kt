package com.e.wheretoeat.main.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.e.wheretoeat.main.api.ApiRepository
import com.e.wheretoeat.main.data.restaurant.Restaurant
import com.e.wheretoeat.main.data.user.User
import com.e.wheretoeat.main.models.ApiRestaurant
import com.e.wheretoeat.main.models.ApiRestaurantResponse
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONArray
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

class MainViewModel : ViewModel() {

    var apiRestaurants: MutableLiveData<MutableList<ApiRestaurant>> = MutableLiveData()

    //viewModel data:
    var users: MutableLiveData<MutableList<User>> = MutableLiveData()
    var favoriteRestaurants: MutableLiveData<MutableList<ApiRestaurant>> = MutableLiveData()
    var restaurants: MutableList<ApiRestaurant> = mutableListOf()
    lateinit var currentApiRestaurant: ApiRestaurant
    var names: MutableList<String> = mutableListOf()

    //This variable will be used to define which data will be updatedat the profile fragment
    //0 ---> username
    //1 ---> address
    //2 ---> phone
    //3 ---> email
    var dataBeEdited = -1


    fun getAllRestaurantsFromDropBox() {
        val client = OkHttpClient()
        val request =
            Request.Builder().url("https://www.dropbox.com/s/94t6su4cimnrdnt/restaurants.json?dl=1")
                .build()
        client.newCall(request).enqueue(object : Callback<List<ApiRestaurant>>,
            okhttp3.Callback {
            override fun onResponse(
                call: Call<List<ApiRestaurant>>,
                response: Response<List<ApiRestaurant>>
            ) {
                val myData = response.body()
                Log.d("Helo", "onresponse 1  : $myData")
            }

            override fun onFailure(call: Call<List<ApiRestaurant>>, t: Throwable) {
                Log.d("Helo", "onfailure 1 - ${t.message}")
            }

            override fun onFailure(call: okhttp3.Call, e: IOException) {
                Log.d("Helo", "onfailure 2 - ${e.message}")
            }

            override fun onResponse(call: okhttp3.Call, response: okhttp3.Response) {
                // val myData = response.body()!!.string()
                parseResponse(response.body()!!.string())
                //   Log.d("Helo", "onresponse 2 : $myData")
            }

        })

    }

    fun parseResponse(response: String) {
        val restaurantList = ArrayList<ApiRestaurant>()
        val jsonArray = JSONArray(response)
        val restaurantSize = jsonArray.length()

        (0 until restaurantSize).forEach { index ->
            val jsonObject = jsonArray.getJSONObject(index)
            val apiRestaurant = ApiRestaurant(
                jsonObject.getString("id").toInt(),
                jsonObject.getString("name"),
                jsonObject.getString("address"),
                jsonObject.getString("city"),
                jsonObject.getString("area"),
                jsonObject.getString("postal_code"),
                jsonObject.getString("country"),
                jsonObject.getString("phone"),
                jsonObject.getString("lat"),
                jsonObject.getString("lng"),
                jsonObject.getString("price").toDouble(),
                jsonObject.getString("reserve_url"),
                jsonObject.getString("mobile_reserve_url"),
                jsonObject.getString("image_url")
            )
            restaurantList.add(apiRestaurant)
        }
        apiRestaurants.postValue(restaurantList)
    }
}



