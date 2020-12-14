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

    //viewModel data
    var users: MutableLiveData<MutableList<User>> = MutableLiveData()
    var favoriteRestaurants: MutableLiveData<MutableList<ApiRestaurant>> = MutableLiveData()
    var apiRestaurants: MutableLiveData<MutableList<ApiRestaurant>> = MutableLiveData()
    lateinit var currentApiRestaurant: ApiRestaurant

    //This variable will be used to define which data will be updated:

    //0 ---> username
    //1 ---> address
    //2 ---> phone
    //3 ---> email
    var dataBeEdited = -1

    /*
    The functions below will need for the database queries
     */
    private val apiRepository = ApiRepository()


    fun getAllRestaurantsFromDropBox() {
        val client = OkHttpClient()
        val request =
            Request.Builder().url("https://www.dropbox.com/s/2lfd0sh7puq41lx/restaurants.json?dl=1")
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
        var artist = ""
        var image = ""
        val restaurantList = ArrayList<ApiRestaurant>()
        val jsonArray = JSONArray(response)
        val restaurantSize = jsonArray.length()
        // Log.d("Helo", "jsonarray: ${jsonArray.length()}")

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

    fun getAllRestaurants() {
        val result = apiRepository.getAllRestaurants()
        val tempListApiRestaurant: MutableList<ApiRestaurant> = mutableListOf()
        result.enqueue(object : Callback<ApiRestaurantResponse> {
            override fun onResponse(
                call: Call<ApiRestaurantResponse>,
                response: Response<ApiRestaurantResponse>
            ) {
                try {
                    Log.d("Helo", "Onresponse - okay !")
                    val restaurantSize = response.body()!!.restaurants.size
                    Log.d("Helo", "size; $restaurantSize")
                    for (i in 0 until restaurantSize) {
                        val apiRestaurant = response.body()!!.restaurants[i]
                        tempListApiRestaurant += apiRestaurant
                    }
                    apiRestaurants.value = tempListApiRestaurant
                } catch (e: Exception) {
                    Log.d("Helo", "Onresponse catch: ${e.message}")
                }
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

    fun castToEntityRestaurant(rest: ApiRestaurant) = Restaurant(
        0,
        rest.name,
        rest.address,
        rest.city,
        rest.area,
        rest.postal_code,
        rest.country,
        rest.phone,
        rest.lat,
        rest.lng,
        rest.price,
        rest.reserve_url,
        rest.mobile_reserve_url,
        rest.image_url
    )
}



