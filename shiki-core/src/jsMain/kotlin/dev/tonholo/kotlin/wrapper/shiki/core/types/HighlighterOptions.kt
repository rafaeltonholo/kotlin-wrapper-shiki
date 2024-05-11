package dev.tonholo.kotlin.wrapper.shiki.core.types

import js.objects.jso

external interface Highlighter

external interface HighlighterOptions {
    var themes: Array<String>

    @JsName("langs")
    var languages: Array<String>
    var langAlias: Map<String, String>
}

fun HighlighterOptions(block: HighlighterOptions.() -> Unit): HighlighterOptions =
    jso(block)
