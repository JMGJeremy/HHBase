@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinAndroid)
    id("maven-publish")
    id("com.google.devtools.ksp")
    id("org.jetbrains.kotlin.plugin.compose") version "2.0.0"
}

android {
    namespace = "com.jmg.baseproject"
    compileSdk = 34

    defaultConfig {
        minSdk = 26

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
        jvmTarget = "1.8"
    }
    publishing {
        singleVariant("release"){
            withSourcesJar()
        }
    }

    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.14"
    }
}

dependencies {
    implementation(libs.core.ktx)
    implementation(libs.lifecycle.runtime.ktx)
    implementation(libs.activity.compose)
    implementation(platform(libs.compose.bom))
    implementation(libs.ui)
    implementation(libs.ui.graphics)
    implementation(libs.ui.tooling.preview)
    implementation(libs.material3)
    implementation(libs.androidx.hilt.navigation.compose)


    //Compose
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation (libs.androidx.material.icons.extended)
    implementation (libs.androidx.material)
    implementation (libs.androidx.constraintlayout.compose)
    implementation (libs.androidx.lifecycle.viewmodel.compose)
    implementation (libs.androidx.hilt.navigation.compose)

    //coil
    implementation(libs.coil.compose)

// retrofit
    implementation (libs.retrofit)
    implementation (libs.converter.gson)
    implementation (libs.logging.interceptor)
    implementation(libs.adapter.rxjava2)

    //Room
    implementation (libs.androidx.room.ktx)
    implementation (libs.androidx.room.runtime)
    ksp(libs.androidx.room.compiler)

    //Camera

    implementation(libs.androidx.camera.core)
    implementation(libs.androidx.camera.camera2)
    implementation(libs.androidx.camera.lifecycle)
    implementation(libs.androidx.ui)
    implementation(libs.androidx.camera.view)
    implementation(libs.androidx.camera.video)


    //Permissions
    implementation(libs.accompanist.permissions)

    // ExoPlayer
    implementation(libs.androidx.media3.exoplayer)
    implementation(libs.androidx.media3.ui)

    //Zoom
    implementation(libs.zoom)
    //Dyte
    implementation(libs.core.android)
//    implementation(libs.uikit)
}

publishing{
    publications{
        register<MavenPublication>("release"){
            groupId = "com.jmgjeremy"
            artifactId = "hhbase"
            version = "1.2.33"
            afterEvaluate{
                from(components["release"])
            }

            repositories {
                mavenLocal()
            }
        }
    }
}