package com.e.wheretoeat.main.data

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.e.wheretoeat.main.viewmodels.MainViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(application: Application) : AndroidViewModel(application) {
    val readAllData: LiveData<List<User>>
    val currentUserId: Int = -1
    private val repository: UserRepository

    init {
        val userDao = UserDatabase.getDatabase(application).userDao()
        repository = UserRepository(userDao)
        readAllData = repository.readAllData
    }


    fun getCurrentUserId(currentUserName: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val id  = repository.getCurrentUserId(currentUserName)
            Log.d("Helo", "userid: $id")
        }
    }


    fun addUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            val id = repository.addUser(user)
            Log.d("Helo", "userid mainviewmodel: $id")
        }
    }

    fun updateUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateUser(user)
        }
    }
}