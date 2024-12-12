package com.example.newz

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController


class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_home, container, false)

        val savedStateHandle = findNavController().currentBackStackEntry?.savedStateHandle

        savedStateHandle?.getLiveData<String>("data")?.observe(viewLifecycleOwner){
            view.findViewById<TextView>(R.id.textView3).text = it
        }


        view.findViewById<Button>(R.id.btn).setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.navigation2)
        }

        return view
    }


}