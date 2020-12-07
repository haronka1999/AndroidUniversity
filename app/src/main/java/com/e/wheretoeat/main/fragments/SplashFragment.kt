package com.e.wheretoeat.main.fragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.e.wheretoeat.R
import com.e.wheretoeat.main.viewmodels.MainViewModel

class SplashFragment : Fragment() {

    private val viewModel: MainViewModel by activityViewModels()
    private lateinit var sharedPref: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        //if the restaurant data is loaded go to home
        viewModel.restaurantsByCountries.observe(viewLifecycleOwner, Observer {
            sharedPref =
                context?.getSharedPreferences("credentials", Context.MODE_PRIVATE)!!
            val credentials = sharedPref.all
            if (credentials.isEmpty()) {
                findNavController().navigate(R.id.action_splashFragment_to_registerFragment2)
            } else {
                findNavController().navigate(R.id.action_splashFragment_to_homeFragment)
            }

        })

        //get all of the restaurants
        viewModel.getAllRestaurants()

        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

}