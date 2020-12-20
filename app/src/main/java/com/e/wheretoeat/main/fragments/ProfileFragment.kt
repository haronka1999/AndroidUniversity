package com.e.wheretoeat.main.fragments

import android.content.Context
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.e.wheretoeat.R
import com.e.wheretoeat.databinding.FragmentProfileBinding
import com.e.wheretoeat.main.viewmodels.MainViewModel

class ProfileFragment : Fragment() {
    private val mainViewModel: MainViewModel by activityViewModels()
    private lateinit var binding: FragmentProfileBinding
    private lateinit var sharedPref: SharedPreferences
    private lateinit var userName: String
    private lateinit var address: String
    private lateinit var phone: String
    private lateinit var email: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPref = context?.getSharedPreferences("credentials", Context.MODE_PRIVATE)!!
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        requireActivity().findViewById<View>(R.id.bottomNavigationView).visibility = View.VISIBLE
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_profile, container, false)

        bindItems()
        editItem()

        binding.favoriteButton.setOnClickListener {
            findNavController().navigate(R.id.action_profileFragment_to_favoriteFragment)
        }

        binding.logOutButton.setOnClickListener {
            val settings =
                requireContext().getSharedPreferences("credentials", Context.MODE_PRIVATE)
            settings.edit().clear().apply()
            findNavController().navigate(R.id.action_profileFragment_to_registerFragment2)
        }

        return binding.root
    }

    private fun editItem() {
        binding.editUserName.setOnClickListener {
            mainViewModel.dataBeEdited = 0
            EditDialogFragment().show(parentFragmentManager, "")
        }
        binding.editAddress.setOnClickListener {
            mainViewModel.dataBeEdited = 1
            EditDialogFragment().show(parentFragmentManager, "")
        }

        binding.editNumber.setOnClickListener {
            mainViewModel.dataBeEdited = 2
            EditDialogFragment().show(parentFragmentManager, "")
        }

        binding.editEmail.setOnClickListener {
            mainViewModel.dataBeEdited = 3
            EditDialogFragment().show(parentFragmentManager, "")
        }
    }

    private fun bindItems() {
        userName = sharedPref.getString("username", "").toString()
        address = sharedPref.getString("address", "").toString()
        phone = sharedPref.getString("phone", "").toString()
        email = sharedPref.getString("email", "").toString()
        val image = sharedPref.getString("pictureUrl", "")
        Glide.with(requireContext())
            .load(image)
            .into(binding.profilePic)
        binding.userNameTextView.text = userName
        binding.addressTextView.text = address
        binding.phoneTextView.text = phone
        binding.emailTextView.text = email
    }
}