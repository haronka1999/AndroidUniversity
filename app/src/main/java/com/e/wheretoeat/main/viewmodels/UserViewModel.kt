package com.e.wheretoeat.main.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.e.wheretoeat.main.models.User
import com.e.wheretoeat.main.data.UserDataBase
import com.e.wheretoeat.main.data.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


//provide data to the UI
//dispatcher between repo and UI
class UserViewModel(application: Application) : ViewModel() {


    private val readAllData: LiveData<List<User>>
    private val repository: UserRepository

    init {
        val userDao = UserDataBase.getDatabase(
            application
        ).userDao()
        repository = UserRepository(userDao)
        readAllData = repository.readAllData
    }


    fun addUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addUser(user)
        }
    }
}