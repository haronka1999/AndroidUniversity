package com.e.wheretoeat.main.api

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

//
class MainViewModelFactory(private val apiRepository: ApiRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(apiRepository) as T
    }
}
