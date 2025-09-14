import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.ksp)
    alias(libs.plugins.jetbrains.kotlin.serialization)
    alias(libs.plugins.jetbrains.kotlin.parcelable)
    alias(libs.plugins.dagger.hilt.android)
}

android {

    namespace = "com.stoyanvuchev.stylepaper"
    compileSdk = 36

    defaultConfig {

        applicationId = "com.stoyanvuchev.stylepaper"
        minSdk = 23
        targetSdk = 36
        versionCode = 1
        versionName = "0.1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables.useSupportLibrary = true

        buildConfigField(
            "String",
            "BASE_URL",
            gradleLocalProperties(
                rootDir,
                providers
            ).getProperty("BASE_URL")
        )

        buildConfigField(
            "String",
            "API_KEY",
            gradleLocalProperties(
                rootDir,
                providers
            ).getProperty("API_KEY")
        )

        buildConfigField(
            "String",
            "WALLPAPERS_LISTING_ENDPOINT",
            gradleLocalProperties(
                rootDir,
                providers
            ).getProperty("WALLPAPERS_LISTING_ENDPOINT")
        )

        buildConfigField(
            "String",
            "SEARCH_WALLPAPERS_LISTING_ENDPOINT",
            gradleLocalProperties(
                rootDir,
                providers
            ).getProperty("SEARCH_WALLPAPERS_LISTING_ENDPOINT")
        )

        buildConfigField(
            "String",
            "WALLPAPER_BY_ID_ENDPOINT",
            gradleLocalProperties(
                rootDir,
                providers
            ).getProperty("WALLPAPER_BY_ID_ENDPOINT")
        )

    }

    buildTypes {

        release {

            isMinifyEnabled = true
            isShrinkResources = true
            isDebuggable = false

            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )

            signingConfig = signingConfigs.getByName("debug")

        }

    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlin {
        jvmToolchain(17)
    }

    buildFeatures {
        compose = true
        buildConfig = true
    }

    packagingOptions.resources.excludes.add("/META-INF/*")

}

dependencies {

    // Core dependencies
    implementation(libs.core)
    implementation(libs.lifecycle.runtime.ktx)
    implementation(libs.lifecycle.viewmodel.savedstate)
    implementation(libs.material)

    // Compose dependencies
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.lifecycle.runtime.compose)
    implementation(libs.lifecycle.viewmodel.compose)

    // Compose BOM
    implementation(platform(libs.compose.bom))
    implementation(libs.compose.foundation)
    implementation(libs.compose.runtime)
    implementation(libs.compose.animation)
    implementation(libs.compose.animation.core)
    implementation(libs.compose.animation.graphics)
    implementation(libs.compose.ui)
    implementation(libs.compose.ui.graphics)
    implementation(libs.compose.material3)
    implementation(libs.compose.material3.windowSizeClass)
    implementation(libs.androidx.animation)

    debugImplementation(libs.compose.ui.tooling)
    debugImplementation(libs.compose.ui.tooling.preview)
    debugImplementation(libs.compose.ui.test.manifest)

    androidTestImplementation(libs.compose.ui.test.manifest)
    androidTestImplementation(libs.compose.ui.test.junit4)

    // Coil
    implementation(libs.coil)

    // Dagger - Hilt
    implementation(libs.dagger.hilt.android)
    ksp(libs.dagger.hilt.compiler)
    ksp(libs.androidx.hilt.compiler)
    implementation(libs.androidx.hilt.navigation.compose)

    // Retrofit
    implementation(libs.retrofit)
    implementation(libs.retrofit.converter.moshi)
    implementation(libs.okhttp)
    implementation(libs.okhttp.logging.interceptor)
    implementation(libs.retrofit.converter.gson)

    // Serialization
    implementation(libs.gson)
    implementation(libs.kotlinx.serialization.json)

    // Room & Datastore
    implementation(libs.room.ktx)
    implementation(libs.room.runtime)
    ksp(libs.room.compiler)

    implementation(libs.datastore.preferences)

    // Better dateTime-time support even on older Android versions
    implementation(libs.threetenabp)

    // Other
    implementation(libs.systemUIBarsTweaker)
    implementation(libs.squircleShape)

    // Dependencies for Unit tests
    testImplementation(libs.jUnit)
    testImplementation(libs.assertK)
    testImplementation(libs.mockk)
    testImplementation(libs.coroutines.test)
    testImplementation(libs.dagger.hilt.android.testing)
    kspTest(libs.dagger.hilt.compiler)
    testImplementation(libs.appCash.turbine)
    testImplementation(libs.mockwebserver)


    // Dependencies for Instrumentation tests
    androidTestImplementation(libs.androidx.arch.core.testing)
    androidTestImplementation(libs.androidx.test.ext)
    androidTestImplementation(libs.espresso.core)
    androidTestImplementation(libs.androidx.test.core)
    androidTestImplementation(libs.assertK)
    androidTestImplementation(libs.mockk)
    androidTestImplementation(libs.androidx.navigation.testing)
    androidTestImplementation(libs.coroutines.test)
    androidTestImplementation(libs.dagger.hilt.android.testing)
    kspAndroidTest(libs.dagger.hilt.compiler)
    kspAndroidTest(libs.androidx.hilt.compiler)
    androidTestImplementation(libs.appCash.turbine)
    androidTestImplementation(libs.mockwebserver)

}

ksp {
    arg("room.schemaLocation", "$projectDir/schemas")
}