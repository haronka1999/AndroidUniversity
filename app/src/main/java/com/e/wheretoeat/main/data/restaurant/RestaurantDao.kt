package com.e.wheretoeat.main.data.restaurant

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.e.wheretoeat.main.data.user.User


@Dao
interface RestaurantDao {

    // this function only add the favorite restaurant
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addRestaurant(restaurant: Restaurant)

    @Query("SELECT * FROM restaurant_table ORDER BY id ASC")
    fun readAllData(): LiveData<List<Restaurant>>
}