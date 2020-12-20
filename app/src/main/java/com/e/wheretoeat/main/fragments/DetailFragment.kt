package com.e.wheretoeat.main.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.e.wheretoeat.R
import com.e.wheretoeat.databinding.FragmentDetailBinding
import com.e.wheretoeat.main.models.ApiRestaurant
import com.e.wheretoeat.main.viewmodels.RestaurantViewModel
import com.e.wheretoeat.main.viewmodels.MainViewModel


class DetailFragment : Fragment() {

    //viewmodels
    private val mainViewModel: MainViewModel by activityViewModels()
    private lateinit var mRestViewModel: RestaurantViewModel

    private lateinit var binding: FragmentDetailBinding
    private lateinit var currentRestaurant: ApiRestaurant
    private lateinit var favoriteRestaurants: MutableList<ApiRestaurant>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mRestViewModel = ViewModelProvider(this).get(RestaurantViewModel::class.java)
        currentRestaurant = mainViewModel.currentApiRestaurant
        favoriteRestaurants = mainViewModel.favoriteRestaurants.value!!
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        requireActivity().findViewById<View>(R.id.bottomNavigationView).visibility = View.VISIBLE
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail, container, false)

        bindItems()

        //check if the current article is in the favorites
        if (favoriteRestaurants.contains(currentRestaurant)) {
            binding.addToFav.setImageResource(R.drawable.ic_love_red)
        }

        /*
        Here I decide if the current restaurant is in the favorite list.
        If it's not add to the database and the viewmodel,
        if there is we can unfavorite it and remove from the viewmodel
         */
        binding.addToFav.setOnClickListener {
            if (favoriteRestaurants.contains(currentRestaurant)) {
                binding.addToFav.setImageResource(R.drawable.ic_love_normal)
                mainViewModel.favoriteRestaurants.value!!.remove(currentRestaurant)
            } else {
                binding.addToFav.setImageResource(R.drawable.ic_love_red)
                mainViewModel.favoriteRestaurants.value!!.add(currentRestaurant)

                //add an entity to the restaurant table in ROOM database
                mRestViewModel.addRestaurant(mRestViewModel.castToEntityRestaurant(currentRestaurant))
            }
        }

        //go to the map fragment
        binding.showOnMap.setOnClickListener {
            findNavController().navigate(R.id.action_detailFragment_to_mapsFragment)
        }

        return binding.root
    }

    // fill the layout with th current restaurant's details
    private fun bindItems() {
        val fullAddress =
            "Address: ${currentRestaurant.country}," + " ${currentRestaurant.city},\n" + currentRestaurant.address
        val priceTag = "Price: ${currentRestaurant.price}"

        binding.restNameTextView.text = currentRestaurant.name
        binding.imageView.setImageResource(R.drawable.food)
        binding.addressTextView.text = fullAddress
        binding.priceTextView.text = priceTag
    }
}