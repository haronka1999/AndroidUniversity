package com.e.wheretoeat.main.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.e.wheretoeat.R
import com.e.wheretoeat.databinding.ActivityMainBinding
import com.e.wheretoeat.main.MainViewModel
import com.e.wheretoeat.main.MainViewModelFactory
import com.e.wheretoeat.main.api.Repository

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding =
            DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        val navController = findNavController(R.id.myNavHostFragment)
        val bottomNav = binding.bottomNavigationView
        bottomNav?.setupWithNavController(navController)
    }
}