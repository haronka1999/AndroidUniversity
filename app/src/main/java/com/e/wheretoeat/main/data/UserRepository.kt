package com.e.wheretoeat.main.data

import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LiveData
import com.e.wheretoeat.main.viewmodels.MainViewModel

class UserRepository(private val userDao: UserDao) {

    val readAllData: LiveData<List<User>> = userDao.readAllData()

    suspend fun addUser(user: User) : Long{
        userDao.addUser(user)
        return user.id.toLong()
    }

    suspend fun updateUser(user : User){
        userDao.updateUser(user)
    }


}