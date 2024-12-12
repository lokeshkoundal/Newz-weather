package com.example.newz.navigation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.newz.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class NavigationComponent : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navigation_component)

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragment) as NavHostFragment
        val navController = navHostFragment.navController
        bottomNavigationView.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {

                R.id.firstFragment -> bottomNavigationView.menu.findItem(R.id.homeFragment).isChecked = true
                R.id.secondFragment -> bottomNavigationView.menu.findItem(R.id.homeFragment).isChecked = true
                R.id.thirdFragment -> bottomNavigationView.menu.findItem(R.id.homeFragment).isChecked = true
                R.id.fourthFragment -> bottomNavigationView.menu.findItem(R.id.homeFragment).isChecked = true
                R.id.homeFragment -> bottomNavigationView.menu.findItem(R.id.homeFragment).isChecked = true
            }

        }

//        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
//        val navController = navHostFragment.navController



    }
}