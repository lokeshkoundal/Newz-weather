plugins{
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("com.google.devtools.ksp")
//    id("androidx.navigation.safeargs")
    id("androidx.navigation.safeargs.kotlin")
//    id("androidx.navigation.safeargs")


    id ("kotlin-kapt")
    id("com.google.dagger.hilt.android")
    kotlin("plugin.serialization") version "2.0.21"

}

android {
    namespace = "com.example.newz"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.newz"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"),"proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures{
        viewBinding = true
        dataBinding = true
    }
}

dependencies {

//  workManager
    implementation(libs.androidx.work.runtime.ktx)

//  hilt
    implementation(libs.dagger.hilt.android)
    implementation(libs.androidx.hilt.common)
    kapt(libs.hilt.android.compiler)


    implementation(libs.androidx.paging.runtime.ktx)

    implementation (libs.glide)

    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.swiperefreshlayout)
    implementation(libs.symbol.processing.api)

    // Views/Fragments integration
//    implementation(libs.androidx.navigation.fragment)
    implementation(libs.androidx.navigation.ui)
    implementation(libs.androidx.navigation.fragment.ktx) // Replace with your version

    kapt(libs.androidx.room.compiler)

    // To use Kotlin Symbol Processing (KSP)
//    kapt(libs.androidx.room.compiler)
//    kapt (libs.androidx.room.compiler.v250)

    // optional - Kotlin Extensions and Coroutines support for Room
    implementation(libs.androidx.room.ktx)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.activity.ktx) // Ensure you're using the latest version

    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    implementation(libs.retrofit)
    implementation (libs.logging.interceptor)
    implementation(libs.converter.gson)

    // Coroutines
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.coroutines.android)

    // ViewModel and LiveData
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.lifecycle.livedata.ktx)
}

kapt {
    correctErrorTypes = true
}