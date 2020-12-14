package com.e.wheretoeat.main.fragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.e.wheretoeat.R
import com.e.wheretoeat.databinding.FragmentSplashBinding
import com.e.wheretoeat.main.viewmodels.MainViewModel

class SplashFragment : Fragment() {

    private val mainViewModel: MainViewModel by activityViewModels()
    private lateinit var sharedPref: SharedPreferences
    private lateinit var binding: FragmentSplashBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_splash, container, false)
        //requireActivity().findViewById<View>(R.id.bottomNavigationView).visibility = View.GONE
        mainViewModel.favoriteRestaurants.value = mutableListOf()


        //if the restaurant data is loaded go to home
        mainViewModel.apiRestaurants.observe(viewLifecycleOwner, {
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
       // mainViewModel.getAllRestaurants()
        mainViewModel.getAllRestaurantsFromDropBox()
        return binding.root
    }
}