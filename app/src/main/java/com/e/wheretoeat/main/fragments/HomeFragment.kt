package com.e.wheretoeat.main.fragments

import android.os.Bundle
import android.util.Log
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
import com.e.wheretoeat.databinding.FragmentHomeBinding
import com.e.wheretoeat.main.adapters.RestaurantAdapter
import com.e.wheretoeat.main.viewmodels.MainViewModel
//import com.e.wheretoeat.main.api.MainViewModelFactory
import com.e.wheretoeat.main.models.Restaurant


class HomeFragment : Fragment(), RestaurantAdapter.OnItemClickListener {
    private lateinit var binding: FragmentHomeBinding
    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        requireActivity().findViewById<View>(R.id.bottomNavigationView).visibility = View.VISIBLE
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)

        val list = viewModel.restaurantsByCountries.value!!
        Log.d("Helo", "list : $list ")
        val recyclerView: RecyclerView = binding!!.restaurantRecyclerView
        recyclerView.adapter = RestaurantAdapter(list, this@HomeFragment)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.setHasFixedSize(true)

//        viewModel.restaurantsByCountries.observe(viewLifecycleOwner, Observer {
//        })

        //   getRestaurantsFromApi()

        return binding.root
    }


    private fun getRestaurantsFromApi() {
        viewModel.getAllRestaurants()
    }

    override fun onItemClick(item: Restaurant) {
        viewModel.currentRestaurant = item
        findNavController().navigate(R.id.action_homeFragment_to_detailFragment)
    }
}