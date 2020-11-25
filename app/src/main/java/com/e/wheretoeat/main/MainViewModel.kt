package com.e.wheretoeat.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.e.wheretoeat.main.api.Repository
import com.e.wheretoeat.main.data.UserRepository
import com.e.wheretoeat.main.models.Restaurant
import kotlinx.coroutines.launch

class MainViewModel(private val repository: Repository) : ViewModel() {
    val myResponse: MutableLiveData<Restaurant> = MutableLiveData()

    fun getRestaurant() {
        viewModelScope.launch {
            val response = repository.getRestaurant()
            myResponse.value = response
        }
    }
}