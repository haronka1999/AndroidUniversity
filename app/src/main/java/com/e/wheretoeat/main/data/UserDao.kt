package com.e.wheretoeat.main.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUser(user: User): Long

    @Update
    suspend fun updateUser(user: User)


    @Query("SELECT * FROM user_table ORDER BY id ASC")
    fun readAllData(): LiveData<List<User>>

    @Query("SELECT id FROM user_table WHERE userName = :currentUserName")
    fun getCurrentUserId(currentUserName: String): LiveData<Int>


}