package com.e.wheretoeat.main.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.e.wheretoeat.R
import com.e.wheretoeat.databinding.FragmentMapsBinding
import com.e.wheretoeat.main.viewmodels.MainViewModel
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions


class MapsFragment : Fragment(), OnMapReadyCallback {

    lateinit var map: GoogleMap
    private val mainViewModel: MainViewModel by activityViewModels()
    lateinit var binding: FragmentMapsBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_maps, container, false)
        val view = binding.root

        val mapFragment: SupportMapFragment? =
            childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(this)

        return view
    }

    override fun onMapReady(googleMap: GoogleMap?) {
        map = googleMap!!

        val lat = mainViewModel.currentApiRestaurant.lat.toDouble()
        val lng = mainViewModel.currentApiRestaurant.lng.toDouble()

        val restTitle = mainViewModel.currentApiRestaurant.name
        val location = LatLng(lat, lng)
        map.addMarker(MarkerOptions().position(location).title(restTitle))
        map.moveCamera(CameraUpdateFactory.newLatLng(location))
    }
}