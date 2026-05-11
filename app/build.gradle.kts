plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.della_pink"

    compileSdk {
        version = release(36) {
            minorApiLevel = 1
        }
    }

    defaultConfig {
        applicationId = "com.example.della_pink"
        minSdk = 27
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner =
            "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures {
        viewBinding = true
    }

    buildTypes {
        release {
            isMinifyEnabled = false

            proguardFiles(
                getDefaultProguardFile(
                    "proguard-android-optimize.txt"
                ),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)

    implementation(libs.material)

    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)

    // GridLayout
    implementation("androidx.gridlayout:gridlayout:1.0.0")

    // Glide
    implementation("com.github.bumptech.glide:glide:4.16.0")
    annotationProcessor(
        "com.github.bumptech.glide:compiler:4.16.0"
    )

    // Navigation Component
    implementation(
        "androidx.navigation:navigation-fragment-ktx:2.7.7"
    )

    implementation(
        "androidx.navigation:navigation-ui-ktx:2.7.7"
    )

    // Material Dialog
    implementation(
        "com.google.android.material:material:1.11.0"
    )

    // Testing
    testImplementation(libs.junit)

    androidTestImplementation(libs.androidx.junit)

    androidTestImplementation(
        libs.androidx.espresso.core
    )
}