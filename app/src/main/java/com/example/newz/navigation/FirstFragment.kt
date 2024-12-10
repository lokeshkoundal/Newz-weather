package com.example.newz.navigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.newz.R

class FirstFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_first, container, false)

        view.findViewById<TextView>(R.id.tv1).setOnClickListener {
//            val action = FirstFragmentDirections.actionFirstFragmentToSecondFragment()
            val action = FirstFragmentDirections.actionFirstFragmentToSecondFragment("this is the String")
            Navigation.findNavController(view).navigate(action)
        }
        return view
    }

}