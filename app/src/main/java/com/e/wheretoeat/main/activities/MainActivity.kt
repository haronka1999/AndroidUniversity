package com.e.wheretoeat.main.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.e.wheretoeat.R
import com.e.wheretoeat.databinding.ActivityMainBinding
import com.e.wheretoeat.main.MainViewModel
import com.e.wheretoeat.main.MainViewModelFactory
import com.e.wheretoeat.main.api.Repository

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding =
            DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        val navController = findNavController(R.id.myNavHostFragment)
        val bottomNav = binding.bottomNavigationView
        bottomNav?.setupWithNavController(navController)

        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
        viewModel.getRestaurant()
    }
}