@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinAndroid)
    id("maven-publish")
}

android {
    namespace = "com.jmg.baseproject"
    compileSdk = 33

    defaultConfig {
        minSdk = 24

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
        kotlinCompilerExtensionVersion = "1.4.3"
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
//    implementation ("com.github.kruid9403:LMBase:1.0.0")


    //Compose
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation (libs.androidx.material.icons.extended)
    implementation (libs.androidx.material)
    implementation (libs.androidx.constraintlayout.compose)
    implementation (libs.androidx.lifecycle.viewmodel.compose)
    implementation (libs.androidx.hilt.navigation.compose)
}

publishing{
    publications{
        register<MavenPublication>("release"){
            groupId = "com.jeremykruid.baseProject"
            artifactId = "baseProject"
            version = "1.0.0"
            afterEvaluate{
                from(components["release"])
            }

        }
    }
}