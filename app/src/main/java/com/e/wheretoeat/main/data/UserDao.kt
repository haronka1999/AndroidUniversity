package com.e.wheretoeat.main.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun adduser(user: User)

    @Query("SELECT * FROM user_table ORDER BY userID ASC")
    fun readAllData(): LiveData<List<User>>

}