import dev.tonholo.kotlin.wrapper.shiki.gradle.applyGroupAndVersion
import dev.tonholo.kotlin.wrapper.shiki.gradle.libs

plugins {
    kotlin("multiplatform")
    id("npm-conventions")
    com.github.gmazzo.buildconfig
}

applyGroupAndVersion()

kotlin {
    js {
        useEsModules()
        browser {
            commonWebpackConfig {
                outputFileName = group.toString().replace('.', '-') + ".js"
            }
        }
        binaries.executable()
    }
}

buildConfig {
    useKotlinOutput()
    buildConfigField("SHIKI_VERSION", libs.versions.shiki.get())
}
