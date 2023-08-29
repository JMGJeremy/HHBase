@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinAndroid)
    id("maven-publish")
}

android {
    namespace = "com.jmg.baseproject"
    compileSdk = 34

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

    //FontAwesome
    implementation("com.github.Gurupreet:FontAwesomeCompose:1.0.0")

    //coil
    implementation(libs.coil.compose)

// retrofit
    implementation (libs.retrofit)
    implementation (libs.converter.gson)
    implementation (libs.logging.interceptor)

    //Camera

    implementation(libs.androidx.camera.core)
    implementation(libs.androidx.camera.camera2)
    implementation(libs.androidx.camera.lifecycle)
    implementation(libs.androidx.ui)
    implementation(libs.androidx.camera.view)
    implementation(libs.androidx.camera.video)


    //Permissions
    implementation(libs.accompanist.permissions)

}

publishing{
    publications{
        register<MavenPublication>("release"){
            groupId = "com.jmgjeremy"
            artifactId = "hhbase"
            version = "1.0.1"
            afterEvaluate{
                from(components["release"])
            }

            repositories {
                mavenLocal()
            }

        }
    }
}