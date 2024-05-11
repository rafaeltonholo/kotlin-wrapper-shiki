@file:JsModule("@shikijs/transformers")

package dev.tonholo.kotlin.wrapper.shiki.core.transformers.notation

import dev.tonholo.kotlin.wrapper.shiki.core.transformers.ShikiTransformer

external interface TransformerNotationFocusOptions {
    /**
     * Class for focused lines
     */
    var classActiveLine: String?

    /**
     * Class added to the root element when the code has focused lines
     */
    var classActivePre: String?
}

external fun transformerNotationFocus(
    options: TransformerNotationFocusOptions = definedExternally,
): ShikiTransformer = definedExternally
