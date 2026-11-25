plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.cerjapk.prototype"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.cerjapk.prototype.phase1"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
        getByName("debug") {
            isDebuggable = true
        }
    }
    // AAPT2 & D8/R8 adalah bawaan AGP; hasil build APK ditempatkan di outputs
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    // Tanpa library eksternal untuk MVP minimal; bisa ditambahkan setelah validasi build local
    // misal: implementation("androidx.appcompat:appcompat:1.6.1")
}