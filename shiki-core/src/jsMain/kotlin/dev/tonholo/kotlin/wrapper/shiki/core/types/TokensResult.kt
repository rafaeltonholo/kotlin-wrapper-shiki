package dev.tonholo.kotlin.wrapper.shiki.core.types

external interface TokensResult {
    /**
     * 2D array of tokens, first dimension is lines, second dimension is tokens in a line.
     */
    val tokens: Array<Array<ThemedToken>>

    /**
     * Foreground color of the code.
     */
    val fg: String?

    /**
     * Background color of the code.
     */
    val bg: String?

    /**
     * A string representation of themes applied to the token.
     */
    val themeName: String?

    /**
     * Custom style string to be applied to the root `<pre>` element.
     * When specified, `fg` and `bg` will be ignored.
     */
    val rootStyle: String?
}

external interface ThemedToken : TokenStyles, TokenBase

external interface TokenBase {
    /**
     * The content of the token
     */
    val content: String

    /**
     * The start offset of the token, relative to the input code. 0-indexed.
     */
    val offset: Int

    /**
     * Explanation of
     *
     * - token text's matching scopes
     * - reason that token text is given a color (one matching scope matches a rule (scope -> color) in the theme)
     */
    val explanation: Array<ThemedTokenExplanation>?
}

external interface TokenStyles {
    /**
     * 6 or 8 digit hex code representation of the token's color
     */
    val color: String?

    /**
     * 6 or 8 digit hex code representation of the token's background color
     */
    val bgColor: String?

    /**
     * Font style of token. Can be None/Italic/Bold/Underline
     */
    val fontStyle: dynamic /*FontStyle?*/

    /**
     * Override with custom inline style for HTML renderer.
     * When specified, `color` and `fontStyle` will be ignored.
     */
    val htmlStyle: String?
}

external interface ThemedTokenExplanation {
    val content: String
    val scopes: Array<ThemedTokenScopeExplanation>
}

external interface ThemedTokenScopeExplanation {
    val scopeName: String
    val themeMatches: Array<Any>
}

// TODO: figure out how to map a typescript enum to kotlin.
//external enum FontStyle {
//    NotSet,
//    None,
//    Italic,
//    Bold,
//    Underline
//}
