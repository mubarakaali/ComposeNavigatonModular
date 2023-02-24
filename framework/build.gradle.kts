plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.android.kotlin)
    alias(libs.plugins.hilt)
    alias(libs.plugins.google.firebase)
    id("kotlin-kapt")
}

android {
    namespace = "com.framework"
    compileSdk = libs.versions.sdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.minSdk.get().toInt()
        targetSdk = libs.versions.sdk.get().toInt()

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }
    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.compose.compiler.v.get()
        kotlinCompilerVersion = libs.versions.kotlin.compiler.version.v.get()
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    coreLibraryDesugaring(libs.jdk.desugaring)

    api(libs.bundles.android.core)
    api(libs.bundles.android.lifecycle)

    api(libs.bundles.compose)
    debugImplementation(libs.bundles.compose.debug)

    api(libs.bundles.accompanist)
    api(libs.coroutines)

    api(platform(libs.firebase.platform))
    api(libs.bundles.firebase.common)
    api(libs.firebase.config)

    implementation(libs.bundles.di)
    kapt(libs.bundles.di.kapt)
}