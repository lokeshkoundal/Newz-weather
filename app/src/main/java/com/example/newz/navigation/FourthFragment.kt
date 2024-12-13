package com.example.newz.navigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.example.newz.R


class FourthFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view  =  inflater.inflate(R.layout.fragment_fourth, container, false)

        val navController = findNavController()

// Access the back stack entry for the old fragment
        view.findViewById<Button>(R.id.button6).setOnClickListener {
            val savedStateHandle = navController.getBackStackEntry(R.id.homeFragment).savedStateHandle

            val data = view.findViewById<EditText>(R.id.editText).text
            savedStateHandle.set("data", data.toString())

            navController.navigate(
                R.id.homeFragment,null, NavOptions.Builder()
                .setLaunchSingleTop(true)
                .setPopUpTo(R.id.homeFragment,false)
                .build())

            // OR -- > add return to source action (in nav graph)
//            navController.navigate(R.id.action_navigation2_pop2)
        }
        return view
    }
}