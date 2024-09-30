package dev.tonholo.kotlin.wrapper.shiki.gradle

import org.gradle.api.Project

private const val GROUP: String = "dev.tonholo.kotlin.wrapper.shiki"

internal fun Project.applyGroupAndVersion() {
    group = GROUP
    version = "1.0.0-${libs.versions.shiki.get()}"
}
