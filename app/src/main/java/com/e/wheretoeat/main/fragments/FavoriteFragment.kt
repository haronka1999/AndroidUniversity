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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.e.wheretoeat.R
import com.e.wheretoeat.databinding.FragmentFavoriteBinding
import com.e.wheretoeat.main.adapters.RestaurantAdapter
import com.e.wheretoeat.main.models.ApiRestaurant
import com.e.wheretoeat.main.viewmodels.MainViewModel
import com.e.wheretoeat.main.viewmodels.RestaurantViewModel

/*
Contains a simple recycler view with the favorited data
 */

class FavoriteFragment : Fragment(), RestaurantAdapter.OnItemClickListener {

    //viewmodels
    private val mainViewModel: MainViewModel by activityViewModels()
    private lateinit var restViewModel: RestaurantViewModel

    lateinit var binding: FragmentFavoriteBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_favorite, container, false)
        restViewModel = ViewModelProvider(this).get(RestaurantViewModel::class.java)

        setFavoriteRecyclerView(binding)

        val favoriteList = mainViewModel.favoriteRestaurants.value!!
        if (favoriteList.isEmpty()) {
            val emptyText = "You haven't added any favorite restaurant yet ! "
            binding.emptyTextView.text = emptyText
        }

        return binding.root
    }

    private fun setFavoriteRecyclerView(binding: FragmentFavoriteBinding?) {
        val apiList = mainViewModel.favoriteRestaurants.value
        val recyclerView: RecyclerView = binding!!.favoriteRecyclerView
        recyclerView.adapter = RestaurantAdapter(apiList!!, this@FavoriteFragment)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.setHasFixedSize(true)
    }

    override fun onItemClick(item: ApiRestaurant) {
        mainViewModel.currentApiRestaurant = item
        findNavController().navigate(R.id.action_favoriteFragment_to_detailFragment)
    }
}