package com.e.wheretoeat.main.fragments

import android.content.Context
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.viewinterop.viewModel
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.e.wheretoeat.R
import com.e.wheretoeat.databinding.FragmentProfileBinding
import com.e.wheretoeat.main.data.User
import com.e.wheretoeat.main.data.UserViewModel
import com.e.wheretoeat.main.viewmodels.MainViewModel

class ProfileFragment : Fragment() {
    private val mainViewModel: MainViewModel by activityViewModels()
    private lateinit var binding: FragmentProfileBinding
    private lateinit var sharedPref: SharedPreferences
    private lateinit var userName: String
    private lateinit var address: String
    private lateinit var phone: String
    private lateinit var email: String
    private lateinit var pictureUrl: Uri

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        requireActivity().findViewById<View>(R.id.bottomNavigationView).visibility = View.VISIBLE
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_profile, container, false)

        sharedPref = context?.getSharedPreferences("credentials", Context.MODE_PRIVATE)!!


        userName = sharedPref.getString("username", "").toString()
        address = sharedPref.getString("address", "").toString()
        phone = sharedPref.getString("phone", "").toString()
        email = sharedPref.getString("email", "").toString()
        pictureUrl = Uri.parse(sharedPref.getString("pictureUrl", "").toString())

        binding.userNameTextView.text = userName
        binding.addressTextView.text = address
        binding.phoneTextView.text = phone
        binding.emailTextView.text = email

        Log.d("Helo", "pictureUri: $pictureUrl")
        binding.logOutButton.setOnClickListener {
            val settings =
                requireContext().getSharedPreferences("credentials", Context.MODE_PRIVATE)
            settings.edit().clear().apply()
            findNavController().navigate(R.id.action_profileFragment_to_splashFragment)
        }

        return binding.root
    }

}