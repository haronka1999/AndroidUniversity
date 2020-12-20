package com.e.wheretoeat.main.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.e.wheretoeat.R
import com.e.wheretoeat.databinding.FragmentMapsBinding
import com.e.wheretoeat.main.viewmodels.MainViewModel
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions


class MapsFragment : Fragment(), OnMapReadyCallback {

    private val mainViewModel: MainViewModel by activityViewModels()

    lateinit var map: GoogleMap
    lateinit var binding: FragmentMapsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_maps, container, false)
        val view = binding.root

        val mapFragment: SupportMapFragment? = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(this)

        return view
    }


    /*
    it is called after the method: getMapAsync
    and it displays the current restaurant on the map
     */
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