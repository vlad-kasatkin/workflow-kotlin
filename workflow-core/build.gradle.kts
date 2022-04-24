plugins {
  `kotlin-multiplatform`
  id("org.jetbrains.dokka")
}

apply(from = rootProject.file(".buildscript/configure-maven-publish.gradle"))

kotlin {
  jvm { withJava() }
  ios()

  sourceSets {
    all {
      languageSettings.apply {
        optIn("kotlin.RequiresOptIn")
      }
    }
    val commonMain by getting {
      dependencies {
        api(libs.kotlin.jdk6)
        api(libs.kotlinx.coroutines.core)
        // For Snapshot.
        api(libs.squareup.okio)
      }
    }
    val commonTest by getting {
      dependencies {
        implementation(libs.kotlinx.atomicfu)
        implementation(libs.kotlinx.coroutines.test.common)
        implementation(libs.kotlin.test.jdk)
      }
    }
    val jvmMain by getting {
      dependencies {
        compileOnly(libs.jetbrains.annotations)
      }
    }
  }
}
