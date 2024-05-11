package dev.tonholo.kotlin.wrapper.shiki.core

import dev.tonholo.kotlin.wrapper.shiki.core.types.CodeToHastOptions
import dev.tonholo.kotlin.wrapper.shiki.core.types.Highlighter
import dev.tonholo.kotlin.wrapper.shiki.core.types.HighlighterOptions
import kotlin.js.Promise

external interface ShikiApi {
    suspend fun getHighlighter(options: HighlighterOptions): Highlighter
    @JsName("codeToHtml")
    fun codeToHtmlPromise(
        code: String,
        options: CodeToHastOptions,
    ): Promise<String>
}
