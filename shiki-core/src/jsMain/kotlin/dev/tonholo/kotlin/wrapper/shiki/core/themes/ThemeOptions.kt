package dev.tonholo.kotlin.wrapper.shiki.core.themes

import dev.tonholo.kotlin.wrapper.shiki.core.ShikiOptionsDsl
import js.objects.jso

external interface ThemeOptions {
    var themes: ThemeInput
    var defaultColor: String
}

internal fun ThemeOptions(builder: ThemeOptions.() -> Unit): ThemeOptions =
    jso(builder)

external interface ThemeInput {
    var light: String
    var dark: String
}

@OptIn(ExperimentalJsExport::class)
@JsExport.Ignore
fun ThemeInput(
    builder: ThemeInput.() -> Unit,
): ThemeInput = js("({})").unsafeCast<ThemeInput>().apply(builder)

class ThemeBuilder {
    var light: ShikiTheme? = null
    var dark: ShikiTheme? = light
    var default: DefaultTheme = DefaultTheme.Light
        private set

    fun activeTheme(theme: DefaultTheme) {
        default = theme
    }

    fun build(): ThemeOptions {
        check(light != null && dark != null) { "At least one theme is required" }
        val lightTheme = requireNotNull(light ?: dark)
        val darkTheme = requireNotNull(dark ?: light)

        return ThemeOptions {
            themes = ThemeInput {
                light = lightTheme.name
                dark = darkTheme.name
            }
            defaultColor = default.name.lowercase()
        }
    }
}

enum class DefaultTheme {
    Light,
    Dark,
}

@ShikiOptionsDsl
fun shikiTheme(builder: ThemeBuilder.() -> Unit): ThemeOptions =
    ThemeBuilder().apply(builder).build()
