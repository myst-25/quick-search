plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    // The oss-licenses plugin must be applied selectively to avoid failing F-Droid builds
    // We apply it later if not building fdroid
    id("kotlin-parcelize")
}

android {
    namespace = "com.myst25.quicksearch"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.myst25.quicksearch"
        minSdk = 24
        targetSdk = 36
        versionCode = 66
        versionName = "3.6.1"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    flavorDimensions += "distribution"
    productFlavors {
        create("standard") {
            dimension = "distribution"
            isDefault = true
        }
        create("fdroid") {
            dimension = "distribution"
        }
    }

    buildTypes {
        debug {
            applicationIdSuffix = ".debug"
            isDebuggable = true
            isJniDebuggable = true
            isMinifyEnabled = false
            resValue("string", "app_name", "QS Pro Debug")
        }
        release {
            signingConfig = signingConfigs.getByName("debug")
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
            )
        }
    }

    adbOptions {
        installOptions("--user", "0")
    }

    buildFeatures {
        compose = true
        buildConfig = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.lifecycle.runtime.compose)
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.material.icons.extended)
    implementation(libs.androidx.glance.appwidget)
    implementation(libs.androidx.profileinstaller)
    implementation(libs.google.material)
    implementation(libs.androidx.security.crypto)
    implementation(libs.okhttp)
    "standardImplementation"(libs.play.review.ktx)
    "standardImplementation"(libs.play.app.update.ktx)
    implementation(libs.libphonenumber)
    implementation(libs.reorderable)
    implementation(libs.androidx.browser)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}
// Conditionally apply OSS licenses plugin and dependency for standard builds
val isFdroidBuild = gradle.startParameter.taskNames.any { it.contains("fdroid", ignoreCase = true) }
if (!isFdroidBuild) {
    apply(plugin = "com.google.android.gms.oss-licenses-plugin")
    dependencies {
        "implementation"(libs.play.services.oss.licenses)
    }
    
    // Gradle 8.13+ validates implicit task input/output dependencies.
    // The OSS licenses plugin’s cleanup task consumes the generated dependencies file,
    // so we explicitly wire the task dependency to keep builds deterministic.
    tasks.matching { it.name == "debugOssLicensesCleanUp" }.configureEach {
        dependsOn("debugOssDependencyTask")
    }
}
