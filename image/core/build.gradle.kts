plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization")
    id("tz.co.asoft.library")
}

description = "A kotlin multiplatform library for headless image input uploads"

kotlin {
    jvm { library() }
    if (Targeting.JS) js(IR) { library() }
    if (Targeting.WASM) wasmJs { library() }
    val osxTargets = if (Targeting.OSX) (iosTargets() + macOsTargets()) else listOf()
//    val ndkTargets = if (Targeting.NDK) ndkTargets() else listOf()
    val linuxTargets = if (Targeting.LINUX) linuxTargets() else listOf()
//    val mingwTargets = if (Targeting.MINGW) mingwTargets() else listOf()

    sourceSets {
        val commonMain by getting {
            dependencies {
                api(libs.epsilon.core)
                api(libs.cinematic.live.core)
            }
        }

        val commonTest by getting {
            dependencies {
                implementation(libs.kommander.core)
            }
        }
    }
}
