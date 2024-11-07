package com.example.newz.room.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.newz.R
import com.example.newz.room.User
import com.example.newz.room.UserViewModel
import com.example.newz.room.UserViewModelFactory


class AddFragment : Fragment() {

    private  lateinit var vm : UserViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add, container, false)

//        vm = ViewModelProvider(requireActivity())[UserViewModel::class.java]
        vm = ViewModelProvider(this, UserViewModelFactory(application = requireActivity().application)).get(UserViewModel::class.java)
        view.findViewById<Button>(R.id.button).setOnClickListener{
            saveDataToDB(view)
        }

        return view
    }

    private fun saveDataToDB(view:View) {
        val firstName = view.findViewById<EditText>(R.id.textInputEditText).text.toString().trim()
        val lastName = view.findViewById<EditText>(R.id.textInputEditText2).text.toString().trim()
        val age = view.findViewById<EditText>(R.id.textInputEditText4).text.toString()

        if(firstName.isNotEmpty() && lastName.isNotEmpty() && age.isNotEmpty()){
            val user = User(0,firstName,lastName,Integer.parseInt(age))
            vm.addUser(user)
            Toast.makeText(requireContext(),"Successfully Added",Toast.LENGTH_SHORT).show()

            //navigateBack :
            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        }else{
            Toast.makeText(requireContext(),"Please fill all fields",Toast.LENGTH_SHORT).show()

        }
    }


}