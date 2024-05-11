@file:JsModule("@shikijs/transformers")

package dev.tonholo.kotlin.wrapper.shiki.core.transformers.notation

import dev.tonholo.kotlin.wrapper.shiki.core.transformers.ShikiTransformer

external interface TransformerNotationWordHighlightOptions {
    /**
     * Class for highlighted words
     */
    var classActiveWord: String?
    /**
     * Class added to the root element when the code has highlighted words
     */
    var classActivePre: String?
}

external fun transformerNotationWordHighlight(
    options: TransformerNotationWordHighlightOptions = definedExternally,
): ShikiTransformer = definedExternally
