package com.e.wheretoeat.main.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.e.wheretoeat.R
import com.e.wheretoeat.databinding.FragmentProfileBinding
import com.e.wheretoeat.main.data.UserViewModel

class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding
    private lateinit var mUserViewModel: UserViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        requireActivity().findViewById<View>(R.id.bottomNavigationView).visibility = View.VISIBLE
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_profile, container, false)

//        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
//        mUserViewModel.readAllData.observe(viewLifecycleOwner, Observer { user ->
//
//            //user[0] because user is a list and now I only have 1 user
//            binding.userNameTextView.text = user[0].userName
//
//        })

        binding.logOutButton.setOnClickListener {
            val settings =
                requireContext().getSharedPreferences("credentials", Context.MODE_PRIVATE)
            settings.edit().clear().apply()
            findNavController().navigate(R.id.action_profileFragment_to_splashFragment)
        }

        return binding.root
    }

}