package com.e.wheretoeat.main.models

import com.google.gson.annotations.SerializedName

class Restaurant(
    @SerializedName("id")  var restaurantID: Int,
     var name: String,
     var address: String,
     var price: Double,
     var image_url: String
) {




}