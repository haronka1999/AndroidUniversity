package com.e.wheretoeat.main.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.e.wheretoeat.R
import com.e.wheretoeat.databinding.FragmentHomeBinding
import com.e.wheretoeat.main.adapters.DataAdapter
import com.e.wheretoeat.main.models.Restaurant


class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)


        val list = generateDummyList(20)
        val recyclerView: RecyclerView = binding.recyclerView
        recyclerView.adapter = DataAdapter(list)
        recyclerView.layoutManager = LinearLayoutManager(activity )
        recyclerView.setHasFixedSize(true)
        return binding.root
    }

    private fun generateDummyList(size: Int): List<Restaurant> {
        val list = ArrayList<Restaurant>()
        for (i in 0 until size) {
            val item = Restaurant("Cim $i", "Address $i", i.toDouble())
            list += item
        }
        return list
    }

}