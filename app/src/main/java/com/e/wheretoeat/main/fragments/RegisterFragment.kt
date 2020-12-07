package com.e.wheretoeat.main.fragments

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.e.wheretoeat.R
import com.e.wheretoeat.databinding.FragmentRegisterBinding
import com.e.wheretoeat.main.activities.MainActivity
import com.e.wheretoeat.main.data.User
import com.e.wheretoeat.main.data.UserViewModel
import java.math.BigInteger
import java.security.MessageDigest


class RegisterFragment : Fragment() {

    private lateinit var mUSerViewModel: UserViewModel
    private lateinit var binding: FragmentRegisterBinding
    private lateinit var email: String
    private lateinit var userName: String
    private lateinit var password: String
    private var userID: Int = 0
    private var userNames: MutableList<String> = mutableListOf()
    private var emails: MutableList<String> = mutableListOf()
    private lateinit var sharedPref: SharedPreferences


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_register, container, false
        )

        sharedPref = context?.getSharedPreferences("credentials", Context.MODE_PRIVATE)!!
        binding.saveButton.setOnClickListener {
            userName = binding.userNameEditText.text.toString()
            password = binding.passwordEditText.text.toString()
            if (!isValidForm(userName, password)) {
                return@setOnClickListener
            }
            val editor = sharedPref.edit()
            editor.clear()
            editor.putString("username", userName)
            editor.putString("password", password)
            editor.apply()
            findNavController().navigate(R.id.action_registerFragment2_to_homeFragment)

        }
        return binding.root
    }


    private fun isValidForm(userName: String, password: String): Boolean {
        if (TextUtils.isEmpty(userName)) {
            binding.userNameEditText.error = "UserName is Required"
            return false
        }


        if (TextUtils.isEmpty(password)) {
            binding.passwordEditText.error = "Password is Required"
            return false
        }

        if (userName.length >= 10) {
            binding.userNameEditText.error = "User name is too long"
            return false
        }


        if (password.length < 6) {
            binding.passwordEditText.error = "Password must be 6 character long"
            return false
        }

        if (userNames.contains(userName)) {
            binding.userNameEditText.error = "This Username is taken"
            return false
        }

        return true
    }


}
