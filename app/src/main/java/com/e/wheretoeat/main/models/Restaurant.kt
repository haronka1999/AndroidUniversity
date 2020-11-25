package com.e.wheretoeat.main.models

import com.google.gson.annotations.SerializedName

class Restaurant(
    @SerializedName("id") private var restaurantID: Int,
    private var name: String,
    private var address: String,
    private var price: Double,
    private var image_url: String
) {




}