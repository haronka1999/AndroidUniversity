package com.e.wheretoeat.main.models

import androidx.room.PrimaryKey

data class ApiRestaurant(
    @PrimaryKey
    val id: Int,
    val price: Double,
    val name: String,
    val address: String,
    val state: String,
    val city: String,
    val zip: Int,
    val country: String,
    val page: Int,
    val per_page: Int
) {


}