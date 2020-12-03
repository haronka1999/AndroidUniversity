package com.e.wheretoeat.main.data

import android.content.Context
import android.service.autofill.UserData
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class UserDataBase : RoomDatabase() {

    abstract fun userDao(): UserDao


    //making singleton
    companion object {
        @Volatile
        private var INSTANCE: UserDataBase? = null

        fun getDatabase(context: Context): UserDataBase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            //will be save by multiple threads
            synchronized(this) {}

            //create database
            val instance = Room.databaseBuilder(
                context.applicationContext,
                UserDataBase::class.java,
                "user_database"
            ).build()
            INSTANCE = instance
            return instance
        }
    }

}