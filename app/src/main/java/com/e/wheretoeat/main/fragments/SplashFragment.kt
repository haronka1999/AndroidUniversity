package com.e.wheretoeat.main.fragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.e.wheretoeat.R
import com.e.wheretoeat.databinding.FragmentSplashBinding
import com.e.wheretoeat.main.adapters.RestaurantAdapter
import com.e.wheretoeat.main.data.restaurant.RestaurantViewModel
import com.e.wheretoeat.main.models.ApiRestaurant
import com.e.wheretoeat.main.viewmodels.MainViewModel

class SplashFragment : Fragment() {

    private val mainViewModel: MainViewModel by activityViewModels()
    private lateinit var restViewModel: RestaurantViewModel
    private lateinit var sharedPref: SharedPreferences
    private lateinit var binding: FragmentSplashBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_splash, container, false)
        restViewModel = ViewModelProvider(this).get(RestaurantViewModel::class.java)
        mainViewModel.favoriteRestaurants.value = mutableListOf()

        fillAllRestaurants()

        return binding.root
    }

    private fun fillAllRestaurants() {
        //if the restaurant data is loaded go to home
        mainViewModel.apiRestaurants.observe(viewLifecycleOwner, {
            sharedPref =
                context?.getSharedPreferences("credentials", Context.MODE_PRIVATE)!!
            val credentials = sharedPref.all
            val restaurantsSize = mainViewModel.apiRestaurants.value!!.size
            mainViewModel.names.add(0,"City")
            for (i in 1 until restaurantsSize+1){
                mainViewModel.names.add(i, mainViewModel.apiRestaurants.value!![i-1].city)
            }
            //delete duplicates
            mainViewModel.names = mainViewModel.names.distinct().toMutableList()

            //this action was needed for solve the null pointer exception
            if (credentials.isEmpty()) {
                findNavController().navigate(R.id.action_splashFragment_to_registerFragment2)
            } else {
                findNavController().navigate(R.id.action_splashFragment_to_homeFragment)
            }
        })

        //get all of the restaurants
        // mainViewModel.getAllRestaurants()
        mainViewModel.getAllRestaurantsFromDropBox()
    }


}