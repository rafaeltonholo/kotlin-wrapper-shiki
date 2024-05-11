@file:JsModule("@shikijs/transformers")

package dev.tonholo.kotlin.wrapper.shiki.core.transformers.notation

import dev.tonholo.kotlin.wrapper.shiki.core.transformers.ShikiTransformer

external interface TransformerMetaWordHighlightOptions {
    /**
     * Class for highlighted words
     *
     * @default 'highlighted-word'
     */
    var className: String?
}

external fun transformerMetaWordHighlight(
    options: TransformerMetaWordHighlightOptions = definedExternally,
): ShikiTransformer = definedExternally
