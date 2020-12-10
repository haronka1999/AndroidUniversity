package com.e.wheretoeat.main.fragments

import android.content.Context
import android.content.SharedPreferences
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.e.wheretoeat.R
import com.e.wheretoeat.databinding.FragmentProfileBinding
import com.e.wheretoeat.main.viewmodels.MainViewModel
import com.squareup.picasso.Picasso

class ProfileFragment : Fragment() {
    private val mainViewModel: MainViewModel by activityViewModels()
    private lateinit var binding: FragmentProfileBinding
    private lateinit var sharedPref: SharedPreferences
    private lateinit var userName: String
    private lateinit var address: String
    private lateinit var phone: String
    private lateinit var email: String
    private lateinit var bitmap: Bitmap

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        requireActivity().findViewById<View>(R.id.bottomNavigationView).visibility = View.VISIBLE
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_profile, container, false)
        sharedPref = context?.getSharedPreferences("credentials", Context.MODE_PRIVATE)!!


        binding.editUserName.setOnClickListener {
            mainViewModel.dataBeEdited = 0
       //     val action = ProfileFragmentDirections.actionProfileFragmentToEditDialogFragment(currentUser)
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





        userName = sharedPref.getString("username", "").toString()
        address = sharedPref.getString("address", "").toString()
        phone = sharedPref.getString("phone", "").toString()
        email = sharedPref.getString("email", "").toString()

        val bitmapString = sharedPref.getString("pictureUrl", "").toString()
        val img: ByteArray = bitmapString.toByteArray()
        Log.d("Helo", "img: $img")

        binding.userNameTextView.text = userName
        binding.addressTextView.text = address
        binding.phoneTextView.text = phone
        binding.emailTextView.text = email

        binding.logOutButton.setOnClickListener {
            val settings =
                requireContext().getSharedPreferences("credentials", Context.MODE_PRIVATE)
            settings.edit().clear().apply()
            findNavController().navigate(R.id.action_profileFragment_to_registerFragment2)
        }

        return binding.root
    }

}