pluginManagement {
    includeBuild("../build-logic")
}

plugins {
    id("multimodule")
}

fun includeSubs(base: String, path: String = base, vararg subs: String) {
    subs.forEach {
        include(":$base-$it")
        project(":$base-$it").projectDir = File("$path/$it")
    }
}

listOf(
    "kommander", "koncurrent", "kase", "kollections",
    "epsilon-api", "symphony", "cinematic"
).forEach { includeBuild("../$it") }

rootProject.name = "epsilon-client"

includeSubs("epsilon-file", "file", "fields")
includeSubs("epsilon-image", "image", "core", "web")
includeSubs("epsilon-image-react", "image/react", "core", "dom")
includeSubs("epsilon-image-compose", "image/compose", "core", "html")

// grounds
includeSubs("grounds-battle", "grounds/battle", "react")
includeSubs("grounds-battle-compose", "grounds/battle/compose", "core", "html")
