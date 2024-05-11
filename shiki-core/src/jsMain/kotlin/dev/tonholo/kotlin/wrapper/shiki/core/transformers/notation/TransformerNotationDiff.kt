@file:JsModule("@shikijs/transformers")

package dev.tonholo.kotlin.wrapper.shiki.core.transformers.notation

import dev.tonholo.kotlin.wrapper.shiki.core.transformers.ShikiTransformer

external interface TransformerNotationDiffOptions {
    /**
     * Class for added lines
     */
    var classLineAdd: String
    /**
     * Class for removed lines
     */
    var classLineRemove: String
    /**
     * Class added to the <pre> element when the current code has diff
     */
    var classActivePre: String
}

external fun transformerNotationDiff(
    options: TransformerNotationDiffOptions = definedExternally,
): ShikiTransformer = definedExternally
