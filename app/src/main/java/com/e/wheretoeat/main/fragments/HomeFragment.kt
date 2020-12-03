package com.e.wheretoeat.main.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.e.wheretoeat.R
import com.e.wheretoeat.databinding.FragmentHomeBinding
import com.e.wheretoeat.main.api.MainViewModel
import com.e.wheretoeat.main.api.MainViewModelFactory
import com.e.wheretoeat.main.adapters.DataAdapter
import com.e.wheretoeat.main.api.ApiRepository
import com.e.wheretoeat.main.models.Restaurant


class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        initializeRecyclerView(binding)


       // readDataFromOpenTable()
        return binding.root
    }

    private fun initializeRecyclerView(binding: FragmentHomeBinding?) {
        val list = generateDummyList(20)
        val recyclerView: RecyclerView = binding!!.restaurantRecyclerView
        recyclerView.adapter = DataAdapter(list)
        recyclerView.layoutManager = LinearLayoutManager(activity )
        recyclerView.setHasFixedSize(true)
    }

    private fun readDataFromOpenTable(){
        val repository = ApiRepository()
        val viewModelFactory =
            MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
        viewModel.getRestaurant(107257)
        viewModel.myResponse.observe(viewLifecycleOwner, Observer { response ->
            Log.d("Helo",response.toString());
            Log.d("Helo",response.toString());
            Log.d("Helo",response.toString());
        })

    }


    private fun generateDummyList(size: Int): List<Restaurant> {

        val list = ArrayList<Restaurant>()
        for (i in 0 until size) {
            val item = Restaurant(i, "Name $i ","Address $i", i.toDouble(),"image url comes here ...")
            list += item
        }
        return list
    }

}