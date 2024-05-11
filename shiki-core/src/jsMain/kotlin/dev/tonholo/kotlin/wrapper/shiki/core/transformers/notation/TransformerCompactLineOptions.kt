@file:JsModule("@shikijs/transformers")

package dev.tonholo.kotlin.wrapper.shiki.core.transformers.notation

import dev.tonholo.kotlin.wrapper.shiki.core.transformers.ShikiTransformer

external interface TransformerCompactLineOption {
    /**
     * 1-based line number.
     */
    var line: Int
    var classes: Array<String>?
}

external fun transformerCompactLineOptions(
    options: TransformerCompactLineOption = definedExternally,
): ShikiTransformer = definedExternally
