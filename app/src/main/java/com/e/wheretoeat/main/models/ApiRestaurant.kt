package com.e.wheretoeat.main.models

import androidx.room.PrimaryKey

data class ApiRestaurant(
    @PrimaryKey
    val id: Int,
    val name: String,
    val address: String,
    val city: String,
    val area: String,
    val postal_code: Int,
    val country: String,
    val phone: String,
    val lat: String,
    val lng: String,
    val price: Double,
    val reserve_url: String,
    val mobile_reserve_url: String,
    val image_url: String,
) {


}