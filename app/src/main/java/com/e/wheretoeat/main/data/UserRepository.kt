package com.e.wheretoeat.main.data

import androidx.lifecycle.LiveData
import com.e.wheretoeat.main.models.User

/*
This class is not a necessary class
but it helps code separation
and makes a cleaner architecture
 */

class UserRepository(private val userDao: UserDao) {
    val readAllData: LiveData<List<User>> = userDao.readAllData()

    suspend fun addUser(user: User) {
        userDao.addUser(user)
    }

}