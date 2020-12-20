package com.e.wheretoeat.main.fragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import com.e.wheretoeat.R
import com.e.wheretoeat.databinding.FragmentEditDialogBinding
import com.e.wheretoeat.main.viewmodels.MainViewModel
import com.e.wheretoeat.main.viewmodels.UserViewModel

class EditDialogFragment : DialogFragment() {


    //viewmodels
    private val mainViewModel: MainViewModel by activityViewModels()
    private lateinit var mUserViewModel: UserViewModel

    private lateinit var binding: FragmentEditDialogBinding
    private lateinit var sharedPref: SharedPreferences
    private lateinit var newValue: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        sharedPref = context?.getSharedPreferences("credentials", Context.MODE_PRIVATE)!!
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_edit_dialog, container, false)

        binding.cancelButton.setOnClickListener {
            dismiss()
        }

        binding.saveButton.setOnClickListener {
            newValue = binding.newValueEditText.text.toString()
            if (newValue == "") {
                Toast.makeText(activity, "Please add a new value", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            insertNewData()
            dismiss()
        }
        return binding.root
    }


    //defines which data need to be edited
    private fun insertNewData() {
        val dataBeEdited = mainViewModel.dataBeEdited
        val editor = sharedPref.edit()
        when (dataBeEdited) {
            0 -> {
                editor.putString("username", newValue)
                editor.apply()
            }
            1 -> {
                editor.putString("address", newValue)
                editor.apply()
            }
            2 -> {
                editor.putString("phone", newValue)
                editor.apply()
            }
            3 -> {
                editor.putString("email", newValue)
                editor.apply()
            }
            else -> {
                //error
                Toast.makeText(activity, "Something went wrong", Toast.LENGTH_SHORT).show()
            }
        }
    }
}