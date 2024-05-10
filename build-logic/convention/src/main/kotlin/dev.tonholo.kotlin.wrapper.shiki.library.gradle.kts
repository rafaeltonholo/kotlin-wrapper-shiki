import dev.tonholo.kotlin.wrapper.shiki.gradle.KotlinWrapperShikiConfig

plugins {
    kotlin("multiplatform")
    id("npm-conventions")
}

group = KotlinWrapperShikiConfig.GROUP
version = KotlinWrapperShikiConfig.VERSION

kotlin {
    js {
        browser {
            commonWebpackConfig {
                outputFileName = group.toString().replace('.', '-') + ".js"
            }
        }
        binaries.executable()
    }
}
