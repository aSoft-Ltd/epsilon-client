import org.jetbrains.kotlin.gradle.dsl.KotlinCompile

import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    id("org.jetbrains.compose")
    kotlin("multiplatform")
    id("tz.co.asoft.library")
}

description = "A sample for image input in compose"

kotlin {
    jvm { }
    js(IR) {
        moduleName = project.name
        browser {
            commonWebpackConfig {
                cssSupport {
                    enabled.set(true)
                }
                outputFileName = "main.bundle.js"
            }
        }
        generateTypeScriptDefinitions()
        binaries.executable()
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(projects.epsilonImageComposeCore)
            }
        }

        val jvmMain by getting {
            dependencies {
                implementation(compose.desktop.currentOs)
            }
        }

        val commonTest by getting {
            dependencies {
                implementation(libs.kommander.core)
            }
        }
    }
}

compose {
    kotlinCompilerPlugin.set(kotlinz.versions.compose.compiler)

    desktop {
        application {
            mainClass = "MainKt"
            nativeDistributions {
                targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
                packageName = "symphony-battle-ground"
                packageVersion = "1.0.0"
            }
        }
    }
}

tasks.withType(KotlinCompile::class).configureEach {
    kotlinOptions {
        val v = kotlinz.versions.kotlin.get()
        freeCompilerArgs += listOf(
            "-P", "plugin:androidx.compose.compiler.plugins.kotlin:suppressKotlinVersionCompatibilityCheck=$v"
        )
    }
}