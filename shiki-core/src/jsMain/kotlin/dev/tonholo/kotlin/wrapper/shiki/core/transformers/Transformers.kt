package dev.tonholo.kotlin.wrapper.shiki.core.transformers

import dev.tonholo.kotlin.wrapper.shiki.core.types.CodeToHastOptions
import dev.tonholo.kotlin.wrapper.shiki.core.types.Structure
import dev.tonholo.kotlin.wrapper.shiki.core.types.ThemedToken
import dev.tonholo.kotlin.wrapper.shiki.core.types.TokensResult

external interface TransformerOptions {
    var transformers: Array<ShikiTransformer>
}

external interface ShikiTransformer {
    /**
     * Name of the transformer
     */
    var name: String?

    /**
     * Transform the raw input code before passing to the highlighter.
     */
    fun preprocess(`this`: ShikiTransformerContextCommon, code: String, options: CodeToHastOptions): String

    /**
     * Transform the full tokens list before converting to HAST.
     * Return a new tokens list will replace the original one.
     */
    fun tokens(`this`: ShikiTransformerContextSource, tokens: Array<Array<ThemedToken>>): Array<Array<ThemedToken>>

    /**
     * Transform the entire generated HAST tree. Return a new Node will replace the original one.
     */
    fun root(`this`: ShikiTransformerContext, hast: dynamic /* Root */): dynamic /* Root */

    /**
     * Transform the `<pre>` element. Return a new Node will replace the original one.
     */
    fun pre(`this`: ShikiTransformerContext, hast: dynamic /* Element */): dynamic /* Element */

    /**
     * Transform the `<code>` element. Return a new Node will replace the original one.
     */
    fun code(`this`: ShikiTransformerContext, hast: dynamic /* Element */): dynamic /* Element */

    /**
     * Transform each line `<span class="line">` element.
     *
     * @param hast
     * @param line 1-based line Int
     */
    fun line(`this`: ShikiTransformerContext, hast: dynamic /* Element */, line: Int): dynamic /* Element */

    /**
     * Transform each token `<span>` element.
     */
    fun span(
        `this`: ShikiTransformerContext,
        hast: dynamic /* Element */,
        line: Int,
        col: Int,
        lineElement: dynamic /* Element */
    ): dynamic /* Element */

    /**
     * Transform the generated HTML String before returning.
     * This hook will only be called with `codeToHtml`.
     */
    fun postprocess(`this`: ShikiTransformerContextCommon, html: String, options: CodeToHastOptions): String
}

external interface ShikiTransformerContextMeta

/**
 * Common transformer context for all transformers hooks
 */
external interface ShikiTransformerContextCommon {
    val meta: ShikiTransformerContextMeta
    val options: CodeToHastOptions
    fun codeToHast(code: String, options: CodeToHastOptions): dynamic /* Root */
    fun codeToTokens(code: String, options: dynamic /* CodeToTokensOptions */): TokensResult
}

external interface ShikiTransformerContextSource : ShikiTransformerContextCommon {
    val source: String
}

/**
 * Transformer context for HAST related hooks
 */
external interface ShikiTransformerContext : ShikiTransformerContextSource {
    val tokens: Array<Array<ThemedToken>>
    val root: dynamic /* Root */
    val pre: dynamic /* Element */
    val code: dynamic /* Element */
    val lines: Array<dynamic /* Element */>

    val structure: Structure

    /**
     * Utility to append class to a hast node
     *
     * If the `property.class` is a String, it will be splitted by space and converted to an array.
     */
    fun addClassToHast(hast: dynamic /* Element */, className: dynamic): dynamic /* Element */
}
