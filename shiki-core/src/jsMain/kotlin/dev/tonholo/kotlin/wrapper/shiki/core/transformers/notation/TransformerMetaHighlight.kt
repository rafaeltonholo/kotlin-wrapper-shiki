@file:JsModule("@shikijs/transformers")

package dev.tonholo.kotlin.wrapper.shiki.core.transformers.notation

import dev.tonholo.kotlin.wrapper.shiki.core.transformers.ShikiTransformer

external interface TransformerMetaHighlightOptions {
    /**
     * Class for highlighted lines
     *
     * @default 'highlighted'
     */
    var className: String?
}

external fun transformerMetaHighlight(
    options: TransformerMetaHighlightOptions = definedExternally,
): ShikiTransformer = definedExternally
