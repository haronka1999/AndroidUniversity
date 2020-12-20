package com.e.wheretoeat.main.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.e.wheretoeat.main.data.MyDatabase
import com.e.wheretoeat.main.data.restaurant.Restaurant
import com.e.wheretoeat.main.data.restaurant.RestaurantRepository
import com.e.wheretoeat.main.models.ApiRestaurant
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

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