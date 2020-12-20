package com.e.wheretoeat.main.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.e.wheretoeat.main.data.restaurant.Restaurant
import com.e.wheretoeat.main.data.restaurant.RestaurantDao
import com.e.wheretoeat.main.data.user.User
import com.e.wheretoeat.main.data.user.UserDao

/*
A Singleton class which define the ROOM database

Note: I didn'nt change the version number I only
changed the database name during development
 */

@Database(entities = [User::class, Restaurant::class], version = 1, exportSchema = false)
abstract class MyDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao
    abstract fun restaurantDao(): RestaurantDao

    companion object {
        @Volatile
        private var INSTANCE: MyDatabase? = null

        fun getDatabase(context: Context): MyDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MyDatabase::class.java,
                    "database2"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}