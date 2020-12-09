package com.e.wheretoeat.main.fragments

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.text.TextUtils
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
import com.e.wheretoeat.main.data.User
import com.e.wheretoeat.main.data.UserViewModel


class RegisterFragment : Fragment() {

    private lateinit var mUserViewModel: UserViewModel
    private lateinit var binding: FragmentRegisterBinding
    private lateinit var userName: String
    private lateinit var address: String
    private lateinit var phone: String
    private lateinit var email: String
    private lateinit var pictureUrl: String
    private var userNames: MutableList<String> = mutableListOf()
    private lateinit var sharedPref: SharedPreferences

    companion object {
        //image pick code
        val IMAGE_PICK_CODE = 1;
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        requireActivity().findViewById<View>(R.id.bottomNavigationView).visibility = View.GONE
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_register, container, false
        )

        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        sharedPref = context?.getSharedPreferences("credentials", Context.MODE_PRIVATE)!!

        binding.chooseImageButton.setOnClickListener {
            pickImageFromGallery()
        }

        binding.saveButton.setOnClickListener {
            userName = binding.userNameEditText.text.toString()
            address = binding.addressEditText.text.toString()
            phone = binding.phoneNumber.text.toString()
            email = binding.emailEditText.text.toString()
//            if (!isValidForm(userName, password)) {
//                return@setOnClickListener
//            }

            //add the user details to shared preferences
            val editor = sharedPref.edit()
            editor.clear()
            editor.putString("username", userName)
            editor.putString("address", address)
            editor.putString("phone", phone)
            editor.putString("email", email)
            editor.apply()

            //add data to database
            insertUserIntoDataBase()
            findNavController().navigate(R.id.action_registerFragment2_to_homeFragment)

        }
        return binding.root
    }

    private fun insertUserIntoDataBase() {
        val user = User(0,userName,pictureUrl,address,phone,email)
        mUserViewModel.addUser(user)
        Toast.makeText(activity, "Successfully added", Toast.LENGTH_SHORT).show()
    }

    private fun pickImageFromGallery() {
        //Intent to pick image
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(intent, IMAGE_PICK_CODE)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1 && resultCode == Activity.RESULT_OK && data != null && data.data != null) {
            val imageUri = data.data!!
            pictureUrl = imageUri.toString()
            binding.registerProfileImage.setImageURI(imageUri)
        }
    }





    private fun isValidForm(userName: String, password: String): Boolean {
        if (TextUtils.isEmpty(userName)) {
            binding.userNameEditText.error = "UserName is Required"
            return false
        }


        if (userName.length >= 10) {
            binding.userNameEditText.error = "User name is too long"
            return false
        }


        if (userNames.contains(userName)) {
            binding.userNameEditText.error = "This Username is taken"
            return false
        }

        return true
    }


}
