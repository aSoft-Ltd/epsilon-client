plugins {
    kotlin("js")
    id("tz.co.asoft.library")
}

description = "A kotlin/js library for helping in uploading images on web"

kotlin {
    js(IR) { browserLib() }
    sourceSets {
        val main by getting {
            dependencies {
                api(projects.epsilonImageCore)
                api(project.dependencies.platform(kotlinw.bom))
                api(kotlinw.browser)
            }
        }
    }
}