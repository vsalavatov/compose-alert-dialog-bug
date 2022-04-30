import org.jetbrains.compose.compose

plugins {
    kotlin("multiplatform") version "1.6.20"
    id("org.jetbrains.compose") version "1.2.0-alpha01-dev675"
    id("com.android.application") version "7.0.0"
}

group = "com.example"
version = "1.0"

repositories {
    google()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    mavenCentral()
}

kotlin {
    jvm {
        compilations.all {
            kotlinOptions.jvmTarget = "1.8"
            kotlinOptions.freeCompilerArgs += listOf(
                "-opt-in=kotlin.RequiresOptIn",
            )
        }
    }
    android {
        publishAllLibraryVariants()
    }
    sourceSets {
        val commonMain by getting {
            dependencies {
                api(compose.runtime)
                api(compose.foundation)
                api(compose.material)
                api(compose.ui)
            }
        }
        val jvmMain by getting {
            dependencies {
                implementation(compose.desktop.currentOs)
            }
        }
        val androidMain by getting {
            dependencies {
                api("androidx.appcompat:appcompat:1.4.1")
                api("androidx.core:core-ktx:1.7.0")
                implementation("androidx.activity:activity-ktx:1.4.0")
                implementation("androidx.activity:activity-compose:1.4.0")
            }
        }
    }
}

android {
    sourceSets {
        named("main") {
            val androidMain = "src/androidMain"
            manifest.srcFile("$androidMain/AndroidManifest.xml")
            res.setSrcDirs(listOf("$androidMain/res"))
            java.setSrcDirs(listOf("$androidMain/java"))
            kotlin.setSrcDirs(listOf("$androidMain/kotlin"))
        }
    }
    compileSdkVersion(31)
    defaultConfig {
        minSdkVersion(24)
        targetSdkVersion(31)
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

compose.desktop {
    application {
        mainClass = "com.example.ui.MainKt"
    }
}