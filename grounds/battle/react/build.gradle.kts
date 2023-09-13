plugins {
    kotlin("js")
    id("tz.co.asoft.library")
}

description = "A sample for image input in react"

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
        val main by getting {
            dependencies {
                implementation(projects.epsilonImageReactDom)
            }
        }
    }
}