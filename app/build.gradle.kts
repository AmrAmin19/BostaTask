plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}

android {
    namespace = "com.example.bostatask"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.bostatask"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    buildFeatures {
        viewBinding = true
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
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)


    // Hilt (Dependency Injection)
    implementation("com.google.dagger:hilt-android:2.48")
    kapt("com.google.dagger:hilt-compiler:2.48")
    implementation("androidx.hilt:hilt-navigation-compose:1.0.0")

    // Retrofit & Networking
    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    implementation(libs.gson)
    implementation("com.squareup.okhttp3:okhttp:4.9.3")

    // Lifecycle Components
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")


    // test

    // JUnit
    testImplementation("junit:junit:4.13.2")

// Mockk for mocking dependencies
    testImplementation("io.mockk:mockk:1.13.3")

// Coroutines Testing
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.7.1")

// LiveData Testing
    testImplementation("androidx.arch.core:core-testing:2.2.0")

// Truth (Optional, for better assertions)
    testImplementation("com.google.truth:truth:1.1.3")




    // Robolectric for testing
    testImplementation("org.robolectric:robolectric:4.9.2")

    // Timber for logging
    implementation("com.jakewharton.timber:timber:5.0.1")

    // Mockito-inline for mockito extensions
    testImplementation("org.mockito:mockito-inline:4.6.1")

    // Core testing library for LiveData and ViewModel tests
    testImplementation("androidx.arch.core:core-testing:2.1.0")

    // Mockito-Kotlin for better Kotlin integration with Mockito
    testImplementation("org.mockito.kotlin:mockito-kotlin:4.0.0")

    // Turbine for testing
    testImplementation ("app.cash.turbine:turbine:0.6.1")
    // For mocking dependencies
    testImplementation ("org.mockito:mockito-core:4.5.1")
    testImplementation ("org.mockito.kotlin:mockito-kotlin:4.0.0")

    // Hilt testing dependencies
    androidTestImplementation ("com.google.dagger:hilt-android-testing:2.48")
    kaptAndroidTest ("com.google.dagger:hilt-android-compiler:2.48")

    // Hilt testing for unit tests
    testImplementation ("com.google.dagger:hilt-android-testing:2.48")
    kaptTest ("com.google.dagger:hilt-android-compiler:2.48")




}