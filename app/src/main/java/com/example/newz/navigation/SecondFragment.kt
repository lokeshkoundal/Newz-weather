package com.example.newz.navigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.example.newz.R

class SecondFragment : Fragment() {

    val args : SecondFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view =  inflater.inflate(R.layout.fragment_second, container, false)

        val myNumber = args.name
        view.findViewById<TextView>(R.id.tv2).text = myNumber

        view.findViewById<Button>(R.id.button3).setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_secondFragment_to_thirdFragment)
        }

        view.findViewById<Button>(R.id.button4).setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_secondFragment_to_fourthFragment)
        }

        return view
    }
}