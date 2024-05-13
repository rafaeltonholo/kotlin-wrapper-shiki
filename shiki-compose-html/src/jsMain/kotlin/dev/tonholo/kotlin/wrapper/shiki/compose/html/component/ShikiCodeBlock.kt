package dev.tonholo.kotlin.wrapper.shiki.compose.html.component

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import dev.tonholo.kotlin.wrapper.shiki.core.Shiki
import dev.tonholo.kotlin.wrapper.shiki.core.themes.ThemeOptions
import dev.tonholo.kotlin.wrapper.shiki.core.transformers.ShikiTransformer
import dev.tonholo.kotlin.wrapper.shiki.core.types.CodeToHastOptions
import kotlinx.browser.document
import org.jetbrains.compose.web.ExperimentalComposeWebApi
import org.jetbrains.compose.web.css.CSSColorValue
import org.jetbrains.compose.web.css.CSSLengthOrPercentageValue
import org.jetbrains.compose.web.css.CSSLengthValue
import org.jetbrains.compose.web.css.CSSNumeric
import org.jetbrains.compose.web.css.DisplayStyle
import org.jetbrains.compose.web.css.FlexDirection
import org.jetbrains.compose.web.css.Position
import org.jetbrains.compose.web.css.StylePropertyString
import org.jetbrains.compose.web.css.StyleSheet
import org.jetbrains.compose.web.css.backgroundColor
import org.jetbrains.compose.web.css.borderRadius
import org.jetbrains.compose.web.css.cssRem
import org.jetbrains.compose.web.css.display
import org.jetbrains.compose.web.css.filter
import org.jetbrains.compose.web.css.flexDirection
import org.jetbrains.compose.web.css.flexGrow
import org.jetbrains.compose.web.css.fontSize
import org.jetbrains.compose.web.css.height
import org.jetbrains.compose.web.css.marginLeft
import org.jetbrains.compose.web.css.minus
import org.jetbrains.compose.web.css.opacity
import org.jetbrains.compose.web.css.overflow
import org.jetbrains.compose.web.css.paddingBottom
import org.jetbrains.compose.web.css.paddingLeft
import org.jetbrains.compose.web.css.paddingRight
import org.jetbrains.compose.web.css.paddingTop
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.css.plus
import org.jetbrains.compose.web.css.position
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.css.right
import org.jetbrains.compose.web.css.s
import org.jetbrains.compose.web.css.times
import org.jetbrains.compose.web.css.top
import org.jetbrains.compose.web.css.transitions
import org.jetbrains.compose.web.css.value
import org.jetbrains.compose.web.css.variable
import org.jetbrains.compose.web.css.width
import org.jetbrains.compose.web.dom.AttrBuilderContext
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.Span
import org.jetbrains.compose.web.dom.Style
import org.jetbrains.compose.web.dom.Text
import org.w3c.dom.HTMLDivElement

object ShikiCodeBlockVars {
    val shikiPaddingStart by variable<CSSNumeric>()
    val shikiPaddingEnd by variable<CSSNumeric>()
    val shikiPaddingTop by variable<CSSNumeric>()
    val shikiPaddingBottom by variable<CSSNumeric>()
    val shikiBlur by variable<CSSLengthValue>()
    val shikiBorderRadius by variable<CSSNumeric>()
    val shikiLanguage by variable<StylePropertyString>()
    val shikiLanguageLabelFontSize by variable<CSSNumeric>()
}

@OptIn(ExperimentalComposeWebApi::class)
object ShikiCodeBlockStylesheet : StyleSheet() {
    val container by style {
        position(Position.Relative)

        "[class*='lang-'] pre.shiki" style {
            paddingTop(ShikiCodeBlockVars.shikiPaddingTop.value() + 0.8.cssRem)
            paddingRight(ShikiCodeBlockVars.shikiPaddingEnd.value())
            paddingBottom(ShikiCodeBlockVars.shikiPaddingBottom.value() + 0.8.cssRem)
            paddingLeft(ShikiCodeBlockVars.shikiPaddingStart.value())
            borderRadius(ShikiCodeBlockVars.shikiBorderRadius.value())
            overflow("auto")
            display(DisplayStyle.Flex)
        }

        "[class*='lang-'] pre.shiki code" style {
            display(DisplayStyle.Flex)
            flexDirection(FlexDirection.Column)
            flexGrow(1)
        }

        "[class*='lang-'] pre.shiki code .line:empty" {
            height(1.5.cssRem)
        }

        "[class*='lang-'] pre.shiki.has-focused code .line:not(.focused)" style {
            opacity(0.7f)
            filter {
                blur(ShikiCodeBlockVars.shikiBlur.value().unsafeCast<CSSLengthValue>())
            }
            transitions {
                "opacity" {
                    duration = 0.35.s
                }
                "filter" {
                    duration = 0.35.s
                }
            }
        }
        "[class*='lang-'] pre.shiki.has-focused:hover code .line:not(.focused)" style {
            opacity(1f)
            filter {
                blur(0.px)
            }
        }
        "[class*='lang-'] pre.shiki code .highlighted" style {
            flexGrow(1)
            width(
                100.percent +
                    ShikiCodeBlockVars.shikiPaddingStart.value() +
                    ShikiCodeBlockVars.shikiPaddingEnd.value()
            )
            transitions {
                "background-color" {
                    duration = 0.5.s
                }
            }
            backgroundColor(
                "color-mix(in srgb, currentcolor 20%, transparent)".unsafeCast<CSSColorValue>(),
            )
            paddingLeft(ShikiCodeBlockVars.shikiPaddingStart.value())
            marginLeft(
                ShikiCodeBlockVars.shikiPaddingStart.value() * -1
            )
        }
    }
    val language by style {
        position(Position.Absolute)
        top((ShikiCodeBlockVars.shikiPaddingTop.value() - 0.65.cssRem).unsafeCast<CSSLengthOrPercentageValue>())
        right(value = ShikiCodeBlockVars.shikiPaddingEnd.value().unsafeCast<CSSLengthOrPercentageValue>())
        fontSize(ShikiCodeBlockVars.shikiLanguageLabelFontSize.value(0.8.cssRem))
        opacity(0.5f)
    }
}

