package com.e.wheretoeat.main.fragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.util.Log
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
    private lateinit var restaurants: MutableList<ApiRestaurant>
    private lateinit var cities: MutableList<String>

    private var isLastPage: Boolean = false
    private var isLoading: Boolean = false
    var handler: Handler = Handler()

    companion object {
        const val PAGE_START = 1
        private var CURRENT_PAGE = PAGE_START
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requireActivity().findViewById<View>(R.id.bottomNavigationView).visibility = View.VISIBLE
        restViewModel = ViewModelProvider(this).get(RestaurantViewModel::class.java)
        sharedPref = context?.getSharedPreferences("credentials", Context.MODE_PRIVATE)!!
        restaurants = mainViewModel.apiRestaurants.value!!
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
                        val sortedList = restaurants.sortedBy { it.name }
                        adapter = RestaurantAdapter(sortedList.toMutableList(), this@HomeFragment)
                        binding.restaurantRecyclerView.adapter = adapter
                    }
                    2 -> {
                        Log.d("Helo", "price")
                        restaurants.sortBy { it.price }
                        adapter = RestaurantAdapter(restaurants, this@HomeFragment)
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
            ArrayAdapter(requireActivity(), android.R.layout.simple_spinner_dropdown_item, cities)
        binding.filterSpinner.adapter = adp
        binding.filterSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                Log.d("Helo", "cities")

                /*
                When the screen is loaded automatically
                will be called for the first item
                which is empty so I need to handle this situation
                 */
                if (position == 0) {
                    return
                }

                val currentCity = cities[position]
                val filteredRestaurants = mutableListOf<ApiRestaurant>()
                for (i in 0 until restaurants.size) {
                    if (restaurants[i].city == currentCity) {
                        filteredRestaurants.add(restaurants[i])
                    }
                }

                adapter = RestaurantAdapter(filteredRestaurants, this@HomeFragment)
                binding.restaurantRecyclerView.adapter = adapter
                Log.d("Helo", "current pos: $position")
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

        }
    }
    //todo
    private fun loadMore() {
    
    }

    private fun setRecycleView() {

        val recyclerView: RecyclerView = binding.restaurantRecyclerView
        recyclerView.adapter = adapter
        val layoutManager = LinearLayoutManager(activity)
        recyclerView.layoutManager = layoutManager
        recyclerView.setHasFixedSize(true)

        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                Log.d("Helo", "onscrolled: $recyclerView")
                Log.d("Helo", "dx: $dx")
                Log.d("Helo", "dy: $dy")
                if (!isLoading) {
                    if (layoutManager.findLastCompletelyVisibleItemPosition() == restaurants.size - 1) {
                        Log.d("Helo", "in the if onscrolled")
                        loadMore()
                        isLoading = true
                    }
                }
            }
        })
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