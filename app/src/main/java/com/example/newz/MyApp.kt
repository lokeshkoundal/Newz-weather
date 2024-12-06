package com.example.newz

import android.app.Application
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class MyApp : Application() {



//    override fun onCreate() {
//        super.onCreate()
//
////        val workRequest = OneTimeWorkRequestBuilder<MyWorker>().build()
////        WorkManager.getInstance(this).enqueue(workRequest)
//
//        //set constraints :
//
//        val constraints  = Constraints.Builder()
//            .setRequiredNetworkType(NetworkType.CONNECTED)
//            .build()
//
//        //periodic request : work will be executed periodically : With constraints
//        val periodicWorkRequest = PeriodicWorkRequestBuilder<WeatherWorker>(10, TimeUnit.SECONDS)
//            .setConstraints(constraints)
//            .build()
//
//        WorkManager.getInstance(this).enqueueUniquePeriodicWork(
//            "SaveWeatherDataToRoomTask",
//            ExistingPeriodicWorkPolicy.UPDATE,
//            periodicWorkRequest
//        )

//        WorkManager.getInstance(this).enqueue(periodicWorkRequest)

//    }


}