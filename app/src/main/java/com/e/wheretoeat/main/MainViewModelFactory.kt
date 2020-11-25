package com.e.wheretoeat.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.e.wheretoeat.main.api.Repository

//
class MainViewModelFactory(private val repository: Repository) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(repository) as T
    }
}
