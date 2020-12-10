package com.e.wheretoeat.main.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


//@Entity()
data class ApiRestaurantResponse(
    @SerializedName("total_entries")
    val total_entries: Int,
    @SerializedName("page")
    val page: Int,
    @SerializedName("per_page")
    val current_page: Int,
    @SerializedName("restaurants")
    val restaurants : List<ApiRestaurant>
)
{
}