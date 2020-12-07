package com.e.wheretoeat.main.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.e.wheretoeat.R
import com.e.wheretoeat.main.adapters.RestaurantAdapter
import com.e.wheretoeat.main.api.MainViewModel

class SplashFragment : Fragment() {

    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        //if the restaurant data is loaded go to home
        viewModel.restaurantsByCountries.observe(viewLifecycleOwner, Observer {
            findNavController().navigate(R.id.action_splashFragment_to_homeFragment)
        })

        //get all of the restaurants
        viewModel.getAllRestaurants()

        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

}