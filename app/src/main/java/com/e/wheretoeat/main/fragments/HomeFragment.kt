package com.e.wheretoeat.main.fragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.e.wheretoeat.R
import com.e.wheretoeat.databinding.FragmentHomeBinding
import com.e.wheretoeat.main.adapters.RestaurantAdapter
import com.e.wheretoeat.main.data.restaurant.RestaurantViewModel
import com.e.wheretoeat.main.data.user.User
import com.e.wheretoeat.main.data.user.UserViewModel
import com.e.wheretoeat.main.models.ApiRestaurant
import com.e.wheretoeat.main.utils.PaginationListener
import com.e.wheretoeat.main.utils.PaginationListener.Companion.PAGE_START
import com.e.wheretoeat.main.viewmodels.MainViewModel
import com.google.android.gms.common.api.Api


class HomeFragment : Fragment(), RestaurantAdapter.OnItemClickListener {
    private lateinit var binding: FragmentHomeBinding
    private val mainViewModel: MainViewModel by activityViewModels()
    private lateinit var restViewModel: RestaurantViewModel
    private lateinit var mUserViewModel: UserViewModel
    private lateinit var sharedPref: SharedPreferences
    private lateinit var adapter: RestaurantAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fillUsers()
        requireActivity().findViewById<View>(R.id.bottomNavigationView).visibility = View.VISIBLE
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        restViewModel = ViewModelProvider(this).get(RestaurantViewModel::class.java)
        Log.d("Helo", "cities: ${mainViewModel.cities}")

        sharedPref = context?.getSharedPreferences("credentials", Context.MODE_PRIVATE)!!
        val apiList = mainViewModel.apiRestaurants.value!!
        val recyclerView: RecyclerView = binding!!.restaurantRecyclerView
        adapter = RestaurantAdapter(apiList, this@HomeFragment)
        recyclerView.adapter = adapter

        val layoutManager = LinearLayoutManager(activity)
        recyclerView.layoutManager = layoutManager
        recyclerView.setHasFixedSize(true)



        return binding.root
    }

    override fun onItemClick(item: ApiRestaurant) {
        mainViewModel.currentApiRestaurant = item
        findNavController().navigate(R.id.action_homeFragment_to_detailFragment)
    }

    private fun fillUsers() {
        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        mUserViewModel.readAllData.observe(viewLifecycleOwner, Observer { user ->
            val usersCount = user.size
            val tempList = mutableListOf<User>()
            for (i in 0 until usersCount) {
                tempList += user[i]
            }
            mainViewModel.users.value = tempList
        })
    }


}