@file:JsModule("@shikijs/transformers")

package dev.tonholo.kotlin.wrapper.shiki.core.transformers.notation

import dev.tonholo.kotlin.wrapper.shiki.core.transformers.ShikiTransformer

external interface TransformerNotationHighlightOptions {
    /**
     * Class for highlighted lines
     */
    var classActiveLine: String?
    /**
     * Class added to the root element when the code has highlighted lines
     */
    var classActivePre: String?
}

external fun transformerNotationHighlight(
    options: TransformerNotationHighlightOptions = definedExternally,
): ShikiTransformer = definedExternally
