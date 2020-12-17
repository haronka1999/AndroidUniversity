package com.e.wheretoeat.main.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.e.wheretoeat.R
import com.e.wheretoeat.databinding.FragmentDetailBinding
import com.e.wheretoeat.main.data.restaurant.Restaurant
import com.e.wheretoeat.main.data.restaurant.RestaurantViewModel
import com.e.wheretoeat.main.viewmodels.MainViewModel


class DetailFragment : Fragment() {
    private lateinit var mRestViewModel: RestaurantViewModel
    private val mainViewModel: MainViewModel by activityViewModels()
    private lateinit var binding: FragmentDetailBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        requireActivity().findViewById<View>(R.id.bottomNavigationView).visibility = View.VISIBLE
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail, container, false)

        mRestViewModel = ViewModelProvider(this).get(RestaurantViewModel::class.java)

        //check if the current article is in the favorites
        Log.d("Helo", "mainViewModel.favoriteRestaurants.value - detail ${mainViewModel.favoriteRestaurants.value}" )
        Log.d("Helo", "currentRest - detail ${mainViewModel.currentApiRestaurant}" )
        if (mainViewModel.favoriteRestaurants.value!!.contains(mainViewModel.currentApiRestaurant)) {
            binding.addToFav.setImageResource(R.drawable.ic_love_red)
        }

        //set the attributes
        val fullAddress =
            "Address: ${mainViewModel.currentApiRestaurant.country}," +
                    " ${mainViewModel.currentApiRestaurant.city},\n" +
                    mainViewModel.currentApiRestaurant.address
        val priceTag = "Price: ${mainViewModel.currentApiRestaurant.price}"

        binding.restNameTextView.text = mainViewModel.currentApiRestaurant.name
        binding.imageView.setImageResource(R.drawable.food)
        binding.addressTextView.text = fullAddress
        binding.priceTextView.text = priceTag


        binding.addToFav.setOnClickListener {
            Log.d("Helo", "need to be red")
            binding.addToFav.setImageResource(R.drawable.ic_love_red)
            mainViewModel.favoriteRestaurants.value!!.add(mainViewModel.currentApiRestaurant)
            //add an entity to the restaurant table
            mRestViewModel.addRestaurant(mRestViewModel.castToEntityRestaurant(mainViewModel.currentApiRestaurant))
        }

        binding.showOnMap.setOnClickListener {
            findNavController().navigate(R.id.action_detailFragment_to_mapsFragment)
        }

        return binding.root
    }


}