plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    kotlin("plugin.serialization") version "2.0.21"
}

android {
    namespace = "dev.nashe.pokemon.data"
    compileSdk = 34

    defaultConfig {
        minSdk = 29

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
        buildConfigField("String", "BASE_URL", "\"https://pokeapi.co/api/v2/\"")
    }

    buildFeatures {
        buildConfig = true
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
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
            excludes += "/META-INF/gradle/incremental.annotation.processors"
        }
    }
    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.kotlin.coroutines)
    implementation(libs.okhttp)
    implementation(libs.gson.converter)
    implementation(libs.okhttp.logging.interceptor)
    implementation(libs.javaxAnnotation)
    implementation(libs.javaxInject)
    implementation(libs.kotlin.serialization)

    implementation(project(":domain"))

    implementation(libs.retrofit)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
}
