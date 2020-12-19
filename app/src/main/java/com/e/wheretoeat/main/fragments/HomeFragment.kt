package com.e.wheretoeat.main.fragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.e.wheretoeat.R
import com.e.wheretoeat.databinding.FragmentHomeBinding
import com.e.wheretoeat.main.adapters.RestaurantAdapter
import com.e.wheretoeat.main.data.restaurant.RestaurantViewModel
import com.e.wheretoeat.main.data.user.User
import com.e.wheretoeat.main.data.user.UserViewModel
import com.e.wheretoeat.main.models.ApiRestaurant
import com.e.wheretoeat.main.viewmodels.MainViewModel


class HomeFragment : Fragment(), RestaurantAdapter.OnItemClickListener {
    private lateinit var binding: FragmentHomeBinding
    private val mainViewModel: MainViewModel by activityViewModels()
    private lateinit var restViewModel: RestaurantViewModel
    private lateinit var mUserViewModel: UserViewModel
    private lateinit var sharedPref: SharedPreferences
    private lateinit var adapter: RestaurantAdapter
    private lateinit var apiList: MutableList<ApiRestaurant>
    private lateinit var names: MutableList<String>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireActivity().findViewById<View>(R.id.bottomNavigationView).visibility = View.VISIBLE
        restViewModel = ViewModelProvider(this).get(RestaurantViewModel::class.java)
        sharedPref = context?.getSharedPreferences("credentials", Context.MODE_PRIVATE)!!
        apiList = mainViewModel.apiRestaurants.value!!
        adapter = RestaurantAdapter(apiList, this@HomeFragment)
        names = mainViewModel.names
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        fillUsers()
        setRecycleView()
        setFilterSpinner()
        setSortSpinner()
        return binding.root
    }

    private fun setSortSpinner() {
        val sortByArray = listOf("Select", "Name", "Price")
        val adp =
            ArrayAdapter(
                requireActivity(),
                android.R.layout.simple_spinner_dropdown_item,
                sortByArray
            )
        binding.sortSpinner.adapter = adp
        binding.sortSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                Log.d("Helo", "onitemselected sort spinner")
                /*
                Here I decide if I need to sort by name or price
                1 --> name
                2 --> price
                 */
                when (position) {
                    1 -> {
                        Log.d("Helo", "name")
                        val sortedList = apiList.sortedBy { it.name }
                        adapter = RestaurantAdapter(sortedList.toMutableList(), this@HomeFragment)
                        binding.restaurantRecyclerView.adapter = adapter
                    }
                    2 -> {
                        Log.d("Helo", "price")
                        apiList.sortBy { it.price }
                        adapter = RestaurantAdapter(apiList, this@HomeFragment)
                        binding.restaurantRecyclerView.adapter = adapter
                    }
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }
    }

    private fun setFilterSpinner() {
        val adp =
            ArrayAdapter(requireActivity(), android.R.layout.simple_spinner_dropdown_item, names)
        binding.filterSpinner.adapter = adp
        binding.filterSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                Log.d("Helo", "onitemselecteed")
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                Log.d("Helo", "onitemselecteed")
            }

        }
    }

    private fun setRecycleView() {
        val recyclerView: RecyclerView = binding.restaurantRecyclerView
        recyclerView.adapter = adapter
        val layoutManager = LinearLayoutManager(activity)
        recyclerView.layoutManager = layoutManager
        recyclerView.setHasFixedSize(true)
    }


    override fun onItemClick(item: ApiRestaurant) {
        mainViewModel.currentApiRestaurant = item
        findNavController().navigate(R.id.action_homeFragment_to_detailFragment)
    }


    private fun fillUsers() {
        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        mUserViewModel.readAllData.observe(viewLifecycleOwner, { user ->
            val usersCount = user.size
            val tempList = mutableListOf<User>()
            for (i in 0 until usersCount) {
                tempList += user[i]
            }
            mainViewModel.users.value = tempList
        })
    }


}