package com.e.wheretoeat.main.fragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.e.wheretoeat.R
import com.e.wheretoeat.databinding.FragmentHomeBinding
import com.e.wheretoeat.main.adapters.RestaurantAdapter
import com.e.wheretoeat.main.viewmodels.RestaurantViewModel
import com.e.wheretoeat.main.data.user.User
import com.e.wheretoeat.main.viewmodels.UserViewModel
import com.e.wheretoeat.main.models.ApiRestaurant
import com.e.wheretoeat.main.viewmodels.MainViewModel


class HomeFragment : Fragment(), RestaurantAdapter.OnItemClickListener {

    //viewModels
    private val mainViewModel: MainViewModel by activityViewModels()
    private lateinit var restViewModel: RestaurantViewModel
    private lateinit var mUserViewModel: UserViewModel

    private lateinit var binding: FragmentHomeBinding
    private lateinit var sharedPref: SharedPreferences
    private lateinit var adapter: RestaurantAdapter

    //helper attributes
    private lateinit var restaurants: MutableList<ApiRestaurant>
    private lateinit var filteredRestaurants: MutableList<ApiRestaurant>
    private lateinit var cities: MutableList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requireActivity().findViewById<View>(R.id.bottomNavigationView).visibility = View.VISIBLE
        restViewModel = ViewModelProvider(this).get(RestaurantViewModel::class.java)
        sharedPref = context?.getSharedPreferences("credentials", Context.MODE_PRIVATE)!!
        restaurants = mainViewModel.apiRestaurants.value!!
        filteredRestaurants = mutableListOf()
        adapter = RestaurantAdapter(restaurants, this@HomeFragment)
        cities = mainViewModel.cities
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

    private fun setFilterSpinner() {
        val adp =
            ArrayAdapter(requireActivity(), android.R.layout.simple_spinner_dropdown_item, cities)
        binding.filterSpinner.adapter = adp
        binding.filterSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                /*
                When the screen is loaded automatically
                will be called for the first item
                which is empty so I need to handle this situation
                 */
                if (position == 0) {
                    return
                }

                val currentCity = cities[position]
                val tempList = mutableListOf<ApiRestaurant>()
                for (i in 0 until restaurants.size) {
                    if (restaurants[i].city == currentCity) {
                        tempList.add(restaurants[i])
                    }
                }
                filteredRestaurants = tempList
                adapter = RestaurantAdapter(filteredRestaurants, this@HomeFragment)
                binding.restaurantRecyclerView.adapter = adapter
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }
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

                //if the user didn't choose any filtering city
                //the user will be able to sort the whole data
                val tempList = if (filteredRestaurants.isEmpty()) {
                    restaurants
                } else {
                    filteredRestaurants
                }

                /*
                Here I decide if I need to sort by name or price
                1 --> name
                2 --> price
                 */
                when (position) {
                    1 -> {
                        val sortedList = tempList.sortedBy { it.name }
                        adapter = RestaurantAdapter(sortedList.toMutableList(), this@HomeFragment)
                        binding.restaurantRecyclerView.adapter = adapter
                    }
                    2 -> {
                        tempList.sortBy { it.price }
                        adapter = RestaurantAdapter(tempList, this@HomeFragment)
                        binding.restaurantRecyclerView.adapter = adapter
                    }
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
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