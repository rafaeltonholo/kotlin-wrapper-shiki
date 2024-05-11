package dev.tonholo.kotlin.wrapper.shiki.core.types

import dev.tonholo.kotlin.wrapper.shiki.core.transformers.TransformerOptions
import js.objects.jso

external interface CodeToHastOptions : TransformerOptions {
    var lang: String
    var themes: ThemeInput

    /**
     * Merge whitespace tokens to saving extra `<span>`.
     *
     * When set to true, it will merge whitespace tokens with the next token.
     * When set to false, it keep the output as-is.
     * When set to `never`, it will force to separate leading and trailing spaces from tokens.
     *
     * @default true
     */
    var mergeWhitespaces: Boolean?

    /**
     * The structure of the generated HAST and HTML.
     *
     * - `classic`: The classic structure with `<pre>` and `<code>` elements, each line wrapped with a `<span class="line">` element.
     * - `inline`: All tokens are rendered as `<span>`, line breaks are rendered as `<br>`. No `<pre>` or `<code>` elements. Default forground and background colors are not applied.
     *
     * @default 'classic'
     */
    var structure: Structure /*'classic' | 'inline'*/

    var defaultColor: String
}

fun CodeToHastOptions(
    builder: CodeToHastOptions.() -> Unit,
): CodeToHastOptions = jso(builder)

external interface ThemeInput {
    var light: String
    var dark: String
}

@OptIn(ExperimentalJsExport::class)
@JsExport.Ignore
fun ThemeInput(
    builder: ThemeInput.() -> Unit,
): ThemeInput = js("({})").unsafeCast<ThemeInput>().apply(builder)

value class Structure(val value: String) {
    companion object {
        val CLASSIC = Structure("classic")
        val INLINE = Structure("inline")
    }
}
