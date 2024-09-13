plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("kotlin-kapt")
    alias(libs.plugins.hilt.plugin)
}

android {
    namespace = "com.baitent.vocabulity"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.baitent.vocabulity"
        minSdk = 23
        targetSdk = 34
        versionCode = 4
        versionName = "4.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables.useSupportLibrary = true
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        viewBinding = true
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.core.ktx)
    implementation(libs.material)
    implementation(libs.androidx.swiperefreshlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.espresso.core)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    kapt(libs.room.compiler)
    implementation(libs.room.runtime)
    implementation(libs.room.ktx)
    kapt(libs.hilt.compiler)
    implementation(libs.hilt.android)
    implementation(libs.navigation.fragment)
    implementation(libs.navigation.ui)


    implementation ("com.lorentzos.swipecards:library:1.0.9")
    implementation (libs.androidx.recyclerview)
    implementation (libs.androidx.viewpager2)
    implementation (libs.androidx.appcompat.v161)
    implementation (libs.androidx.fragment.ktx)
    implementation ("com.google.code.gson:gson:2.11.0")

}
