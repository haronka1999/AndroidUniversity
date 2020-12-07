package com.e.wheretoeat.main.fragments

import android.os.Bundle
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

    private val viewModel: MainViewModel by activityViewModels()
    private lateinit var binding : FragmentDetailBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        requireActivity().findViewById<View>(R.id.bottomNavigationView).visibility = View.VISIBLE
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_detail,container,false)

        binding.restNameTextView.text = viewModel.currentApiRestaurant.name
        binding.imageView.setImageResource(R.drawable.food)
        binding.addressTextView.text = "Address: " +  viewModel.currentApiRestaurant.address
        binding.priceTextView.text =  "Price: " + viewModel.currentApiRestaurant.price.toString()

        return binding.root
    }


}