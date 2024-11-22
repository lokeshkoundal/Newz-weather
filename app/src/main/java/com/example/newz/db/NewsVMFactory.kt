package com.example.newz.db

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class NewsVMFactory(private val application: Application) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NewsVmDb::class.java)) {
            return NewsVmDb(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}