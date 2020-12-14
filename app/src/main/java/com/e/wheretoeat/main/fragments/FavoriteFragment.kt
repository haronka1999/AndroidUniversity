package com.e.wheretoeat.main.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.e.wheretoeat.R
import com.e.wheretoeat.databinding.FragmentFavoriteBinding
import com.e.wheretoeat.main.adapters.RestaurantAdapter
import com.e.wheretoeat.main.models.ApiRestaurant
import com.e.wheretoeat.main.viewmodels.MainViewModel

class FavoriteFragment : Fragment(), RestaurantAdapter.OnItemClickListener {

    lateinit var binding: FragmentFavoriteBinding
    private val mainViewModel: MainViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_favorite, container, false)


        val favoriteList = mainViewModel.favoriteRestaurants.value!!
        if (favoriteList.isEmpty()) {
            binding.emptyTextView.text = "You haven't added any favorite restaurant yet ! "
        }

        val recyclerView: RecyclerView = binding!!.favoriteRecyclerView
        recyclerView.adapter = RestaurantAdapter(favoriteList, this@FavoriteFragment)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.setHasFixedSize(true)
        return binding.root
    }

    override fun onItemClick(item: ApiRestaurant) {
        mainViewModel.currentApiRestaurant = item
        findNavController().navigate(R.id.action_favoriteFragment_to_detailFragment)
    }


}