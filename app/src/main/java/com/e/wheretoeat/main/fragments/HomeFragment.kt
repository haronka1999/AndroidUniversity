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
import com.e.wheretoeat.databinding.FragmentHomeBinding
import com.e.wheretoeat.main.adapters.RestaurantAdapter
import com.e.wheretoeat.main.data.User
import com.e.wheretoeat.main.data.UserViewModel
import com.e.wheretoeat.main.models.ApiRestaurant
import com.e.wheretoeat.main.viewmodels.MainViewModel

class HomeFragment : Fragment(), RestaurantAdapter.OnItemClickListener {
    private lateinit var binding: FragmentHomeBinding
    private val mainViewModel: MainViewModel by activityViewModels()
    private lateinit var mUserViewModel: UserViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fillUsers()
        //requireActivity().findViewById<View>(R.id.bottomNavigationView).visibility = View.VISIBLE
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)

        //val apiList = mainViewModel.apiRestaurantsByCountries.value!!
        val apiList = mutableListOf<ApiRestaurant>()
        val recyclerView: RecyclerView = binding!!.restaurantRecyclerView
        recyclerView.adapter = RestaurantAdapter(apiList, this@HomeFragment)
        recyclerView.layoutManager = LinearLayoutManager(activity)
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