plugins {
    id("dev.tonholo.kotlin.wrapper.shiki.library")
}

kotlin {
    sourceSets {
        jsMain.dependencies {
            implementation(libs.kotlin.wrappers)
            api(npm(name = "shiki", version = libs.versions.shiki.get()))
        }
    }
}
