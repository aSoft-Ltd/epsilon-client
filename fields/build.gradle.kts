plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization")
    id("tz.co.asoft.library")
}

description = "A kotlin multiplatform library for headless input file fields"

kotlin {
    if (Targeting.JVM) jvm { library() }
    if (Targeting.JS) js(IR) { library() }
    if (Targeting.JS) wasmJs { library() }
    if (Targeting.OSX) (iosTargets() + macOsTargets())
    if (Targeting.LINUX) linuxTargets()

    sourceSets {
        val commonMain by getting {
            dependencies {
                api(libs.epsilon.core)
                api(libs.symphony.input.core)
            }
        }

        val commonTest by getting {
            dependencies {
                implementation(libs.kommander.core)
            }
        }
    }
}
