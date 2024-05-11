@file:JsModule("@shikijs/transformers")

package dev.tonholo.kotlin.wrapper.shiki.core.transformers.notation

import dev.tonholo.kotlin.wrapper.shiki.core.transformers.ShikiTransformer

external interface TransformerRenderWhitespaceOptions {
    /**
     * Class for tab
     *
     * @default 'tab'
     */
    var classTab: String?

    /**
     * Class for space
     *
     * @default 'space'
     */
    var classSpace: String?

    /**
     * Position of rendered whitespace
     * @default all position
     */
    var position: String?  /* 'all' | 'boundary' | 'trailing' */
}

external fun transformerRenderWhitespace(
    options: TransformerNotationErrorLevelOptions = definedExternally,
): ShikiTransformer = definedExternally
