package com.e.wheretoeat.main.data.user

import androidx.lifecycle.LiveData
import androidx.room.*
import com.e.wheretoeat.main.data.user.User

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUser(user: User): Long

    @Update
    suspend fun updateUser(user: User)


    @Query("SELECT * FROM user_table ORDER BY id ASC")
    fun readAllData(): LiveData<List<User>>

    @Query("SELECT id FROM user_table WHERE userName = :currentUserName")
    suspend fun getCurrentUserId(currentUserName: String): Int


}