const val SHIKI_STYLE_ID = "shiki-code-block-style"

@Composable
fun ShikiCodeBlock(
    language: String,
    code: String,
    themeOptions: ThemeOptions,
    attrs: AttrBuilderContext<HTMLDivElement>? = null,
    transformers: List<ShikiTransformer> = emptyList(),
    // TODO: move to Defaults object
    paddingStart: CSSLengthValue? = null,
    paddingEnd: CSSLengthValue? = null,
    paddingTop: CSSLengthValue? = null,
    paddingBottom: CSSLengthValue? = null,
    blur: CSSLengthValue? = null,
    borderRadius: CSSLengthValue? = null,
) {
    var isReady by remember { mutableStateOf(false) }
    val isPendingStyle by remember(themeOptions.defaultColor) {
        derivedStateOf {
            document.querySelector(
                "style[id='$SHIKI_STYLE_ID-${themeOptions.defaultColor}']",
            ) == null
        }
    }
    var parsedCode by remember { mutableStateOf("") }
    val unescapedCode = remember(code) {
        code.replace(oldValue = "&amp;", newValue = "&")
            .replace(oldValue = "&lt;", newValue = "<")
            .replace(oldValue = "&gt;", newValue = ">")
            .replace(oldValue = "&quot;", newValue = "\"")
            .replace(oldValue = "&apos;", newValue = "'")
            .replace(oldValue = "&#39;", newValue = "'")
            .replace(oldValue = "&#34;", newValue = "\"")
            .replace(oldValue = "&#96;", newValue = "`")
            .replace(oldValue = "&#123;", newValue = "{")
            .replace(oldValue = "&#125;", newValue = "}")
            .replace(oldValue = "&#91;", newValue = "[")
            .replace(oldValue = "&#93;", newValue = "]")
            .replace(oldValue = "&#40;", newValue = "(")
            .replace(oldValue = "&#41;", newValue = ")")
    }

    LaunchedEffect(themeOptions, transformers) {
        isReady = false
        Shiki.initialize()
        parsedCode = Shiki.instance.codeToHtml(
            unescapedCode,
            options = CodeToHastOptions {
                lang = language
                themes = themeOptions.themes
                defaultColor = themeOptions.defaultColor
                this.transformers = transformers.toTypedArray()
            },
        )
        isReady = true
    }

    if (isReady && isPendingStyle) {
        Style(
            applyAttrs = {
                id("${SHIKI_STYLE_ID}-${themeOptions.defaultColor}")
            },
            ShikiCodeBlockStylesheet.cssRules,
        )
    }

    Div(
        attrs = {
            attrs?.invoke(this)
            classes(ShikiCodeBlockStylesheet.container)
            style {
                property("--${ShikiCodeBlockVars.shikiLanguage.name}", "\"$language\"")
                paddingStart?.let {
                    property("--${ShikiCodeBlockVars.shikiPaddingStart.name}", it)
                }
                paddingEnd?.let {
                    property("--${ShikiCodeBlockVars.shikiPaddingEnd.name}", it)
                }
                paddingTop?.let {
                    property("--${ShikiCodeBlockVars.shikiPaddingTop.name}", it)
                }
                paddingBottom?.let {
                    property("--${ShikiCodeBlockVars.shikiPaddingBottom.name}", it)
                }
                blur?.let {
                    property("--${ShikiCodeBlockVars.shikiBlur.name}", it)
                }
                borderRadius?.let {
                    property("--${ShikiCodeBlockVars.shikiBorderRadius.name}", it)
                }
            }
        },
    ) {
        Span(
            attrs = {
                classes(ShikiCodeBlockStylesheet.language)
            },
        ) {
            Text(language)
        }

        Div(
            attrs = {
                classes("lang-$language")
            },
        ) {
            DisposableEffect(parsedCode) {
                scopeElement.innerHTML = parsedCode
                onDispose { }
            }
        }
    }
}
