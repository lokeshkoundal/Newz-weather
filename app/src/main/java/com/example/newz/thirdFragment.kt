package com.example.newz

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation


class thirdFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =   inflater.inflate(R.layout.fragment_third, container, false)


        view.findViewById<Button>(R.id.button5).setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_thirdFragment_to_fourthFragment)
        }
        return view
    }

}