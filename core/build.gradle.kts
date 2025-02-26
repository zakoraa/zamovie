import java.util.Properties

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.hiltAndroid)
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.raflis.core"
    compileSdk = 35

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")

        val keystoreFile = project.rootProject.file("apikey.properties")
        val properties = Properties()
        properties.load(keystoreFile.inputStream())

        val apiKey = properties.getProperty("API_KEY") ?: ""

        buildConfigField(
            "String",
            "BASE_URL",
            "\"https://api.themoviedb.org/3/\""
        )

        buildConfigField(
            "String",
            "BASE_URL_IMAGE",
            "\"https://image.tmdb.org/t/p/w500\""
        )

        buildConfigField(
            "String",
            "API_KEY",
            "\"$apiKey\""
        )
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
        buildConfig = true
    }
}

dependencies {

    api(libs.androidx.core.ktx)
    api(libs.androidx.appcompat)
    api(libs.androidx.constraintlayout)
    api(libs.junit)
    api(libs.androidx.junit)
    api(libs.androidx.espresso.core)

    api(libs.material)
    api(libs.recyclerview)
    api(libs.glide)

    implementation(libs.hilt.android)
    ksp(libs.hilt.android.compiler)

    implementation(libs.room.runtime)
    ksp(libs.room.compiler)
    implementation(libs.room.testing)
    implementation(libs.androidx.room.ktx)

    api(libs.material)
    api(libs.androidx.activity)

    api(libs.retrofit)
    api(libs.converter.gson)
    api(libs.logging.interceptor)

    api(libs.kotlinx.coroutines.core)
    api(libs.kotlinx.coroutines.android)
    api(libs.androidx.lifecycle.livedata.ktx)

    api(libs.feature.delivery.ktx)

    api(libs.android.database.sqlcipher)
    api(libs.androidx.sqlite.ktx)

    api(libs.androidx.security.crypto)
    api(libs.secure.preferences.lib)
}