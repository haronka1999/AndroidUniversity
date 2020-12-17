package com.e.wheretoeat.main.fragments

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
import com.e.wheretoeat.databinding.FragmentFavoriteBinding
import com.e.wheretoeat.main.adapters.RestaurantAdapter
import com.e.wheretoeat.main.data.restaurant.RestaurantViewModel
import com.e.wheretoeat.main.data.user.UserViewModel
import com.e.wheretoeat.main.models.ApiRestaurant
import com.e.wheretoeat.main.viewmodels.MainViewModel

class FavoriteFragment : Fragment(), RestaurantAdapter.OnItemClickListener {

    lateinit var binding: FragmentFavoriteBinding
    private val mainViewModel: MainViewModel by activityViewModels()
    private lateinit var restViewModel: RestaurantViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_favorite, container, false)
        restViewModel = ViewModelProvider(this).get(RestaurantViewModel::class.java)

        setFavoriteRecyclerView(binding)

        val favoriteList = mainViewModel.favoriteRestaurants.value!!
        if (favoriteList.isEmpty()) {
            binding.emptyTextView.text = "You haven't added any favorite restaurant yet ! "
        }

        return binding.root
    }

    private fun setFavoriteRecyclerView(binding: FragmentFavoriteBinding?) {
        val apiList = mainViewModel.favoriteRestaurants.value
        val recyclerView: RecyclerView = binding!!.favoriteRecyclerView
        recyclerView.adapter = RestaurantAdapter(apiList!!, this@FavoriteFragment)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.setHasFixedSize(true)
        Log.d("Helo", "read all data ${restViewModel.readAllData.value}")
    }

    override fun onItemClick(item: ApiRestaurant) {
        mainViewModel.currentApiRestaurant = item
        findNavController().navigate(R.id.action_favoriteFragment_to_detailFragment)
    }


}