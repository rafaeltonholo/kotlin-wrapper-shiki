@file:JsModule("@shikijs/transformers")

package dev.tonholo.kotlin.wrapper.shiki.core.transformers.notation

import dev.tonholo.kotlin.wrapper.shiki.core.transformers.ShikiTransformer

external interface TransformerNotationErrorLevelOptions {
    var classMap: Map<String, String>

    /**
     * Class added to the <pre> element when the current code has diff
     */
    var classActivePre: String
}

external fun transformerNotationErrorLevel(
    options: TransformerNotationErrorLevelOptions = definedExternally,
): ShikiTransformer = definedExternally
