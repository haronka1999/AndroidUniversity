package com.e.wheretoeat.main.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.e.wheretoeat.R
import com.e.wheretoeat.databinding.ActivityMainBinding

/*
This activity contains all of the fragment in the app.
It is attached a bottom navigation and a NavHostFragment
which is connected with the nav_graph.xml
 */

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding =
            DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        val navController = findNavController(R.id.myNavHostFragment)
        val bottomNav = binding.bottomNavigationView
        bottomNav.setupWithNavController(navController)

        //disable manually the bottomnavigation in splashfragment
        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id == R.id.splashFragment) {
                bottomNav.visibility = View.GONE
            }
        }
    }
}