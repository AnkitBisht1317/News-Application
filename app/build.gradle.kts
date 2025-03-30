plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.ab.newsapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.ab.newsapp"
        minSdk = 25
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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

    // Ensure compatibility with legacy multidex if needed
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    // Core AndroidX Dependencies
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.9.0")
    implementation("androidx.activity:activity-ktx:1.8.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")

    // Unit Testing
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    // Retrofit for API calls
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")

    // Readable Bottom Bar
    implementation("com.github.iammert:ReadableBottomBar:0.2")

    // Multidex support for large apps
    implementation("androidx.multidex:multidex:2.0.1")

    // OkHttp for logging network requests
    implementation("com.squareup.okhttp3:logging-interceptor:4.9.3")

    implementation("com.google.android.material:material:1.11.0") // Latest Material Components
    implementation("com.squareup.okhttp3:logging-interceptor:4.12.0") // Latest OkHttp

    // Glide for image loading
    implementation("com.github.bumptech.glide:glide:4.15.1")
    annotationProcessor("com.github.bumptech.glide:compiler:4.15.1")
}

// Resolve lifecycle version conflicts
configurations.all {
    resolutionStrategy.eachDependency {
        if (requested.group == "androidx.lifecycle") {
            useVersion("2.6.2")
        }
    }
}
