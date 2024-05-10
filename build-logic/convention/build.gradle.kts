plugins {
    `kotlin-dsl`
}

kotlin {
    explicitApi()
}

dependencies {
    // Workaround for using version catalog in Kotlin script convention plugins
    // https://github.com/gradle/gradle/issues/15383#issuecomment-779893192
    implementation(files(libs.javaClass.superclass.protectionDomain.codeSource.location))
    implementation(libs.kotlin.gradlePlugin)
}

