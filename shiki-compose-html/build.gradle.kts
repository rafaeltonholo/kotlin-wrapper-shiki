plugins {
    id("dev.tonholo.kotlin.wrapper.shiki.library")
    alias(libs.plugins.jetbrains.compose)
    alias(libs.plugins.org.jetbrains.kotlin.plugin.compose)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(compose.runtime)
        }
        jsMain.dependencies {
            implementation(compose.html.core)
            implementation(libs.kotlinx.coroutines.core.js)
            implementation(projects.shikiCore)
        }
    }
}
