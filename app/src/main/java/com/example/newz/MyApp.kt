package com.example.newz

import android.app.Application
import androidx.work.Constraints
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.example.newz.weather.WeatherWorker
import dagger.hilt.android.HiltAndroidApp
import java.util.concurrent.TimeUnit


@HiltAndroidApp
class MyApp : Application() {

    override fun onCreate() {
        super.onCreate()

        val constraints  = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()


        //periodic request : work will be executed periodically : With constraints
        val periodicWorkRequest = PeriodicWorkRequestBuilder<WeatherWorker>(30, TimeUnit.MINUTES)
            .setConstraints(constraints)
            .build()

        WorkManager.getInstance(this).enqueueUniquePeriodicWork(
            "SaveWeatherDataToRoomTask",
            ExistingPeriodicWorkPolicy.KEEP,
            periodicWorkRequest
        )

        //oneTimeWork:

//        val oneTimeWorkRequest = OneTimeWorkRequestBuilder<WeatherWorker>()
//            .setConstraints(constraints)
//            .build()
//
//        WorkManager.getInstance(this).enqueue(oneTimeWorkRequest)



//        WorkManager.getInstance(this).enqueue(periodicWorkRequest)

    }



}