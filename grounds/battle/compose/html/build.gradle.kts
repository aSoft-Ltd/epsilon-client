plugins {
    id("org.jetbrains.compose")
    kotlin("multiplatform")
    id("tz.co.asoft.library")
}

description = "A sample for image input in compose"

kotlin {
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
        val jsMain by getting {
            dependencies {
                implementation(projects.epsilonImageComposeHtml)
            }
        }
    }
}