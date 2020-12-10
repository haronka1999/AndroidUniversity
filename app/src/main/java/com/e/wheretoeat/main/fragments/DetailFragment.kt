package com.e.wheretoeat.main.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import com.e.wheretoeat.R
import com.e.wheretoeat.databinding.FragmentDetailBinding
import com.e.wheretoeat.main.viewmodels.MainViewModel


class DetailFragment : Fragment() {

    private val mainViewModel: MainViewModel by activityViewModels()
    private lateinit var binding: FragmentDetailBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        requireActivity().findViewById<View>(R.id.bottomNavigationView).visibility = View.VISIBLE
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail, container, false)


        //set the attributes
        binding.restNameTextView.text = mainViewModel.currentApiRestaurant.name
        binding.imageView.setImageResource(R.drawable.food)
        val fullAddress =
            "Address: ${mainViewModel.currentApiRestaurant.country}," +
                    " ${mainViewModel.currentApiRestaurant.city},\n" +
                    mainViewModel.currentApiRestaurant.address
        val priceTag = "Price: ${mainViewModel.currentApiRestaurant.price}"
        binding.addressTextView.text = fullAddress
        binding.priceTextView.text = priceTag

        Log.d("Helo", "Curentrest: ${mainViewModel.currentApiRestaurant}")
        binding.imageButton.setOnClickListener {
            binding.imageButton.setImageResource(R.drawable.ic_love_red)
         //   mainViewModel.favoriteRestaurants.value!!.add(mainViewModel.currentApiRestaurant)
        }


        return binding.root
    }


}