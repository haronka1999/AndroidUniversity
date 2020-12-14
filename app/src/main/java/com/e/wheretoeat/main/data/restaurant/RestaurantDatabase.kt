package com.e.wheretoeat.main.data.restaurant

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.e.wheretoeat.R

import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader

@Database(entities = [Restaurant::class], version = 1, exportSchema = false)
abstract class RestaurantDatabase : RoomDatabase() {

    abstract fun restaurantDao(): RestaurantDao

    companion object {
         lateinit var activity : Context

        @Volatile
        private var INSTANCE: RestaurantDatabase? = null
        fun getDatabase(context: Context): RestaurantDatabase {
            activity = context.applicationContext

            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    RestaurantDatabase::class.java,
                    "restaurant_database"
                ).build()
                INSTANCE = instance
                return instance

            }
        }
    }

//    private suspend fun fillWithData(context: Context) {
//        val dao: RestaurantDao = getDatabase(context).restaurantDao()
//        val restaurants = loadJSONArray(context)
//
//        try {
//
//            for (i in 0 until restaurants!!.length()) {
//                val restaurantJSON = restaurants.getJSONObject(i)
//                val restaurant = Restaurant(
//                    0,
//                    restaurantJSON.getString("name"),
//                    restaurantJSON.getString("address"),
//                    restaurantJSON.getString("city"),
//                    restaurantJSON.getString("area"),
//                    restaurantJSON.getString("postal_code").toInt(),
//                    restaurantJSON.getString("country"),
//                    restaurantJSON.getString("phone"),
//                    restaurantJSON.getString("lat"),
//                    restaurantJSON.getString("lng"),
//                    restaurantJSON.getString("price").toDouble(),
//                    restaurantJSON.getString("reserve_url"),
//                    restaurantJSON.getString("mobile_reserve_url"),
//                    restaurantJSON.getString("image_url")
//                )
//                dao.addRestaurant(restaurant)
//
//            }
//
//        } catch (e: JSONException) {
//            Log.d("Helo", "fillwithdata - exception: ${e.message}")
//        }
//
//
//    }
//
//
//    private fun loadJSONArray(context: Context): JSONArray? {
//        val builder = StringBuilder()
//        val input = context.resources.openRawResource(R.raw.restaurants)
//        val newInput = InputStreamReader(input)
//        val reader = BufferedReader(newInput)
//
//        var line = ""
//
//        try {
//
//            while (true) {
//                line = reader.readLine()
//                if (line == null) {
//                    break
//                }
//                builder.append(line)
//            }
//
//            val json = JSONObject(builder.toString())
//
//            return json.getJSONArray("restaurants")
//        } catch (e: Exception) {
//            Log.d("Helo", "catch - inputread : ${e.message}")
//        }
//
//        return null
//    }

}