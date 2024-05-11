plugins {
    id("dev.tonholo.kotlin.wrapper.shiki.library")
}

kotlin {
    sourceSets {
        jsMain.dependencies {
            implementation(libs.kotlin.wrappers)
            implementation(libs.kotlinx.coroutines.core.js)
            api(npm(name = "shiki", version = libs.versions.shiki.get()))
            api(npm(name = "@shikijs/transformers", version = libs.versions.shiki.get()))
        }
    }
}
