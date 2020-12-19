package com.e.wheretoeat.main.data.restaurant

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.e.wheretoeat.R
import com.e.wheretoeat.main.data.MyDatabase
import com.e.wheretoeat.main.models.ApiRestaurant
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader

class RestaurantViewModel(application: Application) : AndroidViewModel(application) {

    val readAllData: LiveData<List<Restaurant>>
    private val repository: RestaurantRepository

    init {
        val restaurantDao = MyDatabase.getDatabase(application).restaurantDao()
        repository = RestaurantRepository(restaurantDao)
        readAllData = repository.readAllData
    }


    fun addRestaurant(restaurant: Restaurant) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addRestaurant(restaurant)
        }
    }

    /*
    The functions below will need for the database queries
     */


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